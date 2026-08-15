package com.lion.nyctophobia.forge.client;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.client.NFog;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/// Forge counterpart to the Fabric WorldRendererMixin. The event has to be cancelled, otherwise
/// Forge discards the plane distances set here.
@Mod.EventBusSubscriber(modid = Nyctophobia.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NyctophobiaForgeClient {

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
        NFog.FogPlanes planes = NFog.compute(MinecraftClient.getInstance(), event.getCamera(), event.getFarPlaneDistance());
        if (planes == null) {
            return;
        }

        event.setNearPlaneDistance(planes.near());
        event.setFarPlaneDistance(planes.far());
        event.setCanceled(true);
    }
}
