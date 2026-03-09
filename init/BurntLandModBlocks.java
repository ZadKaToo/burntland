

package net.com.burntland.init;

import net.com.burntland.block.*;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.com.burntland.BurntLandMod;

public class BurntLandModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, BurntLandMod.MODID);
	public static final RegistryObject<Block> BURNT_GARSS = REGISTRY.register("burnt_garss", () -> new BurntGarssBlock());
	public static final RegistryObject<Block> BURNT_TALL_GRASS = REGISTRY.register("burnt_tall_grass", () -> new BurnttallgrassBlock());
	public static final RegistryObject<Block> BURNT_SHORT_GRASS = REGISTRY.register("burnt_short_grass", () -> new BurntshortgrassBlock());
	public static final RegistryObject<Block> FORGOTTEN_SEEDS = REGISTRY.register("forgotten_seeds", () -> new BurntpuregrassBlock());
	public static final RegistryObject<Block> BURNT_LOGS = REGISTRY.register("burnt_logs", () -> new BurntlogsBlock());
	public static final RegistryObject<Block> BURNTGRASS_BLOCKS = REGISTRY.register("burntgrass_blocks", () -> new BurntgrassblocksBlock());
	public static final RegistryObject<Block> WOOL_WALL = REGISTRY.register("wool_wall", () -> new WoolwallsBlock());
	public static final RegistryObject<Block> LAST_LIGHTS = REGISTRY.register("last_lights", () -> new LastlightsBlock());
	public static final RegistryObject<Block> RUNE_BLOCKS = REGISTRY.register("rune_blocks", () -> new RuneblocksBlock());
	public static final RegistryObject<Block> BURNTDIRT_BLOCKS = REGISTRY.register("burntdirt_blocks", () -> new BurntdirtblocksBlock());
	public static final RegistryObject<Block> ABANDONED_STAIRS = REGISTRY.register("abandoned_stairs", () -> new AbandonedstairsBlock());
	public static final RegistryObject<Block> ABANDONED_SLABS = REGISTRY.register("abandoned_slabs", () -> new AbandonedslabsBlock());
	public static final RegistryObject<Block> ABANDONED_FENCES = REGISTRY.register("abandoned_fences", () -> new AbandonedfencesBlock());
	public static final RegistryObject<Block> ABANDONED_TRAPDOOR = REGISTRY.register("abandoned_trapdoor", () -> new AbandonedtrapdoorBlock());
	public static final RegistryObject<Block> ABANDONED_DOOR = REGISTRY.register("abandoned_door", () -> new AbandoneddoorBlock());
	public static final RegistryObject<Block> ABANDONED_FENCEGATE = REGISTRY.register("abandoned_fencegate", () -> new AbandonedfencegateBlock());
	public static final RegistryObject<Block> ABANDONED_BUTTON = REGISTRY.register("abandoned_button", () -> new AbandonedbuttonBlock());
	public static final RegistryObject<Block> ABANDONED_PRESSUREPLATE = REGISTRY.register("abandoned_pressureplate", () -> new AbandonedpressureplateBlock());
	public static final RegistryObject<Block> STAKE_HEDGE = REGISTRY.register("stake_hedge", () -> new Obstacles1Block());
	public static final RegistryObject<Block> CALTROPS = REGISTRY.register("caltrops", () -> new Obstacles2Block());
	public static final RegistryObject<Block> ABATISS = REGISTRY.register("abatiss", () -> new Obstacles3Block());
	public static final RegistryObject<Block> BARBICANS = REGISTRY.register("barbicans", () -> new Obstacles4Block());
	public static final RegistryObject<Block> ABATIS = REGISTRY.register("abatis", () -> new Supostacles1Block());
	public static final RegistryObject<Block> BARBICAN = REGISTRY.register("barbican", () -> new Supostacles2Block());
	public static final RegistryObject<Block> BURNT_GRASS_PATH = REGISTRY.register("burnt_grass_path", () -> new BurntgrasspathBlock());
	public static final RegistryObject<Block> TALL_MASALAGE = REGISTRY.register("tall_masalage", () -> new TallmasalageBlock());
	public static final RegistryObject<Block> RUSTED_IRON_BLOCKS = REGISTRY.register("rusted_iron_blocks", () -> new RustedIronBlocksBlock());
	public static final RegistryObject<Block> RUSTED_CHAIN = REGISTRY.register("rusted_chain", () -> new RustedChainBlock());
	public static final RegistryObject<Block> SOOTY_BRICKS = REGISTRY.register("sooty_bricks", () -> new SootyBricksBlock());
	public static final RegistryObject<Block> ANCIENT_BANNER = REGISTRY.register("ancient_banner", () -> new AncientBannerBlock());
	public static final RegistryObject<Block> WAR_DEBRIS = REGISTRY.register("war_debris", () -> new WarDebrisBlock());
	public static final RegistryObject<Block> SKULL_PILE = REGISTRY.register("skull_pile", () -> new SkullPileBlock());
	public static final RegistryObject<Block> EMPTY_CAGE = REGISTRY.register("empty_cage", () -> new EmptyCageBlock());
	public static final RegistryObject<Block> ANCIENT_POT = REGISTRY.register("ancient_pot", () -> new AncientPotBlock());
	public static final RegistryObject<Block> PITCH_BARREL = REGISTRY.register("pitch_barrel", () -> new PitchBarrelBlock());
	public static final RegistryObject<Block> BROKEN_WHEEL = REGISTRY.register("broken_wheel", () -> new BrokenWheelBlock());
	public static final RegistryObject<Block> LANTERNWITHCAPE = REGISTRY.register("lanternwithcape", () -> new LanternwithcapeBlock());
	public static final RegistryObject<Block> ANCIENT_BANNERSTAND = REGISTRY.register("ancient_bannerstand", () -> new AncientBannerstandBlock());
	public static final RegistryObject<Block> MELTED_CANDLE = REGISTRY.register("melted_candle", () -> new MeltedCandleBlock());
	public static final RegistryObject<Block> TORN_TARGET = REGISTRY.register("torn_target", () -> new TornTargetBlock());
	public static final RegistryObject<Block> ASH_SACK = REGISTRY.register("ash_sack", () -> new AshSackBlock());
	public static final RegistryObject<Block> CHARRED_ROOTS = REGISTRY.register("charred_roots", () -> new CharredRootsBlock());
	public static final RegistryObject<Block> WALL_SHACKLES = REGISTRY.register("wall_shackles", () -> new WallShacklesBlock());
	public static final RegistryObject<Block> WOODEN_CROSS = REGISTRY.register("wooden_cross", () -> new WoodenCrossBlock());
	public static final RegistryObject<Block> RUSTY_GRATE = REGISTRY.register("rusty_grate", () -> new RustyGrateBlock());
	public static final RegistryObject<Block> EXTINGUISHED_TORCH = REGISTRY.register("extinguished_torch", () -> new ExtinguishedTorchBlock());
	public static final RegistryObject<Block> ROTTING_CRATE = REGISTRY.register("rotting_crate", () -> new RottingCrateBlock());
	public static final RegistryObject<Block> BOARDED_WINDOW = REGISTRY.register("boarded_window", () -> new BoardedWindowBlock());
	public static final RegistryObject<Block> TOPPLED_QUIVER = REGISTRY.register("toppled_quiver", () -> new ToppledQuiverBlock());
	public static final RegistryObject<Block> FIREWOOD_STACK = REGISTRY.register("firewood_stack", () -> new FirewoodStackBlock());
	public static final RegistryObject<Block> INK_POT_QUILL = REGISTRY.register("ink_pot_quill", () -> new InkPotQuillBlock());
	public static final RegistryObject<Block> WOODEN_BUCKET = REGISTRY.register("wooden_bucket", () -> new WoodenBucketBlock());
	public static final RegistryObject<Block> ASHLAYER = REGISTRY.register("ashlayer", () -> new AshlayerBlock());
    public static final RegistryObject<Block> BURNT_PLANKS = REGISTRY.register("burnt_planks", () -> new BurntPlanksBlock());
}
