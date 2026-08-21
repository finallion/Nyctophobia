package com.lion.nyctophobia.data;

import com.lion.nyctophobia.init.NBiomes;
import com.lion.nyctophobia.init.NConfiguredFeatures;
import com.lion.nyctophobia.init.NPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class NDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        FabricDataGenerator.Pack pack = dataGenerator.createPack();
        pack.addProvider(NDynamicRegistryProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder builder) {
        builder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, context -> {
            NRegistryContext<ConfiguredFeature<?, ?>> entries = new NRegistryContext<>(context);
            NConfiguredFeatures.populate(entries);
            NPlacedFeatures.populateSelectors(entries);
        });
        builder.addRegistry(RegistryKeys.PLACED_FEATURE, context -> NPlacedFeatures.populate(new NRegistryContext<>(context)));
        builder.addRegistry(RegistryKeys.BIOME, context -> NBiomes.populate(new NRegistryContext<>(context)));
    }
}
