package com.lion.nyctophobia.forge.client;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.client.NFog;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/// Cancel the event, or the loader drops the planes.
@Mod.EventBusSubscriber(modid = Nyctophobia.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NyctophobiaForgeClient {

    @SubscribeEvent
    public static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(CommandManager.literal(Nyctophobia.MOD_ID)
                .then(CommandManager.literal("fog")
                        .then(CommandManager.literal("on").executes(context -> set(context.getSource(), true)))
                        .then(CommandManager.literal("off").executes(context -> set(context.getSource(), false)))
                        .executes(context -> {
                            NFog.toggle();
                            return report(context.getSource());
                        })));
    }

    private static int set(ServerCommandSource source, boolean value) {
        NFog.setEnabled(value);
        return report(source);
    }

    private static int report(ServerCommandSource source) {
        source.sendFeedback(NFog::feedback, false);
        return 1;
    }

    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        // The hook fires per fog pass. Handle the terrain pass only.
        if (event.getMode() != BackgroundRenderer.FogType.FOG_TERRAIN) {
            return;
        }

        // Pass the raw view distance. NFog derives the planes from it like vanilla.
        NFog.FogPlanes planes = NFog.compute(
                MinecraftClient.getInstance(),
                event.getCamera(),
                event.getRenderer().getViewDistance(),
                event.getNearPlaneDistance(),
                event.getFarPlaneDistance()
        );

        if (planes == null) {
            return;
        }

        event.setNearPlaneDistance(planes.near());
        event.setFarPlaneDistance(planes.far());
        event.setCanceled(true);
    }
}
