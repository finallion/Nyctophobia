package com.lion.nyctophobia.world.features;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;

public record NPlaceContext<C>(WorldGenLevel level, ChunkGenerator generator, RandomSource random, BlockPos origin, C config) {}
