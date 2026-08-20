package com.lion.nyctophobia.config;

import java.util.List;

public class BiomeConfigEntry {
    public final boolean canGenerate;
    public final List<String> replaceableBiomes;

    private BiomeConfigEntry(boolean canGenerate, List<String> replaceableBiomes) {
        this.canGenerate = canGenerate;
        this.replaceableBiomes = replaceableBiomes;
    }

    public static BiomeConfigEntry of(List<String> replaceableBiomes) {
        return new BiomeConfigEntry(true, replaceableBiomes);
    }

    public static BiomeConfigEntry of(boolean canGenerate, List<String> replaceableBiomes) {
        return new BiomeConfigEntry(canGenerate, replaceableBiomes);
    }
}
