
/*
 *	com note: This file will be REGENERATED on each build.
 */
package net.com.burntland.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

import net.minecraft.world.item.ItemStack;

@Mod.EventBusSubscriber
public class BurntLandModFuels {
	@SubscribeEvent
	public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		ItemStack itemstack = event.getItemStack();
		if (itemstack.getItem() == BurntLandModBlocks.BURNT_LOGS.get().asItem())
			event.setBurnTime(800);
		else if (itemstack.getItem() == BurntLandModBlocks.BURNT_PLANKS.get().asItem())
			event.setBurnTime(300);
		else if (itemstack.getItem() == BurntLandModBlocks.ABANDONED_STAIRS.get().asItem())
			event.setBurnTime(300);
		else if (itemstack.getItem() == BurntLandModBlocks.ABANDONED_SLABS.get().asItem())
			event.setBurnTime(300);
		else if (itemstack.getItem() == BurntLandModBlocks.ABANDONED_FENCES.get().asItem())
			event.setBurnTime(300);
		else if (itemstack.getItem() == BurntLandModBlocks.ABANDONED_TRAPDOOR.get().asItem())
			event.setBurnTime(300);
		else if (itemstack.getItem() == BurntLandModBlocks.ABANDONED_DOOR.get().asItem())
			event.setBurnTime(300);
		else if (itemstack.getItem() == BurntLandModBlocks.ABANDONED_FENCEGATE.get().asItem())
			event.setBurnTime(300);
		else if (itemstack.getItem() == BurntLandModBlocks.ABANDONED_PRESSUREPLATE.get().asItem())
			event.setBurnTime(300);
		else if (itemstack.getItem() == BurntLandModBlocks.ABANDONED_BUTTON.get().asItem())
			event.setBurnTime(300);
	}
}
