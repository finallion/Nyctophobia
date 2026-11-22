package com.lion.nyctophobia.platform.fabric;

import com.lion.nyctophobia.Nyctophobia;
import com.mojang.serialization.Codec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.function.Supplier;

public class RegistryHelperImpl {

    public static <C extends FeatureConfig, F extends Feature<C>> Supplier<F> registerFeature(
            String name,
            Supplier<F> feature
    ) {
        F registered = Registry.register(Registries.FEATURE, new Identifier(Nyctophobia.MOD_ID, name), feature.get());
        return () -> registered;
    }

    public static <P extends TrunkPlacer> Supplier<TrunkPlacerType<P>> registerTrunkPlacerType(
            String name,
            Codec<P> codec
    ) {
        TrunkPlacerType<P> registered = Registry.register(
                Registries.TRUNK_PLACER_TYPE,
                new Identifier(Nyctophobia.MOD_ID, name),
                new TrunkPlacerType<>(codec)
        );
        return () -> registered;
    }

    public static <S extends Structure> Supplier<StructureType<S>> registerStructureType(String name, Codec<S> codec) {
        StructureType<S> registered = Registry.register(
                Registries.STRUCTURE_TYPE,
                new Identifier(Nyctophobia.MOD_ID, name),
                () -> codec
        );
        return () -> registered;
    }
}
