package com.lion.nyctophobia.world.features;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

public abstract class NFeature<C> implements Feature {
    protected final C config;

    protected NFeature(C config) {
        this.config = config;
    }

    public C config() {
        return config;
    }

    @Override
    public final boolean place(WorldGenLevel level, ChunkGenerator generator, RandomSource random, BlockPos origin) {
        return place(new NPlaceContext<>(level, generator, random, origin, config));
    }

    protected abstract boolean place(NPlaceContext<C> context);
}
