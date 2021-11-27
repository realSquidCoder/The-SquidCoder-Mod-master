package com.SquidCoder.squidcoder.data.custom.Recipes;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.data.custom.blocks.ModBlocks;
import com.SquidCoder.squidcoder.data.custom.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    final List<GeneratedRecipe> all = new ArrayList<>();
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }


    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        makeDecompactingRecipe(ModBlocks.COPPER_BLOCK.get(), ModItems.COPPER_INGOT.get(), "copper_block", consumer);
        makeDecompactingRecipe(ModBlocks.SILVER_BLOCK.get(), ModItems.SILVER_INGOT.get(), "silver_block", consumer);

        makeDecompactingRecipe(ModBlocks.RAW_COPPER_BLOCK.get(), ModItems.RAW_COPPER.get(), "raw_copper_block", consumer);
        makeDecompactingRecipe(ModBlocks.RAW_IRON_BLOCK.get(), ModItems.RAW_IRON.get(), "raw_iron_block", consumer);
        makeDecompactingRecipe(ModBlocks.RAW_GOLD_BLOCK.get(), ModItems.RAW_GOLD.get(), "raw_gold_block", consumer);

        makeCompactingRecipe(ModItems.SILVER_INGOT.get(), ModBlocks.SILVER_BLOCK.get(), "silver_ingot",consumer);
        makeCompactingRecipe(ModItems.SILVER_NUGGET.get(), ModItems.SILVER_INGOT.get(), "silver_nugget", consumer);
        makeCompactingRecipe(ModItems.COPPER_INGOT.get(), ModBlocks.COPPER_BLOCK.get(), "copper_ingot", consumer);

        makeCompactingRecipe(ModItems.RAW_COPPER.get(), ModBlocks.RAW_COPPER_BLOCK.get(), "raw_copper", consumer);
        makeCompactingRecipe(ModItems.RAW_IRON.get(), ModBlocks.RAW_IRON_BLOCK.get(), "raw_iron", consumer);
        makeCompactingRecipe(ModItems.RAW_GOLD.get(), ModBlocks.RAW_GOLD_BLOCK.get(), "raw_gold", consumer);

        ShapedRecipeBuilder.shaped(ModBlocks.SHIPWRIGHTS_TABLE.get())
                        .define('/',Items.STICK)
                        .define('#',ItemTags.PLANKS)
                        .define('o',ModItems.BLUEPRINT.get())
                        .define('%',Items.CRAFTING_TABLE)
                        .pattern("/o/")
                        .pattern("#%#")
                        .pattern("# #")
                        .unlockedBy("has_blueprint", has(ModItems.BLUEPRINT.get()))
                        .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SPYGLASS.get())
                .define('c', ModItems.COPPER_INGOT.get())
                .define('a', ModItems.AMETHYST_SHARD.get())
                .pattern("a")
                .pattern("c")
                .pattern("c")
                .unlockedBy("has_item", has(ModItems.AMETHYST_SHARD.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.BLUEPRINT.get())
                .define('#', Items.PAPER)
                .define('o', Items.BLUE_DYE)
                .pattern("###")
                .pattern("#o#")
                .pattern("###")
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(consumer);
        

        //Melting Recipes:

        //From ore block
        makeMeltingRecipesFromOre(ModBlocks.SILVER_ORE.get(), ModItems.SILVER_INGOT.get(), 0.7f, "silver_ingot", consumer);
        makeMeltingRecipesFromOre(ModBlocks.COPPER_ORE.get(), ModItems.COPPER_INGOT.get(), 0.7f, "copper_ingot", consumer);

        //From raw item
        makeMeltingRecipesFromItem(ModItems.RAW_COPPER.get(), ModItems.COPPER_INGOT.get(), 0.7f, "copper_ingot", consumer);
        makeMeltingRecipesFromItem(ModItems.RAW_IRON.get(), Items.IRON_INGOT.getItem(), 0.7f, "iron_ingot", consumer);
        makeMeltingRecipesFromItem(ModItems.RAW_GOLD.get(), Items.GOLD_INGOT.getItem(), 1f, "gold_ingot", consumer);
    }

    private void makeMeltingRecipesFromItem(Item ingredient, Item result, float exp, String result_name, Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.smelting(Ingredient.of(ingredient), result, exp, 200)
                .unlockedBy("has_item", has(ingredient))
                .save(consumer, modId(result_name+"_from_smelting_raw"));
        CookingRecipeBuilder.blasting(Ingredient.of(ingredient), result, exp, 100)
                .unlockedBy("has_item", has(ingredient))
                .save(consumer, modId(result_name+"_from_blasting_raw"));
    }

    private void makeMeltingRecipesFromOre(Block ingredient, Item result, float exp, String result_name, Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.smelting(Ingredient.of(ingredient), result, exp, 200)
                .unlockedBy("has_item", has(ingredient))
                .save(consumer, modId(result_name+"_from_smelting_ore"));
        CookingRecipeBuilder.blasting(Ingredient.of(ingredient), result, exp, 100)
                .unlockedBy("has_item", has(ingredient))
                .save(consumer, modId(result_name+"_from_blasting_ore"));
    }

    private void makeCompactingRecipe(IItemProvider input, IItemProvider output, String storing_item, Consumer<IFinishedRecipe> consumer){
        ShapedRecipeBuilder.shaped(output)
                .define('#', input)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(input))
                .save(consumer, modId(storing_item +"_compacting"));
    }

    private void makeDecompactingRecipe(Block storageBlock, Item decompactingResult, String block_name, Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(decompactingResult,9)
                .requires(storageBlock)
                .unlockedBy("has_item", has(storageBlock))
                .save(consumer,modId(block_name + "_decompacting"));

    }

    @FunctionalInterface
    public interface GeneratedRecipe {
        void register(Consumer<IFinishedRecipe> consumer);
    }

    protected GeneratedRecipe register(GeneratedRecipe recipe) {
        all.add(recipe);
        return recipe;
    }

    private ResourceLocation modId(String path) {
        return new ResourceLocation(SquidCoderMod.MOD_ID, path);
    }
}
