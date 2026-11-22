package com.lion.nyctophobia.platform.neoforge;

import com.lion.nyctophobia.Nyctophobia;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RegistryHelperImpl {
    public static final DeferredRegister<MapCodec<? extends Feature>> FEATURE_TYPES =
            DeferredRegister.create(Registries.FEATURE_TYPE, Nyctophobia.MOD_ID);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Nyctophobia.MOD_ID);
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, Nyctophobia.MOD_ID);

    public static Supplier<MapCodec<? extends Feature>> registerFeatureType(
            String name,
            Supplier<MapCodec<? extends Feature>> codec
    ) {
        return FEATURE_TYPES.register(name, codec);
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
