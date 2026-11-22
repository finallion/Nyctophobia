package com.lion.nyctophobia.world.surfacerules;


import com.lion.nyctophobia.init.NBiomes;
import com.lion.nyctophobia.world.noise.NNoiseParameters;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.material.MaterialRules;
import net.minecraft.world.level.levelgen.material.condition.MaterialCondition;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.data.NRegistryContext;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class NSurfaceRules {
    public static final ResourceKey<MaterialRule> OVERWORLD = ResourceKey.create(Registries.MATERIAL_RULE, Identifier.fromNamespaceAndPath(Nyctophobia.MOD_ID, "overworld"));

    private static final MaterialCondition ON_FLOOR = MaterialRules.stoneDepthCheck(0, false, CaveSurface.FLOOR);
    private static final MaterialCondition UNDER_FLOOR = MaterialRules.stoneDepthCheck(0, true, CaveSurface.FLOOR);

    private static final MaterialRule MOSS_BLOCK = block(Blocks.MOSS_BLOCK);
    private static final MaterialRule ROOTED_DIRT = block(Blocks.ROOTED_DIRT);
    private static final MaterialRule COARSE_DIRT = block(Blocks.COARSE_DIRT);
    private static final MaterialRule MUD = block(Blocks.MUD);
    private static final MaterialRule GRAVEL = block(Blocks.GRAVEL);
    private static final MaterialRule BASALT = block(Blocks.BASALT);
    private static final MaterialRule BLACKSTONE = block(Blocks.BLACKSTONE);
    private static final MaterialRule SOUL_SAND = block(Blocks.SOUL_SAND);
    private static final MaterialRule SOUL_SOIL = block(Blocks.SOUL_SOIL);
    private static final MaterialRule GRASS_BLOCK = block(Blocks.GRASS_BLOCK);
    private static final MaterialRule WATER = block(Blocks.WATER);
    private static final MaterialRule CALCITE = block(Blocks.CALCITE);
    private static final MaterialRule TUFF = block(Blocks.TUFF);
    private static final MaterialRule SAND = block(Blocks.SAND);
    private static final MaterialRule SANDSTONE = block(Blocks.SANDSTONE);
    private static final MaterialRule BONE_BLOCK = block(Blocks.BONE_BLOCK.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y).getBlock());
    private static final MaterialRule NETHER_WART = block(Blocks.NETHER_WART_BLOCK);
    private static final MaterialRule MOSSY_COBBLESTONE = block(Blocks.MOSSY_COBBLESTONE);
    private static final MaterialRule GREEN_TERRACOTTA = block(Blocks.DYED_TERRACOTTA.green());
    private static final MaterialRule DIRT = block(Blocks.DIRT);
    private static final MaterialRule AIR = block(Blocks.AIR);
    private static final MaterialRule STONE = block(Blocks.STONE);
    private static final MaterialRule DEEPSLATE = block(Blocks.DEEPSLATE);

    private static final ResourceKey<MaterialRule> COPPER_ORE_VEIN = vanillaRule("overworld/copper_ore_vein");
    private static final ResourceKey<MaterialRule> IRON_ORE_VEIN = vanillaRule("overworld/iron_ore_vein");

    private static ResourceKey<MaterialRule> vanillaRule(String path) {
        return ResourceKey.create(Registries.MATERIAL_RULE, Identifier.withDefaultNamespace(path));
    }

    private static MaterialRule veinRule(NRegistryContext<MaterialRule> entries, ResourceKey<MaterialRule> key) {
        return new MaterialRule.HolderHolder(entries.ref(key));
    }

    public static void populate(NRegistryContext<MaterialRule> entries) {
        entries.add(OVERWORLD, makeRules(entries.biomes(), veinRule(entries, COPPER_ORE_VEIN), veinRule(entries, IRON_ORE_VEIN)));
    }

    public static MaterialRule makeRules(HolderGetter<Biome> biomes, MaterialRule copperVein, MaterialRule ironVein) {
        MaterialRule noiseGrass = MaterialRules.sequence(
                MaterialRules.ifTrue(
                        ON_FLOOR,
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        MaterialRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SURFACE, -0.2, 0.0), GRASS_BLOCK)
                                )
                        )
                )
        );

        MaterialRule noiseGrass93 = MaterialRules.sequence(
                MaterialRules.ifTrue(
                        ON_FLOOR,
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        MaterialRules.yBlockCheck(VerticalAnchor.absolute(93), 3),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SURFACE, -0.2, 0.0), GRASS_BLOCK)
                                )
                        )
                )
        );

        MaterialRule noiseCoarseDirt = MaterialRules.sequence(
                MaterialRules.ifTrue(
                        ON_FLOOR,
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        MaterialRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SURFACE, 0.2, 0.4), COARSE_DIRT)
                                )
                        )
                )
        );

        MaterialRule noiseMossCobble = MaterialRules.sequence(
                MaterialRules.ifTrue(
                        ON_FLOOR,
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        MaterialRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
                                        MaterialRules.sequence(
                                                MaterialRules.sequence(
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SWAMP, -0.4D, 0.2D), MOSS_BLOCK),
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.PATCH, 0.5D, 0.6D), MOSSY_COBBLESTONE)
                                                ),
                                                MaterialRules.sequence(
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SWAMP, -0.4D, 0.3D), GRASS_BLOCK),
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SWAMP, -0.5D, 0.5D), COARSE_DIRT),
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.NETHER_WART, -1.0D, 1.0D), ROOTED_DIRT),
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.CLAY_BANDS_OFFSET, -0.3D, 0.4D), MOSSY_COBBLESTONE),
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.PATCH, -0.3D, 0.3D), MOSSY_COBBLESTONE)
                                                ),
                                                MaterialRules.sequence(
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SURFACE, 0.2D, 0.5D), GRASS_BLOCK),
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.PATCH, -0.8D, 0.8D), ROOTED_DIRT)
                                                )
                                        )
                                ),
                                MaterialRules.sequence(
                                        MaterialRules.ifTrue(MaterialRules.yBlockCheck(VerticalAnchor.absolute(61), 0), DIRT),
                                        MaterialRules.ifTrue(MaterialRules.yBlockCheck(VerticalAnchor.absolute(0), 0), STONE),
                                        MaterialRules.ifTrue(MaterialRules.yBlockCheck(VerticalAnchor.absolute(-64), 0), DEEPSLATE)
                                )
                        )
                )
        );

        MaterialRule noiseErosion = MaterialRules.sequence(
                MaterialRules.ifTrue(
                        ON_FLOOR,
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        MaterialRules.yBlockCheck(VerticalAnchor.absolute(93), 0),
                                        MaterialRules.ifTrue(
                                                MaterialRules.noiseCondition2d(Noises.SURFACE, -0.1, 1.15),
                                                MaterialRules.sequence(
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SURFACE, -0.1, 0.15), COARSE_DIRT),
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SURFACE, 0.15, 0.5), SOUL_SAND),
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SURFACE, 0.5, 0.75), SOUL_SOIL),
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SURFACE, 0.75, 0.9), SOUL_SAND),
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SURFACE, 0.9, 1.15), COARSE_DIRT)
                                                )
                                        )
                                )
                        )
                )
        );

        MaterialRule noiseMoss = MaterialRules.sequence(
                MaterialRules.ifTrue(
                        ON_FLOOR,
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        MaterialRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(NNoiseParameters.HAUNTED_FOREST_NOISE, -0.2, 2.0), MOSS_BLOCK)
                                )
                        )
                )
        );

        MaterialRule noiseMoss93 = MaterialRules.sequence(
                MaterialRules.ifTrue(
                        ON_FLOOR,
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        MaterialRules.yBlockCheck(VerticalAnchor.absolute(93), 2),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(NNoiseParameters.HAUNTED_FOREST_NOISE, -0.2, 2.0), MOSS_BLOCK)
                                )
                        )
                )
        );

        MaterialCondition above62 = MaterialRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
        MaterialCondition above63_0 = MaterialRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
        MaterialRule waterErosionRule = MaterialRules.sequence(
                MaterialRules.ifTrue(
                        ON_FLOOR,
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        MaterialRules.isBiome(biomes, NBiomes.HAUNTED_LAKES_KEY),
                                        MaterialRules.ifTrue(
                                                above62,
                                                MaterialRules.ifTrue(
                                                        MaterialRules.not(above63_0),
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SWAMP, 1.0D), WATER)
                                                )
                                        )
                                )
                        )
                )
        );

        MaterialCondition above68_0 = MaterialRules.yBlockCheck(VerticalAnchor.absolute(68), 0);

        MaterialRule erodedHauntedForestRule = MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, NBiomes.ERODED_HAUNTED_FOREST_KEY),
                MaterialRules.sequence(noiseGrass93, noiseErosion, noiseMoss93)
        );

        MaterialRule deepDarkForestRule =
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, NBiomes.DEEP_DARK_FOREST), noiseMossCobble);

        MaterialRule hauntedForestRule = MaterialRules.sequence(
                MaterialRules.ifTrue(
                        MaterialRules.isBiome(biomes, NBiomes.HAUNTED_FOREST_KEY, NBiomes.HAUNTED_LAKES_KEY),
                        MaterialRules.sequence(noiseGrass, noiseCoarseDirt, noiseMoss)
                ),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, NBiomes.HAUNTED_LAKES_KEY), waterErosionRule)
        );

        MaterialRule ancientReefRule = MaterialRules.sequence(
                MaterialRules.ifTrue(
                        UNDER_FLOOR,
                        MaterialRules.sequence(
                                MaterialRules.ifTrue(
                                        MaterialRules.yBlockCheck(VerticalAnchor.absolute(53), 2),
                                        MaterialRules.sequence(
                                                MaterialRules.ifTrue(
                                                        above63_0,
                                                        MaterialRules.ifTrue(
                                                                MaterialRules.not(above68_0),
                                                                MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SWAMP, -1.0D, -0.55), WATER)
                                                        )
                                                ),
                                                MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SURFACE, -0.1D, 0.2D), CALCITE),
                                                MaterialRules.ifTrue(MaterialRules.steep(), TUFF),
                                                MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SURFACE, 0.2D, 0.5D), SANDSTONE),
                                                SAND
                                        )
                                )
                        )
                )
        );

        // The water band stops below sea level 63.
        MaterialCondition above60 = MaterialRules.yBlockCheck(VerticalAnchor.absolute(60), 0);
        MaterialCondition dryAbove = MaterialRules.waterBlockCheck(-1, 0);

        MaterialRule marshlandsRule = MaterialRules.ifTrue(
                ON_FLOOR,
                MaterialRules.sequence(
                        MaterialRules.ifTrue(
                                dryAbove,
                                MaterialRules.ifTrue(
                                        above60,
                                        MaterialRules.ifTrue(
                                                MaterialRules.not(above63_0),
                                                MaterialRules.sequence(
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SWAMP, -0.45D), WATER),
                                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SWAMP, -0.7D, -0.45D), MUD)
                                                )
                                        )
                                )
                        ),
                        MaterialRules.ifTrue(MaterialRules.not(dryAbove), MUD),
                        MaterialRules.ifTrue(
                                MaterialRules.yBlockCheck(VerticalAnchor.absolute(61), 0),
                                MaterialRules.sequence(
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SWAMP, -0.6D, -0.2D), MUD),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SURFACE, 0.3D, 0.7D), MOSS_BLOCK),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.PATCH, 0.4D, 0.8D), COARSE_DIRT),
                                        GRASS_BLOCK
                                )
                        ),
                        MaterialRules.ifTrue(MaterialRules.yBlockCheck(VerticalAnchor.absolute(0), 0), DIRT)
                )
        );

        MaterialRule burntForestRule = MaterialRules.ifTrue(
                ON_FLOOR,
                MaterialRules.sequence(
                        MaterialRules.ifTrue(
                                above62,
                                MaterialRules.sequence(
                                        MaterialRules.ifTrue(MaterialRules.steep(), BASALT),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SURFACE, 0.1D, 0.6D), BLACKSTONE),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SURFACE, 0.6D, 1.0D), SOUL_SOIL),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.PATCH, 0.2D, 0.6D), BASALT),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.PATCH, -0.6D, -0.2D), GRAVEL),
                                        MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.SWAMP, -0.8D, -0.3D), SOUL_SAND),
                                        COARSE_DIRT
                                )
                        ),
                        MaterialRules.ifTrue(MaterialRules.yBlockCheck(VerticalAnchor.absolute(0), 0), DIRT)
                )
        );

        return MaterialRules.sequence(
                copperVein,
                ironVein,
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, NBiomes.BURNT_FOREST_KEY), burntForestRule),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, NBiomes.MARSHLANDS_KEY), marshlandsRule),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, NBiomes.ERODED_HAUNTED_FOREST_KEY), erodedHauntedForestRule),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, NBiomes.HAUNTED_LAKES_KEY), hauntedForestRule),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, NBiomes.HAUNTED_FOREST_KEY), hauntedForestRule),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, NBiomes.ANCIENT_DEAD_CORAL_REEF_KEY), ancientReefRule),
                MaterialRules.ifTrue(MaterialRules.isBiome(biomes, NBiomes.DEEP_DARK_FOREST), deepDarkForestRule)
        );
    }

    private static MaterialRule block(Block block) {
        return MaterialRules.state(block.defaultBlockState());
    }

}
