package com.lion.nyctophobia.platform;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.function.Supplier;

/// The loader fills its registers late, so every method returns a Supplier.
public class RegistryHelper {

    @ExpectPlatform
    public static <C extends FeatureConfig, F extends Feature<C>> Supplier<F> registerFeature(
            String name,
            Supplier<F> feature
    ) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <P extends TrunkPlacer> Supplier<TrunkPlacerType<P>> registerTrunkPlacerType(
            String name,
            MapCodec<P> codec
    ) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <S extends Structure> Supplier<StructureType<S>> registerStructureType(
            String name,
            MapCodec<S> codec
    ) {
        throw new AssertionError();
    }
}
