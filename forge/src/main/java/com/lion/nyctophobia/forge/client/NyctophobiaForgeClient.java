package com.lion.nyctophobia.forge.client;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.client.NFog;
import net.minecraft.client.MinecraftClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/// Forge counterpart to the Fabric WorldRendererMixin. The event has to be cancelled, otherwise
/// Forge discards the plane distances set here.
@Mod.EventBusSubscriber(modid = Nyctophobia.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NyctophobiaForgeClient {

    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        NFog.FogPlanes planes = NFog.compute(MinecraftClient.getInstance(), event.getCamera(), event.getFarPlaneDistance());
        if (planes == null) {
            return;
        }

        event.setNearPlaneDistance(planes.near());
        event.setFarPlaneDistance(planes.far());
        event.setCanceled(true);
    }
}
