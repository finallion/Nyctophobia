package com.lion.nyctophobia.neoforge.client;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.client.NFog;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;

/// NeoForge counterpart to the Fabric FogRendererMixin.
@EventBusSubscriber(modid = Nyctophobia.MOD_ID, value = Dist.CLIENT)
public class NyctophobiaNeoForgeClient {

    /// Registered as a client command for the same reason as on Fabric: the fog never leaves the
    /// client, so a server side command could not reach it in multiplayer.
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
        // The event's own plane accessors address the environmental pair. The Fabric mixin overrides
        // the render distance pair, and the fog has to come out the same on both loaders, so this
        // goes through the FogData directly.
        FogData fogData = event.getFogData();
        NFog.FogPlanes planes = NFog.compute(MinecraftClient.getInstance(), event.getCamera(),
                event.getRenderer().getViewDistanceBlocks(), fogData.renderDistanceStart, fogData.renderDistanceEnd);
        if (planes == null) {
            return;
        }

        fogData.renderDistanceStart = planes.near();
        fogData.renderDistanceEnd = planes.far();
        // Sky and clouds read their own planes, so without this the clouds stay visible and the sky
        // keeps its clear colour above a fogged landscape.
        fogData.skyEnd = Math.min(fogData.skyEnd, planes.far());
        fogData.cloudEnd = Math.min(fogData.cloudEnd, planes.far());
    }
}
