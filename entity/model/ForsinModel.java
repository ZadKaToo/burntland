package net.com.burntland.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.com.burntland.entity.ForsinEntity;

public class ForsinModel extends GeoModel<ForsinEntity> {
	@Override
	public ResourceLocation getAnimationResource(ForsinEntity entity) {
		return ResourceLocation.parse("burntland:animations/forsinnn.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ForsinEntity entity) {
		return ResourceLocation.parse("burntland:geo/forsinnn.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ForsinEntity entity) {
		return ResourceLocation.parse("burntland:textures/entities/" + entity.getTexture() + ".png");
	}

}
