package com.lion.nyctophobia.mixin.fabric;

import com.lion.nyctophobia.client.NFog;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/// The shader uses the closer plane pair. The render distance planes set the terrain fog.
@Mixin(FogRenderer.class)
public class FogRendererMixin {

    @Inject(
            method = "setupFog(Lnet/minecraft/client/Camera;ILnet/minecraft/client/DeltaTracker;FLnet/minecraft/client/multiplayer/ClientLevel;)Lnet/minecraft/client/renderer/fog/FogData;",
            at = @At("RETURN")
    )
    private void nyctophobia$applyBiomeFog(
            Camera camera,
            int viewDistance,
            DeltaTracker tickCounter,
            float skyDarkness,
            ClientLevel world,
            CallbackInfoReturnable<FogData> cir
    ) {
        FogData fogData = cir.getReturnValue();
        NFog.FogPlanes planes = NFog.compute(
                Minecraft.getInstance(),
                camera,
                viewDistance * 16.0F,
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
