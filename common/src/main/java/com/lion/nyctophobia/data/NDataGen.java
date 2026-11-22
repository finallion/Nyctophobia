package com.lion.nyctophobia.data;

import com.lion.nyctophobia.init.NBiomes;
import com.lion.nyctophobia.init.NConfiguredFeatures;
import com.lion.nyctophobia.init.NPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class NDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        FabricDataGenerator.Pack pack = dataGenerator.createPack();
        pack.addProvider(NDynamicRegistryProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder builder) {
        builder.add(Registries.CONFIGURED_FEATURE, context -> {
            NRegistryContext<ConfiguredFeature<?, ?>> entries = new NRegistryContext<>(context);
            NConfiguredFeatures.populate(entries);
            NPlacedFeatures.populateSelectors(entries);
        });
        builder.add(Registries.PLACED_FEATURE, context -> NPlacedFeatures.populate(new NRegistryContext<>(context)));
        builder.add(Registries.BIOME, context -> NBiomes.populate(new NRegistryContext<>(context)));
    }
}
