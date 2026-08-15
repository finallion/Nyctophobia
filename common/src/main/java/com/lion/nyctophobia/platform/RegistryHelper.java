package com.lion.nyctophobia.platform;

import com.mojang.serialization.Codec;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.function.Supplier;

/// Every method returns a Supplier because Forge fills its DeferredRegisters after this class is
/// touched; reading the registry object eagerly there yields null.
public class RegistryHelper {

    @ExpectPlatform
    public static <C extends FeatureConfig, F extends Feature<C>> Supplier<F> registerFeature(String name, Supplier<F> feature) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <P extends TrunkPlacer> Supplier<TrunkPlacerType<P>> registerTrunkPlacerType(String name, Codec<P> codec) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <S extends Structure> Supplier<StructureType<S>> registerStructureType(String name, Codec<S> codec) {
        throw new AssertionError();
    }
}
