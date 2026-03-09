package net.com.burntland.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.com.burntland.entity.SlaveEntity;

public class SlaveModel extends GeoModel<SlaveEntity> {
	@Override
	public ResourceLocation getAnimationResource(SlaveEntity entity) {
		return ResourceLocation.parse("burntland:animations/slave.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(SlaveEntity entity) {
		return ResourceLocation.parse("burntland:geo/slave.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(SlaveEntity entity) {
		return ResourceLocation.parse("burntland:textures/entities/" + entity.getTexture() + ".png");
	}

}
