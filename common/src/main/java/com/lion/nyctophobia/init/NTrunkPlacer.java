package com.lion.nyctophobia.init;

import com.lion.nyctophobia.platform.RegistryHelper;
import com.lion.nyctophobia.world.trunk.DeepDarkOakTrunkPlacer;
import java.util.function.Supplier;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class NTrunkPlacer {

    public static final Supplier<TrunkPlacerType<DeepDarkOakTrunkPlacer>> DEEP_DARK_OAK_TRUNK_PLACER =
            RegistryHelper.registerTrunkPlacerType("deep_dark_oak_trunk_placer", DeepDarkOakTrunkPlacer.CODEC);

    public static void init() {}
}
