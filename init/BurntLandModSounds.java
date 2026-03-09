package net.com.burntland.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.com.burntland.BurntLandMod;

public class BurntLandModSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BurntLandMod.MODID);

    public static final RegistryObject<SoundEvent> BEARTH_EXECUTIONER = REGISTRY.register("bearth_executioner", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "bearth_executioner")));
    public static final RegistryObject<SoundEvent> VICE_EXE = REGISTRY.register("vice_exe", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "vice_exe")));
    public static final RegistryObject<SoundEvent> MORE = REGISTRY.register("more", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "more")));
    public static final RegistryObject<SoundEvent> MORE2 = REGISTRY.register("more2", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "more2")));
    public static final RegistryObject<SoundEvent> CHAIN = REGISTRY.register("chain", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "chain")));
    public static final RegistryObject<SoundEvent> SOUL = REGISTRY.register("soul", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "soul")));
    public static final RegistryObject<SoundEvent> AAAA = REGISTRY.register("aaaa", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "aaaa")));
    public static final RegistryObject<SoundEvent> THEME_BURN = REGISTRY.register("theme_burn", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "theme_burn")));
    public static final RegistryObject<SoundEvent> WALKEXE = REGISTRY.register("walkexe", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "walkexe")));
    public static final RegistryObject<SoundEvent> FOOTSTEP_EXE = REGISTRY.register("footstep-exe", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "footstep-exe")));
    public static final RegistryObject<SoundEvent> DROW = REGISTRY.register("drow", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "drow")));
    public static final RegistryObject<SoundEvent> FIRST = REGISTRY.register("first", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "first")));
    public static final RegistryObject<SoundEvent> SECORD = REGISTRY.register("secord", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "secord")));
    public static final RegistryObject<SoundEvent> THIRD = REGISTRY.register("third", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "third")));
    public static final RegistryObject<SoundEvent> CLEAR = REGISTRY.register("clear", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "clear")));
    public static final RegistryObject<SoundEvent> FB = REGISTRY.register("fb", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "fb")));
    public static final RegistryObject<SoundEvent> FT = REGISTRY.register("ft", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "ft")));
    public static final RegistryObject<SoundEvent> FG = REGISTRY.register("fg", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "fg")));
    public static final RegistryObject<SoundEvent> FC = REGISTRY.register("fc", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "fc")));
    public static final RegistryObject<SoundEvent> GA = REGISTRY.register("ga", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "ga")));
    public static final RegistryObject<SoundEvent> GAA = REGISTRY.register("gaa", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "gaa")));
    public static final RegistryObject<SoundEvent> FACEAB = REGISTRY.register("faceab", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "faceab")));
    public static final RegistryObject<SoundEvent> FACESO = REGISTRY.register("faceso", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "faceso")));
    public static final RegistryObject<SoundEvent> METCOLL = REGISTRY.register("metcoll", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "metcoll")));
    public static final RegistryObject<SoundEvent> METSLA = REGISTRY.register("metsla", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "metsla")));
    public static final RegistryObject<SoundEvent> GOTTHEAXE = REGISTRY.register("gottheaxe", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "gottheaxe")));
    public static final RegistryObject<SoundEvent> FACECARNI = REGISTRY.register("facecarni", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "facecarni")));
    public static final RegistryObject<SoundEvent> GOTABSOLONCROSS = REGISTRY.register("gotabsoloncross", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "gotabsoloncross")));
    public static final RegistryObject<SoundEvent> COLLECTLIVE = REGISTRY.register("collectlive", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "collectlive")));
    public static final RegistryObject<SoundEvent> COLLECTHURT = REGISTRY.register("collecthurt", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "collecthurt")));
    public static final RegistryObject<SoundEvent> FORLIVE = REGISTRY.register("forlive", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "forlive")));
    public static final RegistryObject<SoundEvent> FORDIE = REGISTRY.register("fordie", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "fordie")));
    public static final RegistryObject<SoundEvent> FACETHEGEE = REGISTRY.register("facethegee", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("burntland", "facethegee")));
}