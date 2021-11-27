package com.SquidCoder.squidcoder.data.client;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.data.custom.blocks.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SquidCoderMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.SILVER_BLOCK.get());
        simpleBlock(ModBlocks.COPPER_BLOCK.get());
        simpleBlock(ModBlocks.EXPOSED_COPPER.get());
        simpleBlock(ModBlocks.WEATHERED_COPPER.get());
        simpleBlock(ModBlocks.OXIDIZED_COPPER.get());
        simpleBlock(ModBlocks.WAXED_COPPER_BLOCK.get());
        simpleBlock(ModBlocks.SILVER_ORE.get());
        simpleBlock(ModBlocks.COPPER_ORE.get());
        simpleBlock(ModBlocks.RAW_COPPER_BLOCK.get());
        simpleBlock(ModBlocks.RAW_GOLD_BLOCK.get());
        simpleBlock(ModBlocks.RAW_IRON_BLOCK.get());
        //cube(ModBlocks.SHIPWRIGHTS_TABLE.get(),modLoc("block/shipwrights_table_side"),modLoc("block/shipwrights_table_side"),modLoc("block/shipwrights_table_top"));
    }
}
