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
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class NSurfaceRules {
    private static final SurfaceRules.RuleSource MOSS_BLOCK = block(Blocks.MOSS_BLOCK);
    private static final SurfaceRules.RuleSource ROOTED_DIRT = block(Blocks.ROOTED_DIRT);
    private static final SurfaceRules.RuleSource COARSE_DIRT = block(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource MUD = block(Blocks.MUD);
    private static final SurfaceRules.RuleSource GRAVEL = block(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource BASALT = block(Blocks.BASALT);
    private static final SurfaceRules.RuleSource BLACKSTONE = block(Blocks.BLACKSTONE);
    private static final SurfaceRules.RuleSource SOUL_SAND = block(Blocks.SOUL_SAND);
    private static final SurfaceRules.RuleSource SOUL_SOIL = block(Blocks.SOUL_SOIL);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = block(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource WATER = block(Blocks.WATER);
    private static final SurfaceRules.RuleSource CALCITE = block(Blocks.CALCITE);
    private static final SurfaceRules.RuleSource TUFF = block(Blocks.TUFF);
    private static final SurfaceRules.RuleSource SAND = block(Blocks.SAND);
    private static final SurfaceRules.RuleSource SANDSTONE = block(Blocks.SANDSTONE);
    private static final SurfaceRules.RuleSource BONE_BLOCK = block(Blocks.BONE_BLOCK.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y).getBlock());
    private static final SurfaceRules.RuleSource NETHER_WART = block(Blocks.NETHER_WART_BLOCK);
    private static final SurfaceRules.RuleSource MOSSY_COBBLESTONE = block(Blocks.MOSSY_COBBLESTONE);
    private static final SurfaceRules.RuleSource GREEN_TERRACOTTA = block(Blocks.DYED_TERRACOTTA.green());
    private static final SurfaceRules.RuleSource DIRT = block(Blocks.DIRT);
    private static final SurfaceRules.RuleSource AIR = block(Blocks.AIR);
    private static final SurfaceRules.RuleSource STONE = block(Blocks.STONE);
    private static final SurfaceRules.RuleSource DEEPSLATE = block(Blocks.DEEPSLATE);

    public static SurfaceRules.RuleSource makeRules(HolderGetter<Biome> biomes) {
        SurfaceRules.RuleSource noiseGrass = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SURFACE, -0.2, 0.0), GRASS_BLOCK)
                                )
                        )
                )
        );

        SurfaceRules.RuleSource noiseGrass93 = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.yBlockCheck(VerticalAnchor.absolute(93), 3),
                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SURFACE, -0.2, 0.0), GRASS_BLOCK)
                                )
                        )
                )
        );

        SurfaceRules.RuleSource noiseCoarseDirt = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SURFACE, 0.2, 0.4), COARSE_DIRT)
                                )
                        )
                )
        );

        SurfaceRules.RuleSource noiseMossCobble = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
                                        SurfaceRules.sequence(
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SWAMP, -0.4D, 0.2D), MOSS_BLOCK),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.PATCH, 0.5D, 0.6D), MOSSY_COBBLESTONE)
                                                ),
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SWAMP, -0.4D, 0.3D), GRASS_BLOCK),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SWAMP, -0.5D, 0.5D), COARSE_DIRT),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.NETHER_WART, -1.0D, 1.0D), ROOTED_DIRT),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.CLAY_BANDS_OFFSET, -0.3D, 0.4D), MOSSY_COBBLESTONE),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.PATCH, -0.3D, 0.3D), MOSSY_COBBLESTONE)
                                                ),
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SURFACE, 0.2D, 0.5D), GRASS_BLOCK),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.PATCH, -0.8D, 0.8D), ROOTED_DIRT)
                                                )
                                        )
                                ),
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(61), 0), DIRT),
                                        SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(0), 0), STONE),
                                        SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(-64), 0), DEEPSLATE)
                                )
                        )
                )
        );

        SurfaceRules.RuleSource noiseErosion = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.yBlockCheck(VerticalAnchor.absolute(93), 0),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.noiseCondition2d(Noises.SURFACE, -0.1, 1.15),
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SURFACE, -0.1, 0.15), COARSE_DIRT),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SURFACE, 0.15, 0.5), SOUL_SAND),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SURFACE, 0.5, 0.75), SOUL_SOIL),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SURFACE, 0.75, 0.9), SOUL_SAND),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SURFACE, 0.9, 1.15), COARSE_DIRT)
                                                )
                                        )
                                )
                        )
                )
        );

        SurfaceRules.RuleSource noiseMoss = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(NNoiseParameters.HAUNTED_FOREST_NOISE, -0.2, 2.0), MOSS_BLOCK)
                                )
                        )
                )
        );

        SurfaceRules.RuleSource noiseMoss93 = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.yBlockCheck(VerticalAnchor.absolute(93), 2),
                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(NNoiseParameters.HAUNTED_FOREST_NOISE, -0.2, 2.0), MOSS_BLOCK)
                                )
                        )
                )
        );

        SurfaceRules.ConditionSource above62 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
        SurfaceRules.ConditionSource above63_0 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
        SurfaceRules.RuleSource waterErosionRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(biomes, NBiomes.HAUNTED_LAKES_KEY),
                                        SurfaceRules.ifTrue(
                                                above62,
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.not(above63_0),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SWAMP, 1.0D), WATER)
                                                )
                                        )
                                )
                        )
                )
        );

        SurfaceRules.ConditionSource above68_0 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(68), 0);

        SurfaceRules.RuleSource erodedHauntedForestRule = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(biomes, NBiomes.ERODED_HAUNTED_FOREST_KEY),
                SurfaceRules.sequence(noiseGrass93, noiseErosion, noiseMoss93)
        );

        SurfaceRules.RuleSource deepDarkForestRule =
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, NBiomes.DEEP_DARK_FOREST), noiseMossCobble);

        SurfaceRules.RuleSource hauntedForestRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, NBiomes.HAUNTED_FOREST_KEY, NBiomes.HAUNTED_LAKES_KEY),
                        SurfaceRules.sequence(noiseGrass, noiseCoarseDirt, noiseMoss)
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, NBiomes.HAUNTED_LAKES_KEY), waterErosionRule)
        );

        SurfaceRules.RuleSource ancientReefRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.UNDER_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.yBlockCheck(VerticalAnchor.absolute(53), 2),
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(
                                                        above63_0,
                                                        SurfaceRules.ifTrue(
                                                                SurfaceRules.not(above68_0),
                                                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SWAMP, -1.0D, -0.55), WATER)
                                                        )
                                                ),
                                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SURFACE, -0.1D, 0.2D), CALCITE),
                                                SurfaceRules.ifTrue(SurfaceRules.steep(), TUFF),
                                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SURFACE, 0.2D, 0.5D), SANDSTONE),
                                                SAND
                                        )
                                )
                        )
                )
        );

        // The water band stops below sea level 63.
        SurfaceRules.ConditionSource above60 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(60), 0);
        SurfaceRules.ConditionSource dryAbove = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource marshlandsRule = SurfaceRules.ifTrue(
                SurfaceRules.ON_FLOOR,
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                                dryAbove,
                                SurfaceRules.ifTrue(
                                        above60,
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.not(above63_0),
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SWAMP, -0.45D), WATER),
                                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SWAMP, -0.7D, -0.45D), MUD)
                                                )
                                        )
                                )
                        ),
                        SurfaceRules.ifTrue(SurfaceRules.not(dryAbove), MUD),
                        SurfaceRules.ifTrue(
                                SurfaceRules.yBlockCheck(VerticalAnchor.absolute(61), 0),
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SWAMP, -0.6D, -0.2D), MUD),
                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SURFACE, 0.3D, 0.7D), MOSS_BLOCK),
                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.PATCH, 0.4D, 0.8D), COARSE_DIRT),
                                        GRASS_BLOCK
                                )
                        ),
                        SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(0), 0), DIRT)
                )
        );

        SurfaceRules.RuleSource burntForestRule = SurfaceRules.ifTrue(
                SurfaceRules.ON_FLOOR,
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                                above62,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(SurfaceRules.steep(), BASALT),
                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SURFACE, 0.1D, 0.6D), BLACKSTONE),
                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SURFACE, 0.6D, 1.0D), SOUL_SOIL),
                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.PATCH, 0.2D, 0.6D), BASALT),
                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.PATCH, -0.6D, -0.2D), GRAVEL),
                                        SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SWAMP, -0.8D, -0.3D), SOUL_SAND),
                                        COARSE_DIRT
                                )
                        ),
                        SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(0), 0), DIRT)
                )
        );

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, NBiomes.BURNT_FOREST_KEY), burntForestRule),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, NBiomes.MARSHLANDS_KEY), marshlandsRule),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, NBiomes.ERODED_HAUNTED_FOREST_KEY), erodedHauntedForestRule),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, NBiomes.HAUNTED_LAKES_KEY), hauntedForestRule),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, NBiomes.HAUNTED_FOREST_KEY), hauntedForestRule),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, NBiomes.ANCIENT_DEAD_CORAL_REEF_KEY), ancientReefRule),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, NBiomes.DEEP_DARK_FOREST), deepDarkForestRule)
        );
    }

    private static SurfaceRules.RuleSource block(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

}
