package net.com.burntland.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.com.burntland.entity.CollectorEntity;

public class CollectorModel extends GeoModel<CollectorEntity> {
	@Override
	public ResourceLocation getAnimationResource(CollectorEntity entity) {
		return ResourceLocation.parse("burntland:animations/geckolib_entity2.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(CollectorEntity entity) {
		return ResourceLocation.parse("burntland:geo/geckolib_entity2.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(CollectorEntity entity) {
		return ResourceLocation.parse("burntland:textures/entities/" + entity.getTexture() + ".png");
	}

}
