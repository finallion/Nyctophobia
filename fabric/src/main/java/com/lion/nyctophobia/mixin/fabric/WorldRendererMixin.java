package com.lion.nyctophobia.mixin.fabric;

import com.lion.nyctophobia.client.NFog;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/// Runs right after vanilla has set the terrain fog so the overrides in NFog win. The injection
/// point is the part that moves between MC versions; the behaviour itself lives in NFog.
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
    public void nyctophobia$applyBiomeFog(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline,
                                          Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager,
                                          Matrix4f positionMatrix, CallbackInfo ci) {
        if (this.client.world == null) {
            return;
        }

        BlockPos pos = camera.getBlockPos();
        String biomePath = this.client.world.getBiome(pos).getKey()
                .map(key -> key.getValue().getPath())
                .orElse("");

        float[] planes = NFog.compute(camera, gameRenderer.getViewDistance(), pos, biomePath);
        if (planes != null) {
            RenderSystem.setShaderFogStart(planes[0]);
            RenderSystem.setShaderFogEnd(planes[1]);
        }
    }
}
