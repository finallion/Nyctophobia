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

/// Runs once per frame, after vanilla has filled the FogData and right before it is uploaded, so the
/// overrides in NFog win. The injection point is the part that moves between MC versions; the
/// behaviour itself lives in NFog.
///
/// The render distance planes are the ones overridden: they are the cylindrical terrain fog the
/// earlier versions hooked, and the shader lets the closer of the two plane pairs win, so the
/// environmental haze vanilla computes alongside never reaches past them.
@Mixin(FogRenderer.class)
public class FogRendererMixin {

    @Inject(
            method = "applyFog(Lnet/minecraft/client/render/Camera;IZLnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;getDevice()Lcom/mojang/blaze3d/systems/GpuDevice;"
            )
    )
    private void nyctophobia$applyBiomeFog(Camera camera, int viewDistance, boolean thick, RenderTickCounter tickCounter,
                                           float skyDarkness, ClientWorld world, CallbackInfoReturnable<Vector4f> cir,
                                           @Local FogData fogData) {
        NFog.FogPlanes planes = NFog.compute(MinecraftClient.getInstance(), camera, viewDistance * 16.0F,
                fogData.renderDistanceStart, fogData.renderDistanceEnd);
        if (planes == null) {
            return;
        }

        fogData.renderDistanceStart = planes.near();
        fogData.renderDistanceEnd = planes.far();
    }
}
