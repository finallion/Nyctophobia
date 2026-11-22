package com.lion.nyctophobia.world.noise;

import com.lion.nyctophobia.Nyctophobia;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;

public class NNoiseParameters {

    public static final RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> HAUNTED_FOREST_NOISE = registerNoiseParameter("haunted_forest_noise");

    private static RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> registerNoiseParameter(String id) {
        return RegistryKey.of(RegistryKeys.NOISE_PARAMETERS, Identifier.of(Nyctophobia.MOD_ID, id));
    }
}
