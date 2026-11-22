package com.lion.nyctophobia.data;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class NRegistryContext<T> {

    private final BootstrapContext<T> context;

    public NRegistryContext(BootstrapContext<T> context) {
        this.context = context;
    }

    public Holder<T> add(ResourceKey<T> key, T value) {
        return context.register(key, value);
    }

    public <R> Holder<R> ref(ResourceKey<R> key) {
        return context.lookup(key.registryKey()).getOrThrow(key);
    }

    public HolderGetter<PlacedFeature> placedFeatures() {
        return context.lookup(Registries.PLACED_FEATURE);
    }

    public HolderGetter<Biome> biomes() {
        return context.lookup(Registries.BIOME);
    }

    public HolderGetter<WorldCarver> configuredCarvers() {
        return context.lookup(Registries.CARVER);
    }
}
