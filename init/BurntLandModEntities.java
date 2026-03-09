

package net.com.burntland.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.com.burntland.entity.WanderEntity;
import net.com.burntland.entity.TrueformthecollectorEntity;
import net.com.burntland.entity.SoulEntity;
import net.com.burntland.entity.SlaveEntity;
import net.com.burntland.entity.ForsinEntity;
import net.com.burntland.entity.ExecutessplusEntity;
import net.com.burntland.entity.CollectorEntity;
import net.com.burntland.BurntLandMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BurntLandModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BurntLandMod.MODID);
	public static final RegistryObject<EntityType<ExecutessplusEntity>> CARNIFEX = register("carnifex", EntityType.Builder.<ExecutessplusEntity>of(ExecutessplusEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ExecutessplusEntity::new).fireImmune().sized(1.5f, 5f));
	public static final RegistryObject<EntityType<SlaveEntity>> SOUL_THRALLS = register("soul_thralls",
			EntityType.Builder.<SlaveEntity>of(SlaveEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SlaveEntity::new).fireImmune().sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<SoulEntity>> SPIRIT_VESSELS = register("spirit_vessels",
			EntityType.Builder.<SoulEntity>of(SoulEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SoulEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<ForsinEntity>> ABSOLON = register("absolon",
			EntityType.Builder.<ForsinEntity>of(ForsinEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ForsinEntity::new).fireImmune().sized(1.5f, 3f));
	public static final RegistryObject<EntityType<CollectorEntity>> COLLECTOR = register("collector",
			EntityType.Builder.<CollectorEntity>of(CollectorEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CollectorEntity::new).fireImmune().sized(1f, 1.8f));
	public static final RegistryObject<EntityType<TrueformthecollectorEntity>> TRUEFORMTHECOLLECTOR = register("trueformthecollector", EntityType.Builder.<TrueformthecollectorEntity>of(TrueformthecollectorEntity::new, MobCategory.CREATURE)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TrueformthecollectorEntity::new).fireImmune().sized(1.5f, 3.5f));
	public static final RegistryObject<EntityType<WanderEntity>> WANDER = register("wander",
			EntityType.Builder.<WanderEntity>of(WanderEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(WanderEntity::new).fireImmune().sized(0.6f, 1.8f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			ExecutessplusEntity.init();
			SlaveEntity.init();
			SoulEntity.init();
			ForsinEntity.init();
			CollectorEntity.init();
			TrueformthecollectorEntity.init();
			WanderEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(CARNIFEX.get(), ExecutessplusEntity.createAttributes().build());
		event.put(SOUL_THRALLS.get(), SlaveEntity.createAttributes().build());
		event.put(SPIRIT_VESSELS.get(), SoulEntity.createAttributes().build());
		event.put(ABSOLON.get(), ForsinEntity.createAttributes().build());
		event.put(COLLECTOR.get(), CollectorEntity.createAttributes().build());
		event.put(TRUEFORMTHECOLLECTOR.get(), TrueformthecollectorEntity.createAttributes().build());
		event.put(WANDER.get(), WanderEntity.createAttributes().build());
	}
}
