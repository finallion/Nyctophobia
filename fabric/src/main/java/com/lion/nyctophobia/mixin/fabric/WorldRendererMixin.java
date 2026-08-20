package com.lion.nyctophobia.mixin.fabric;

import com.lion.nyctophobia.client.NFog;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.WorldRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/// Runs right after vanilla has set the terrain fog so the overrides in NFog win. The injection
/// point is the part that moves between MC versions; the behaviour itself lives in NFog.
///
/// Ordinal 0 is the terrain pass: vanilla calls applyFog once inside render, and the sky pass lives
/// in the lambda handed to renderSky, which is a method of its own and not counted here.
@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Final
    @Shadow
    private MinecraftClient client;

    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/BackgroundRenderer;applyFog(Lnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/BackgroundRenderer$FogType;FZF)V",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    public void nyctophobia$applyBiomeFog(RenderTickCounter tickCounter, boolean renderBlockOutline, Camera camera,
                                          GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager,
                                          Matrix4f positionMatrix, Matrix4f projectionMatrix, CallbackInfo ci) {
        NFog.FogPlanes planes = NFog.compute(this.client, camera, gameRenderer.getViewDistance());
        if (planes == null) {
            return;
        }

        RenderSystem.setShaderFogStart(planes.near());
        RenderSystem.setShaderFogEnd(planes.far());
    }
}
