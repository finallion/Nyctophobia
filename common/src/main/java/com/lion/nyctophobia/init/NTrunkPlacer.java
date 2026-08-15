package com.lion.nyctophobia.init;

import com.lion.nyctophobia.platform.RegistryHelper;
import com.lion.nyctophobia.world.trunk.DeepDarkOakTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.function.Supplier;

public class NTrunkPlacer {

    public static final Supplier<TrunkPlacerType<DeepDarkOakTrunkPlacer>> DEEP_DARK_OAK_TRUNK_PLACER =
            RegistryHelper.registerTrunkPlacerType("deep_dark_oak_trunk_placer", DeepDarkOakTrunkPlacer.CODEC);

    public static void init() {}
}
