package com.lion.nyctophobia.data;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.PlacedFeature;

/// The bootstrap context the worldgen populators write into.
///
/// Every reference is resolved through the builder's own lookups, so it is bound at the time it is
/// serialised. A reference made up outside a bootstrap is unbound, and the 1.21 serialiser rejects
/// it as "not valid in current registry set" instead of writing the file.
public final class NRegistryContext<T> {

    private final Registerable<T> context;

    public NRegistryContext(Registerable<T> context) {
        this.context = context;
    }

    public RegistryEntry<T> add(RegistryKey<T> key, T value) {
        return context.register(key, value);
    }

    public <R> RegistryEntry<R> ref(RegistryKey<R> key) {
        return context.getRegistryLookup(key.getRegistryRef()).getOrThrow(key);
    }

    public RegistryEntryLookup<PlacedFeature> placedFeatures() {
        return context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
    }

    public RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers() {
        return context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);
    }
}
