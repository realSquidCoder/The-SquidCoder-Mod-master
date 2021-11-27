package com.SquidCoder.squidcoder.data.custom.Recipes.custom;

import com.SquidCoder.squidcoder.data.custom.Recipes.AllRecipeTypes;
import com.SquidCoder.squidcoder.data.custom.items.ModItems;
import net.minecraft.data.DataGenerator;

public class PolishingRecipeGen extends ProcessingRecipeGen {

    GeneratedRecipe

            ROSE_QUARTZ = create(ModItems.AMETHYST_SHARD::get, b -> b.output(ModItems.AMETHYST_SHARD.get()))

            ;

    public PolishingRecipeGen(DataGenerator p_i48262_1_) {
        super(p_i48262_1_);
    }

    @Override
    protected AllRecipeTypes getRecipeType() {
        return AllRecipeTypes.SANDPAPER_POLISHING;
    }

}
