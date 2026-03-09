package net.com.burntland.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.com.burntland.entity.ExecutessplusEntity;

public class ExecutessplusModel extends GeoModel<ExecutessplusEntity> {
	@Override
	public ResourceLocation getAnimationResource(ExecutessplusEntity entity) {
		return ResourceLocation.parse("burntland:animations/executess.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ExecutessplusEntity entity) {
		return ResourceLocation.parse("burntland:geo/executess.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ExecutessplusEntity entity) {
		return ResourceLocation.parse("burntland:textures/entities/" + entity.getTexture() + ".png");
	}

}
