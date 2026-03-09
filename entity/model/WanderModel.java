package net.com.burntland.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.com.burntland.entity.WanderEntity;

public class WanderModel extends GeoModel<WanderEntity> {
	@Override
	public ResourceLocation getAnimationResource(WanderEntity entity) {
		return ResourceLocation.parse("burntland:animations/wander.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(WanderEntity entity) {
		return ResourceLocation.parse("burntland:geo/wander.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(WanderEntity entity) {
		return ResourceLocation.parse("burntland:textures/entities/" + entity.getTexture() + ".png");
	}

}
