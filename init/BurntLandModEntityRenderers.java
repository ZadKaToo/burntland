
package net.com.burntland.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.com.burntland.client.renderer.WanderRenderer;
import net.com.burntland.client.renderer.TrueformthecollectorRenderer;
import net.com.burntland.client.renderer.SoulRenderer;
import net.com.burntland.client.renderer.SlaveRenderer;
import net.com.burntland.client.renderer.ForsinRenderer;
import net.com.burntland.client.renderer.ExecutessplusRenderer;
import net.com.burntland.client.renderer.CollectorRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BurntLandModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BurntLandModEntities.CARNIFEX.get(), ExecutessplusRenderer::new);
		event.registerEntityRenderer(BurntLandModEntities.SOUL_THRALLS.get(), SlaveRenderer::new);
		event.registerEntityRenderer(BurntLandModEntities.SPIRIT_VESSELS.get(), SoulRenderer::new);
		event.registerEntityRenderer(BurntLandModEntities.ABSOLON.get(), ForsinRenderer::new);
		event.registerEntityRenderer(BurntLandModEntities.COLLECTOR.get(), CollectorRenderer::new);
		event.registerEntityRenderer(BurntLandModEntities.TRUEFORMTHECOLLECTOR.get(), TrueformthecollectorRenderer::new);
		event.registerEntityRenderer(BurntLandModEntities.WANDER.get(), WanderRenderer::new);
	}
}
