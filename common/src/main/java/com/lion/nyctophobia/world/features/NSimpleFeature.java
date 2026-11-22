package com.lion.nyctophobia.world.features;

import com.mojang.datafixers.util.Unit;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.levelgen.feature.Feature;

public abstract class NSimpleFeature extends NFeature<Unit> {
    private final MapCodec<? extends Feature> codec = MapCodec.unit(this);

    protected NSimpleFeature() {
        super(Unit.INSTANCE);
    }

    @Override
    public MapCodec<? extends Feature> codec() {
        return codec;
    }
}
