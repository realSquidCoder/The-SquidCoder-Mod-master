package com.SquidCoder.squidcoder.setup.world.gen;

import com.SquidCoder.squidcoder.data.custom.blocks.ModBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ModConfiguredFeatures {

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> REDWOOD =
            register("redwood", Feature.TREE.configured((
                        new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(ModBlocks.SILVER_BLOCK.get().defaultBlockState()),/*trunkProvider*/
                            new SimpleBlockStateProvider(ModBlocks.SILVER_ORE.get().defaultBlockState()),/*leavesProvider*/
                            new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3),/*Offset, Radius, Height*/
                            new StraightTrunkPlacer(6, 4, 3),/*baseHeight, heightRandA, heightRandB*/
                            new TwoLayerFeature(1, 0, 1)))/*limit, lowerSize, upperSize*/
                    .ignoreVines()
                    .build()));
    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key,
                                                                                 ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}