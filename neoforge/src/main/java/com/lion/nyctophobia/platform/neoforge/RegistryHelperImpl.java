package com.lion.nyctophobia.platform.neoforge;

import com.lion.nyctophobia.Nyctophobia;
import com.mojang.serialization.MapCodec;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RegistryHelperImpl {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(RegistryKeys.FEATURE, Nyctophobia.MOD_ID);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES =
            DeferredRegister.create(RegistryKeys.TRUNK_PLACER_TYPE, Nyctophobia.MOD_ID);
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(RegistryKeys.STRUCTURE_TYPE, Nyctophobia.MOD_ID);

    /// The registers hold wildcard types. The supplier builds exactly that type, so the cast is safe.
    @SuppressWarnings("unchecked")
    public static <C extends FeatureConfig, F extends Feature<C>> Supplier<F> registerFeature(
            String name,
            Supplier<F> feature
    ) {
        return (Supplier<F>) (Supplier<?>) FEATURES.register(name, feature::get);
    }

    @SuppressWarnings("unchecked")
    public static <P extends TrunkPlacer> Supplier<TrunkPlacerType<P>> registerTrunkPlacerType(
            String name,
            MapCodec<P> codec
    ) {
        return (Supplier<TrunkPlacerType<P>>) (Supplier<?>) TRUNK_PLACER_TYPES.register(name, () -> new TrunkPlacerType<>(codec));
    }

    @SuppressWarnings("unchecked")
    public static <S extends Structure> Supplier<StructureType<S>> registerStructureType(
            String name,
            MapCodec<S> codec
    ) {
        // The explicit type gives the compiler a target for S.
        Supplier<StructureType<S>> factory = () -> () -> codec;
        return (Supplier<StructureType<S>>) (Supplier<?>) STRUCTURE_TYPES.register(name, factory);
    }
}
