

package net.com.burntland.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.BlockItem;

import net.com.burntland.item.LybbItem;
import net.com.burntland.item.ExecutioneraxeItem;
import net.com.burntland.item.AntiLybbItem;
import net.com.burntland.item.AbsoloncrossItem;
import net.com.burntland.BurntLandMod;

public class BurntLandModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, BurntLandMod.MODID);
	public static final RegistryObject<Item> BURNT_PLANKS = block(BurntLandModBlocks.BURNT_PLANKS);
	public static final RegistryObject<Item> BURNT_GARSS = block(BurntLandModBlocks.BURNT_GARSS);
	public static final RegistryObject<Item> BURNT_TALL_GRASS = doubleBlock(BurntLandModBlocks.BURNT_TALL_GRASS);
	public static final RegistryObject<Item> BURNT_SHORT_GRASS = block(BurntLandModBlocks.BURNT_SHORT_GRASS);
	public static final RegistryObject<Item> FORGOTTEN_SEEDS = block(BurntLandModBlocks.FORGOTTEN_SEEDS);
	public static final RegistryObject<Item> BURNT_LOGS = block(BurntLandModBlocks.BURNT_LOGS);
	public static final RegistryObject<Item> BURNTGRASS_BLOCKS = block(BurntLandModBlocks.BURNTGRASS_BLOCKS);
	public static final RegistryObject<Item> WOOL_WALL = block(BurntLandModBlocks.WOOL_WALL);
	public static final RegistryObject<Item> LAST_LIGHTS = block(BurntLandModBlocks.LAST_LIGHTS);
	public static final RegistryObject<Item> RUNE_BLOCKS = block(BurntLandModBlocks.RUNE_BLOCKS);
	public static final RegistryObject<Item> BURNTDIRT_BLOCKS = block(BurntLandModBlocks.BURNTDIRT_BLOCKS);
	public static final RegistryObject<Item> ABANDONED_STAIRS = block(BurntLandModBlocks.ABANDONED_STAIRS);
	public static final RegistryObject<Item> ABANDONED_SLABS = block(BurntLandModBlocks.ABANDONED_SLABS);
	public static final RegistryObject<Item> ABANDONED_FENCES = block(BurntLandModBlocks.ABANDONED_FENCES);
	public static final RegistryObject<Item> ABANDONED_TRAPDOOR = block(BurntLandModBlocks.ABANDONED_TRAPDOOR);
	public static final RegistryObject<Item> ABANDONED_DOOR = doubleBlock(BurntLandModBlocks.ABANDONED_DOOR);
	public static final RegistryObject<Item> ABANDONED_FENCEGATE = block(BurntLandModBlocks.ABANDONED_FENCEGATE);
	public static final RegistryObject<Item> ABANDONED_BUTTON = block(BurntLandModBlocks.ABANDONED_BUTTON);
	public static final RegistryObject<Item> ABANDONED_PRESSUREPLATE = block(BurntLandModBlocks.ABANDONED_PRESSUREPLATE);
	public static final RegistryObject<Item> STAKE_HEDGE = block(BurntLandModBlocks.STAKE_HEDGE);
	public static final RegistryObject<Item> CALTROPS = block(BurntLandModBlocks.CALTROPS);
	public static final RegistryObject<Item> ABATISS = block(BurntLandModBlocks.ABATISS);
	public static final RegistryObject<Item> BARBICANS = block(BurntLandModBlocks.BARBICANS);
	public static final RegistryObject<Item> ABATIS = block(BurntLandModBlocks.ABATIS);
	public static final RegistryObject<Item> BARBICAN = block(BurntLandModBlocks.BARBICAN);
	public static final RegistryObject<Item> CARNIFEX_AXE = REGISTRY.register("carnifex_axe", () -> new ExecutioneraxeItem());
	public static final RegistryObject<Item> CARNIFEX_SPAWN_EGG = REGISTRY.register("carnifex_spawn_egg", () -> new ForgeSpawnEggItem(BurntLandModEntities.CARNIFEX, -6737152, -1, new Item.Properties()));
	public static final RegistryObject<Item> SOUL_THRALLS_SPAWN_EGG = REGISTRY.register("soul_thralls_spawn_egg", () -> new ForgeSpawnEggItem(BurntLandModEntities.SOUL_THRALLS, -1, -16777216, new Item.Properties()));
	public static final RegistryObject<Item> SPIRIT_VESSELS_SPAWN_EGG = REGISTRY.register("spirit_vessels_spawn_egg", () -> new ForgeSpawnEggItem(BurntLandModEntities.SPIRIT_VESSELS, -16777165, -16777063, new Item.Properties()));
	public static final RegistryObject<Item> BURNT_GRASS_PATH = block(BurntLandModBlocks.BURNT_GRASS_PATH);
	public static final RegistryObject<Item> ABSOLON_SPAWN_EGG = REGISTRY.register("absolon_spawn_egg", () -> new ForgeSpawnEggItem(BurntLandModEntities.ABSOLON, -16777216, -2031872, new Item.Properties()));
	public static final RegistryObject<Item> AMBROSIA = REGISTRY.register("ambrosia", () -> new LybbItem());
	public static final RegistryObject<Item> TALL_MASALAGE = block(BurntLandModBlocks.TALL_MASALAGE);
	public static final RegistryObject<Item> ANTI_LYBB = REGISTRY.register("anti_lybb", () -> new AntiLybbItem());
	public static final RegistryObject<Item> COLLECTOR_SPAWN_EGG = REGISTRY.register("collector_spawn_egg", () -> new ForgeSpawnEggItem(BurntLandModEntities.COLLECTOR, -16777216, -13369600, new Item.Properties()));
	public static final RegistryObject<Item> ABSOLONCROSS = REGISTRY.register("absoloncross", () -> new AbsoloncrossItem());
	public static final RegistryObject<Item> TRUEFORMTHECOLLECTOR_SPAWN_EGG = REGISTRY.register("trueformthecollector_spawn_egg", () -> new ForgeSpawnEggItem(BurntLandModEntities.TRUEFORMTHECOLLECTOR, -16777165, -6684724, new Item.Properties()));
	public static final RegistryObject<Item> WANDER_SPAWN_EGG = REGISTRY.register("wander_spawn_egg", () -> new ForgeSpawnEggItem(BurntLandModEntities.WANDER, -10092544, -6711040, new Item.Properties()));
	public static final RegistryObject<Item> RUSTED_IRON_BLOCKS = block(BurntLandModBlocks.RUSTED_IRON_BLOCKS);
	public static final RegistryObject<Item> RUSTED_CHAIN = block(BurntLandModBlocks.RUSTED_CHAIN);
	public static final RegistryObject<Item> SOOTY_BRICKS = block(BurntLandModBlocks.SOOTY_BRICKS);
	public static final RegistryObject<Item> ANCIENT_BANNER = block(BurntLandModBlocks.ANCIENT_BANNER);
	public static final RegistryObject<Item> WAR_DEBRIS = block(BurntLandModBlocks.WAR_DEBRIS);
	public static final RegistryObject<Item> SKULL_PILE = block(BurntLandModBlocks.SKULL_PILE);
	public static final RegistryObject<Item> EMPTY_CAGE = block(BurntLandModBlocks.EMPTY_CAGE);
	public static final RegistryObject<Item> ANCIENT_POT = block(BurntLandModBlocks.ANCIENT_POT);
	public static final RegistryObject<Item> PITCH_BARREL = block(BurntLandModBlocks.PITCH_BARREL);
	public static final RegistryObject<Item> BROKEN_WHEEL = block(BurntLandModBlocks.BROKEN_WHEEL);
	public static final RegistryObject<Item> LANTERNWITHCAPE = block(BurntLandModBlocks.LANTERNWITHCAPE);
	public static final RegistryObject<Item> ANCIENT_BANNERSTAND = block(BurntLandModBlocks.ANCIENT_BANNERSTAND);
	public static final RegistryObject<Item> MELTED_CANDLE = block(BurntLandModBlocks.MELTED_CANDLE);
	public static final RegistryObject<Item> TORN_TARGET = block(BurntLandModBlocks.TORN_TARGET);
	public static final RegistryObject<Item> ASH_SACK = block(BurntLandModBlocks.ASH_SACK);
	public static final RegistryObject<Item> CHARRED_ROOTS = block(BurntLandModBlocks.CHARRED_ROOTS);
	public static final RegistryObject<Item> WALL_SHACKLES = block(BurntLandModBlocks.WALL_SHACKLES);
	public static final RegistryObject<Item> WOODEN_CROSS = block(BurntLandModBlocks.WOODEN_CROSS);
	public static final RegistryObject<Item> RUSTY_GRATE = block(BurntLandModBlocks.RUSTY_GRATE);
	public static final RegistryObject<Item> EXTINGUISHED_TORCH = block(BurntLandModBlocks.EXTINGUISHED_TORCH);
	public static final RegistryObject<Item> ROTTING_CRATE = block(BurntLandModBlocks.ROTTING_CRATE);
	public static final RegistryObject<Item> BOARDED_WINDOW = block(BurntLandModBlocks.BOARDED_WINDOW);
	public static final RegistryObject<Item> TOPPLED_QUIVER = block(BurntLandModBlocks.TOPPLED_QUIVER);
	public static final RegistryObject<Item> FIREWOOD_STACK = block(BurntLandModBlocks.FIREWOOD_STACK);
	public static final RegistryObject<Item> INK_POT_QUILL = block(BurntLandModBlocks.INK_POT_QUILL);
	public static final RegistryObject<Item> WOODEN_BUCKET = block(BurntLandModBlocks.WOODEN_BUCKET);
	public static final RegistryObject<Item> ASHLAYER = block(BurntLandModBlocks.ASHLAYER);

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}

	private static RegistryObject<Item> doubleBlock(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
	}
}
