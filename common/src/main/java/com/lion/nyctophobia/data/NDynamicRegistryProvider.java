package com.lion.nyctophobia.data;

import com.lion.nyctophobia.init.NBiomes;
import com.lion.nyctophobia.init.NConfiguredFeatures;
import com.lion.nyctophobia.init.NPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class NDynamicRegistryProvider extends FabricDynamicRegistryProvider {

    protected NDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        NConfiguredFeatures.populate(entries);
        NPlacedFeatures.populate(entries);
        NBiomes.populate(entries);
    }

    @Override
    public String getName() {
        return "Nyctophobia";
    }
}
