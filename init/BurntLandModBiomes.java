package net.com.burntland.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;

import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.biome.FeatureSorter;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.server.MinecraftServer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Registry;
import net.minecraft.core.Holder;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.mojang.datafixers.util.Pair;
import com.google.common.base.Suppliers;

@Mod.EventBusSubscriber
public class BurntLandModBiomes {

    @SubscribeEvent
    @SuppressWarnings("removal")
    public static void onServerAboutToStart(ServerAboutToStartEvent event) {
        MinecraftServer server = event.getServer();
        Registry<DimensionType> dimensionTypeRegistry = server.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE);
        Registry<LevelStem> levelStemTypeRegistry = server.registryAccess().registryOrThrow(Registries.LEVEL_STEM);
        Registry<Biome> biomeRegistry = server.registryAccess().registryOrThrow(Registries.BIOME);

        //  แก้ที่ 1: ใช้ new ResourceLocation และสร้าง Key เตรียมไว้ก่อน
        ResourceKey<Biome> burntlandKey = ResourceKey.create(Registries.BIOME, new ResourceLocation("burntland", "burntland"));

        //  แก้ที่ 2: ดึงค่าแบบปลอดภัย (Safe Check) เพื่อป้องกันการแครช
        Optional<Holder.Reference<Biome>> biomeHolderOpt = biomeRegistry.getHolder(burntlandKey);
        if (biomeHolderOpt.isEmpty()) {
            System.out.println(" [Burnt Land] Warning: Biome 'burntland:burntland' not found in registry! Skipping injection.");
            return; // หยุดทำงานโค้ดส่วนนี้ทันที เกมจะได้ไม่แครช
        }
        Holder<Biome> burntlandHolder = biomeHolderOpt.get();

        for (LevelStem levelStem : levelStemTypeRegistry.stream().toList()) {
            DimensionType dimensionType = levelStem.type().value();
            if (dimensionType == dimensionTypeRegistry.getOrThrow(BuiltinDimensionTypes.OVERWORLD)) {
                ChunkGenerator chunkGenerator = levelStem.generator();

                // Inject biomes to biome source
                if (chunkGenerator.getBiomeSource() instanceof MultiNoiseBiomeSource noiseSource) {
                    List<Pair<Climate.ParameterPoint, Holder<Biome>>> parameters = new ArrayList<>(noiseSource.parameters().values());

                    // แก้ที่ 3: ใช้ Holder ที่ผ่านการเช็คความปลอดภัยแล้วมาใส่
                    addParameterPoint(parameters, new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(-0.5f, 1f), Climate.Parameter.span(-0.1001f, 1f), Climate.Parameter.span(-0.11f, 0.55f), Climate.Parameter.span(0.05f, 0.45f),
                            Climate.Parameter.point(0.0f), Climate.Parameter.span(-1f, 1f), 0), burntlandHolder));
                    addParameterPoint(parameters, new Pair<>(new Climate.ParameterPoint(Climate.Parameter.span(-0.5f, 1f), Climate.Parameter.span(-0.1001f, 1f), Climate.Parameter.span(-0.11f, 0.55f), Climate.Parameter.span(0.05f, 0.45f),
                            Climate.Parameter.point(1.0f), Climate.Parameter.span(-1f, 1f), 0), burntlandHolder));

                    chunkGenerator.biomeSource = MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(parameters));
                    chunkGenerator.featuresPerStep = Suppliers
                            .memoize(() -> FeatureSorter.buildFeaturesPerStep(List.copyOf(chunkGenerator.biomeSource.possibleBiomes()), biome -> chunkGenerator.generationSettingsGetter.apply(biome).features(), true));
                }

                // Inject surface rules
                if (chunkGenerator instanceof NoiseBasedChunkGenerator noiseGenerator) {
                    NoiseGeneratorSettings noiseGeneratorSettings = noiseGenerator.settings.value();
                    SurfaceRules.RuleSource currentRuleSource = noiseGeneratorSettings.surfaceRule();

                    if (currentRuleSource instanceof SurfaceRules.SequenceRuleSource sequenceRuleSource) {
                        List<SurfaceRules.RuleSource> surfaceRules = new ArrayList<>(sequenceRuleSource.sequence());

                        //  แก้ที่ 4: ใช้ burntlandKey ที่สร้างไว้ด้านบน
                        addSurfaceRule(surfaceRules, 1, preliminarySurfaceRule(burntlandKey, BurntLandModBlocks.BURNTGRASS_BLOCKS.get().defaultBlockState(),
                                BurntLandModBlocks.BURNTDIRT_BLOCKS.get().defaultBlockState(), BurntLandModBlocks.BURNTDIRT_BLOCKS.get().defaultBlockState()));

                        NoiseGeneratorSettings moddedNoiseGeneratorSettings = new NoiseGeneratorSettings(noiseGeneratorSettings.noiseSettings(), noiseGeneratorSettings.defaultBlock(), noiseGeneratorSettings.defaultFluid(),
                                noiseGeneratorSettings.noiseRouter(), SurfaceRules.sequence(surfaceRules.toArray(SurfaceRules.RuleSource[]::new)), noiseGeneratorSettings.spawnTarget(), noiseGeneratorSettings.seaLevel(),
                                noiseGeneratorSettings.disableMobGeneration(), noiseGeneratorSettings.aquifersEnabled(), noiseGeneratorSettings.oreVeinsEnabled(), noiseGeneratorSettings.useLegacyRandomSource());

                        noiseGenerator.settings = new Holder.Direct<>(moddedNoiseGeneratorSettings);
                    }
                }
            }
        }
    }

    private static SurfaceRules.RuleSource preliminarySurfaceRule(ResourceKey<Biome> biomeKey, BlockState groundBlock, BlockState undergroundBlock, BlockState underwaterBlock) {
        return SurfaceRules.ifTrue(SurfaceRules.isBiome(biomeKey),
                SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), SurfaceRules.state(groundBlock)), SurfaceRules.state(underwaterBlock))),
                                SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), SurfaceRules.state(undergroundBlock)))));
    }

    private static void addParameterPoint(List<Pair<Climate.ParameterPoint, Holder<Biome>>> parameters, Pair<Climate.ParameterPoint, Holder<Biome>> point) {
        if (!parameters.contains(point))
            parameters.add(point);
    }

    private static void addSurfaceRule(List<SurfaceRules.RuleSource> surfaceRules, int index, SurfaceRules.RuleSource rule) {
        if (!surfaceRules.contains(rule)) {
            if (index >= surfaceRules.size()) {
                surfaceRules.add(rule);
            } else {
                surfaceRules.add(index, rule);
            }
        }
    }
}