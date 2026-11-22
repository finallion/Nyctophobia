package com.lion.nyctophobia.mixin.fabric;

import com.lion.nyctophobia.client.NFog;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
    public void nyctophobia$applyBiomeFog(
            MatrixStack matrices,
            float tickDelta,
            long limitTime,
            boolean renderBlockOutline,
            Camera camera,
            GameRenderer gameRenderer,
            LightmapTextureManager lightmapTextureManager,
            Matrix4f positionMatrix,
            CallbackInfo ci
    ) {
        NFog.FogPlanes planes = NFog.compute(
                this.client,
                camera,
                gameRenderer.getViewDistance(),
                RenderSystem.getShaderFogStart(),
                RenderSystem.getShaderFogEnd()
        );

        if (planes == null) {
            return;
        }

        RenderSystem.setShaderFogStart(planes.near());
        RenderSystem.setShaderFogEnd(planes.far());
    }
}
