package com.lion.nyctophobia.forge.client;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.client.NFog;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.BlockPos;
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
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) {
            return;
        }

        Camera camera = event.getCamera();
        BlockPos pos = camera.getBlockPos();
        String biomePath = client.world.getBiome(pos).getKey()
                .map(key -> key.getValue().getPath())
                .orElse("");

        float[] planes = NFog.compute(camera, event.getFarPlaneDistance(), pos, biomePath);
        if (planes == null) {
            return;
        }

        event.setNearPlaneDistance(planes[0]);
        event.setFarPlaneDistance(planes[1]);
        event.setCanceled(true);
    }
}
