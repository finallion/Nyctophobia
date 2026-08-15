package com.lion.nyctophobia.init;

import com.lion.nyctophobia.platform.RegistryHelper;
import com.lion.nyctophobia.world.structures.MushroomStructure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.function.Supplier;

public class NStructures {

    public static final Supplier<StructureType<MushroomStructure>> MUSHROOM_STRUCTURE =
            RegistryHelper.registerStructureType("mushroom_structure", MushroomStructure.CODEC);

    public static void registerStructureFeatures() {}
}
