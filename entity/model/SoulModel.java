package net.com.burntland.entity.model;

import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.constant.DataTickets;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;

import net.com.burntland.entity.SoulEntity;

public class SoulModel extends GeoModel<SoulEntity> {
	@Override
	public ResourceLocation getAnimationResource(SoulEntity entity) {
		return ResourceLocation.parse("burntland:animations/soul.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(SoulEntity entity) {
		return ResourceLocation.parse("burntland:geo/soul.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(SoulEntity entity) {
		return ResourceLocation.parse("burntland:textures/entities/" + entity.getTexture() + ".png");
	}

	@Override
	public void setCustomAnimations(SoulEntity animatable, long instanceId, AnimationState animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("bone7/bone6");
		if (head != null) {
			EntityModelData entityData = (EntityModelData) animationState.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}

	}
}
