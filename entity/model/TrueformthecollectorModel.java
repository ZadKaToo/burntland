package net.com.burntland.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.com.burntland.entity.TrueformthecollectorEntity;

public class TrueformthecollectorModel extends GeoModel<TrueformthecollectorEntity> {
	@Override
	public ResourceLocation getAnimationResource(TrueformthecollectorEntity entity) {
		return ResourceLocation.parse("burntland:animations/true_form_the_collectioner_back.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(TrueformthecollectorEntity entity) {
		return ResourceLocation.parse("burntland:geo/true_form_the_collectioner_back.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(TrueformthecollectorEntity entity) {
		return ResourceLocation.parse("burntland:textures/entities/" + entity.getTexture() + ".png");
	}

}
