package com.lion.nyctophobia.neoforge.client;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.client.NFog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;

@EventBusSubscriber(modid = Nyctophobia.MOD_ID, value = Dist.CLIENT)
public class NyctophobiaNeoForgeClient {

    @SubscribeEvent
    public static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal(Nyctophobia.MOD_ID)
                .then(Commands.literal("fog")
                        .then(Commands.literal("on").executes(context -> set(context.getSource(), true)))
                        .then(Commands.literal("off").executes(context -> set(context.getSource(), false)))
                        .executes(context -> {
                            NFog.toggle();
                            return report(context.getSource());
                        })));
    }

    private static int set(CommandSourceStack source, boolean value) {
        NFog.setEnabled(value);
        return report(source);
    }

    private static int report(CommandSourceStack source) {
        source.sendSuccess(NFog::feedback, false);
        return 1;
    }

    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        FogData fogData = event.getFogData();
        NFog.FogPlanes planes = NFog.compute(
                Minecraft.getInstance(),
                event.getCamera(),
                Minecraft.getInstance().options.getEffectiveRenderDistance() * 16.0F,
                fogData.renderDistanceStart,
                fogData.renderDistanceEnd
        );

        if (planes == null) {
            return;
        }

        fogData.renderDistanceStart = planes.near();
        fogData.renderDistanceEnd = planes.far();
        // Sky and clouds read their own planes.
        fogData.skyEnd = Math.min(fogData.skyEnd, planes.far());
        fogData.cloudEnd = Math.min(fogData.cloudEnd, planes.far());
    }
}
