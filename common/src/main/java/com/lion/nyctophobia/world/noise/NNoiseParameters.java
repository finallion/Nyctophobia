package com.lion.nyctophobia.world.noise;

import com.lion.nyctophobia.Nyctophobia;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class NNoiseParameters {

    public static final ResourceKey<NormalNoise.NoiseParameters> HAUNTED_FOREST_NOISE = registerNoiseParameter("haunted_forest_noise");


    private static ResourceKey<NormalNoise.NoiseParameters> registerNoiseParameter(String id) {
        return ResourceKey.create(Registries.NOISE, Identifier.fromNamespaceAndPath(Nyctophobia.MOD_ID, id));
    }
}
