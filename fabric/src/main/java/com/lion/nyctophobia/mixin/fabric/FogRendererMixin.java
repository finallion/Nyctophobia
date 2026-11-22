package com.lion.nyctophobia.mixin.fabric;

import com.lion.nyctophobia.client.NFog;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/// The shader uses the closer plane pair. The render distance planes set the terrain fog.
/// The result never passes the vanilla planes.
@Mixin(FogRenderer.class)
public class FogRendererMixin {

    @Inject(
            method = "applyFog(Lnet/minecraft/client/render/Camera;IZLnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;getDevice()Lcom/mojang/blaze3d/systems/GpuDevice;"
            )
    )
    private void nyctophobia$applyBiomeFog(
            Camera camera,
            int viewDistance,
            boolean thick,
            RenderTickCounter tickCounter,
            float skyDarkness,
            ClientWorld world,
            CallbackInfoReturnable<Vector4f> cir,
            @Local FogData fogData
    ) {
        NFog.FogPlanes planes = NFog.compute(
                MinecraftClient.getInstance(),
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
