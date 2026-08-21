package com.lion.nyctophobia.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/// Writes what the bootstraps in NDataGen built. addAll keeps only this mod's namespace, so the
/// vanilla entries the lookups also hold never reach the output.
public class NDynamicRegistryProvider extends FabricDynamicRegistryProvider {

    protected NDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.BIOME));
    }

    @Override
    public String getName() {
        return "Nyctophobia";
    }
}
