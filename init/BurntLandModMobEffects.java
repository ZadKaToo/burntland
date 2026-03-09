

package net.com.burntland.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.com.burntland.potion.LBYYMobEffect;
import net.com.burntland.BurntLandMod;

public class BurntLandModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BurntLandMod.MODID);
	public static final RegistryObject<MobEffect> LBYY = REGISTRY.register("lbyy", () -> new LBYYMobEffect());
}
