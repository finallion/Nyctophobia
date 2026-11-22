package com.lion.nyctophobia.platform.fabric;

import com.lion.nyctophobia.Nyctophobia;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import java.util.function.Supplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class RegistryHelperImpl {

    public static Supplier<MapCodec<? extends Feature>> registerFeatureType(
            String name,
            Supplier<MapCodec<? extends Feature>> codec
    ) {
        MapCodec<? extends Feature> registered = Registry.register(BuiltInRegistries.FEATURE_TYPE, Identifier.fromNamespaceAndPath(Nyctophobia.MOD_ID, name), codec.get());
        return () -> registered;
    }

    public static <P extends TrunkPlacer> Supplier<TrunkPlacerType<P>> registerTrunkPlacerType(
            String name,
            MapCodec<P> codec
    ) {
        TrunkPlacerType<P> registered = Registry.register(
                BuiltInRegistries.TRUNK_PLACER_TYPE,
                Identifier.fromNamespaceAndPath(Nyctophobia.MOD_ID, name),
                new TrunkPlacerType<>(codec)
        );
        return () -> registered;
    }

    public static <S extends Structure> Supplier<StructureType<S>> registerStructureType(
            String name,
            MapCodec<S> codec
    ) {
        StructureType<S> registered = Registry.register(
                BuiltInRegistries.STRUCTURE_TYPE,
                Identifier.fromNamespaceAndPath(Nyctophobia.MOD_ID, name),
                () -> codec
        );
        return () -> registered;
    }
}
