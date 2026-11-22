package com.lion.nyctophobia.platform.forge;

import com.lion.nyctophobia.Nyctophobia;
import com.mojang.serialization.Codec;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class RegistryHelperImpl {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, Nyctophobia.MOD_ID);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES =
            DeferredRegister.create(RegistryKeys.TRUNK_PLACER_TYPE, Nyctophobia.MOD_ID);
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(RegistryKeys.STRUCTURE_TYPE, Nyctophobia.MOD_ID);

    @SuppressWarnings("unchecked")
    public static <C extends FeatureConfig, F extends Feature<C>> Supplier<F> registerFeature(
            String name,
            Supplier<F> feature
    ) {
        return (Supplier<F>) FEATURES.register(name, feature::get);
    }

    public static <P extends TrunkPlacer> Supplier<TrunkPlacerType<P>> registerTrunkPlacerType(
            String name,
            Codec<P> codec
    ) {
        Supplier<TrunkPlacerType<P>> registered = TRUNK_PLACER_TYPES.register(name, () -> new TrunkPlacerType<>(codec));
        return registered;
    }

    public static <S extends Structure> Supplier<StructureType<S>> registerStructureType(String name, Codec<S> codec) {
        Supplier<StructureType<S>> registered = STRUCTURE_TYPES.register(name, () -> () -> codec);
        return registered;
    }
}
