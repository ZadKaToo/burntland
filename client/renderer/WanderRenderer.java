
package net.com.burntland.client.renderer;

import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.com.burntland.entity.model.WanderModel;
import net.com.burntland.entity.WanderEntity;
import net.minecraft.core.BlockPos;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class WanderRenderer extends GeoEntityRenderer<WanderEntity> {
	public WanderRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new WanderModel());
		this.shadowRadius = 0.5f;
	}

	@Override
	public RenderType getRenderType(WanderEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, WanderEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
			float blue, float alpha) {
		float scale = 1f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	protected float getDeathMaxRotation(WanderEntity entityLivingBaseIn) {
		return 0.0F;
	}

	// --- วางโค้ดตรงนี้ (ต่อจาก Constructor) ---
	@Override
    protected int getBlockLightLevel(WanderEntity entity, BlockPos pos) {
        // บังคับให้เกมนับว่าตรงที่บอสยืนอยู่ มีแสงระดับ 15 (สว่างสุด) เสมอ
        return 10;
    }
}
