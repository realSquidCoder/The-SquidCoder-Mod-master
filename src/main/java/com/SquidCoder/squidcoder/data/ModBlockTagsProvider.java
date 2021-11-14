package com.SquidCoder.squidcoder.data;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.setup.ModBlocks;
import com.SquidCoder.squidcoder.setup.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, SquidCoderMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ModTags.Blocks.ORES_SILVER)
                .add(ModBlocks.SILVER_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_SILVER);
        tag(ModTags.Blocks.STORAGE_BLOCKS_SILVER)
                .add(ModBlocks.SILVER_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_SILVER);
    }
}
