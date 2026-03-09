

package net.com.burntland.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.com.burntland.BurntLandMod;

public class BurntLandModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BurntLandMod.MODID);
	public static final RegistryObject<CreativeModeTab> BRUNT_LAND = REGISTRY.register("brunt_land",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.burntland.brunt_land")).icon(() -> new ItemStack(BurntLandModBlocks.BURNTGRASS_BLOCKS.get())).displayItems((parameters, tabData) -> {
				tabData.accept(BurntLandModBlocks.RUSTY_GRATE.get().asItem());
				tabData.accept(BurntLandModBlocks.TORN_TARGET.get().asItem());
				tabData.accept(BurntLandModBlocks.LANTERNWITHCAPE.get().asItem());
				tabData.accept(BurntLandModBlocks.PITCH_BARREL.get().asItem());
				tabData.accept(BurntLandModBlocks.ROTTING_CRATE.get().asItem());
				tabData.accept(BurntLandModBlocks.RUSTED_IRON_BLOCKS.get().asItem());
				tabData.accept(BurntLandModBlocks.LAST_LIGHTS.get().asItem());
				tabData.accept(BurntLandModBlocks.RUNE_BLOCKS.get().asItem());
				tabData.accept(BurntLandModBlocks.WOOL_WALL.get().asItem());
				tabData.accept(BurntLandModBlocks.BURNT_PLANKS.get().asItem());
				tabData.accept(BurntLandModBlocks.BURNT_LOGS.get().asItem());
				tabData.accept(BurntLandModBlocks.ABANDONED_SLABS.get().asItem());
				tabData.accept(BurntLandModBlocks.ABANDONED_FENCES.get().asItem());
				tabData.accept(BurntLandModBlocks.ABANDONED_TRAPDOOR.get().asItem());
				tabData.accept(BurntLandModBlocks.ABANDONED_DOOR.get().asItem());
				tabData.accept(BurntLandModBlocks.ABANDONED_FENCEGATE.get().asItem());
				tabData.accept(BurntLandModBlocks.ABANDONED_BUTTON.get().asItem());
				tabData.accept(BurntLandModBlocks.ABANDONED_PRESSUREPLATE.get().asItem());
				tabData.accept(BurntLandModBlocks.ABANDONED_STAIRS.get().asItem());
				tabData.accept(BurntLandModBlocks.BURNTGRASS_BLOCKS.get().asItem());
				tabData.accept(BurntLandModBlocks.BURNTDIRT_BLOCKS.get().asItem());
				tabData.accept(BurntLandModBlocks.BURNT_GRASS_PATH.get().asItem());
				tabData.accept(BurntLandModBlocks.BURNT_GARSS.get().asItem());
				tabData.accept(BurntLandModBlocks.BURNT_SHORT_GRASS.get().asItem());
				tabData.accept(BurntLandModBlocks.BURNT_TALL_GRASS.get().asItem());
				tabData.accept(BurntLandModBlocks.TALL_MASALAGE.get().asItem());
				tabData.accept(BurntLandModBlocks.FORGOTTEN_SEEDS.get().asItem());
				tabData.accept(BurntLandModBlocks.CHARRED_ROOTS.get().asItem());
				tabData.accept(BurntLandModBlocks.ASHLAYER.get().asItem());
				tabData.accept(BurntLandModBlocks.BARBICAN.get().asItem());
				tabData.accept(BurntLandModBlocks.CALTROPS.get().asItem());
				tabData.accept(BurntLandModBlocks.STAKE_HEDGE.get().asItem());
				tabData.accept(BurntLandModBlocks.ABATIS.get().asItem());
				tabData.accept(BurntLandModBlocks.RUSTED_CHAIN.get().asItem());
				tabData.accept(BurntLandModBlocks.ANCIENT_BANNER.get().asItem());
				tabData.accept(BurntLandModBlocks.WAR_DEBRIS.get().asItem());
				tabData.accept(BurntLandModBlocks.SKULL_PILE.get().asItem());
				tabData.accept(BurntLandModBlocks.EMPTY_CAGE.get().asItem());
				tabData.accept(BurntLandModBlocks.ANCIENT_POT.get().asItem());
				tabData.accept(BurntLandModBlocks.BROKEN_WHEEL.get().asItem());
				tabData.accept(BurntLandModBlocks.ANCIENT_BANNERSTAND.get().asItem());
				tabData.accept(BurntLandModBlocks.MELTED_CANDLE.get().asItem());
				tabData.accept(BurntLandModBlocks.ASH_SACK.get().asItem());
				tabData.accept(BurntLandModBlocks.WALL_SHACKLES.get().asItem());
				tabData.accept(BurntLandModBlocks.WOODEN_CROSS.get().asItem());
				tabData.accept(BurntLandModBlocks.EXTINGUISHED_TORCH.get().asItem());
				tabData.accept(BurntLandModBlocks.BOARDED_WINDOW.get().asItem());
				tabData.accept(BurntLandModBlocks.TOPPLED_QUIVER.get().asItem());
				tabData.accept(BurntLandModBlocks.FIREWOOD_STACK.get().asItem());
				tabData.accept(BurntLandModBlocks.INK_POT_QUILL.get().asItem());
				tabData.accept(BurntLandModBlocks.WOODEN_BUCKET.get().asItem());
				tabData.accept(BurntLandModItems.AMBROSIA.get());
				tabData.accept(BurntLandModItems.ANTI_LYBB.get());
				tabData.accept(BurntLandModItems.CARNIFEX_AXE.get());
				tabData.accept(BurntLandModItems.ABSOLONCROSS.get());
				tabData.accept(BurntLandModItems.CARNIFEX_SPAWN_EGG.get());
				tabData.accept(BurntLandModItems.SPIRIT_VESSELS_SPAWN_EGG.get());
				tabData.accept(BurntLandModItems.SOUL_THRALLS_SPAWN_EGG.get());
				tabData.accept(BurntLandModItems.ABSOLON_SPAWN_EGG.get());
				tabData.accept(BurntLandModItems.COLLECTOR_SPAWN_EGG.get());
				tabData.accept(BurntLandModItems.TRUEFORMTHECOLLECTOR_SPAWN_EGG.get());
				tabData.accept(BurntLandModItems.WANDER_SPAWN_EGG.get());
			}).build());
}
