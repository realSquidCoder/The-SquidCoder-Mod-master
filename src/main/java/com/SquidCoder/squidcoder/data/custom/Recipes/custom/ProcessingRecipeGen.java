package com.SquidCoder.squidcoder.data.custom.Recipes.custom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import com.SquidCoder.squidcoder.SquidCoderMod;

import com.SquidCoder.squidcoder.data.custom.Recipes.AllRecipeTypes;
import com.SquidCoder.squidcoder.data.custom.Recipes.ModRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;

public abstract class ProcessingRecipeGen extends ModRecipeProvider {

    final List<GeneratedRecipe> all = new ArrayList<>();
    protected static List<ProcessingRecipeGen> generators = new ArrayList<>();
    protected static final int BUCKET = FluidAttributes.BUCKET_VOLUME;
    protected static final int BOTTLE = 250;

    public static void registerAll(DataGenerator gen) {
        generators.add(new PolishingRecipeGen(gen));

        gen.addProvider(new IDataProvider() {

            @Override
            public String getName() {
                return "SquidCoder's Processing Recipes";
            }

            @Override
            public void run(DirectoryCache dc) throws IOException {
                generators.forEach(g -> {
                    try {
                        g.run(dc);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    public ProcessingRecipeGen(DataGenerator genIn) {
        super(genIn);
    }

    /**
     * Create a processing recipe with a single itemstack ingredient, using its id
     * as the name of the recipe
     */
    protected <T extends ProcessingRecipe<?>> GeneratedRecipe create(String namespace, Supplier<IItemProvider> singleIngredient,
                                                                     UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        ProcessingRecipeSerializer<T> serializer = getSerializer();
        GeneratedRecipe generatedRecipe = c -> {
            IItemProvider iItemProvider = singleIngredient.get();
            transform
                    .apply(new ProcessingRecipeBuilder<>(serializer.getFactory(), new ResourceLocation(namespace, iItemProvider.asItem()
                            .getRegistryName()
                            .getPath())).withItemIngredients(Ingredient.of(iItemProvider)))
                    .build(c);
        };
        all.add(generatedRecipe);
        return generatedRecipe;
    }

    /**
     * Create a processing recipe with a single itemstack ingredient, using its id
     * as the name of the recipe
     */
    protected <T extends ProcessingRecipe<?>> GeneratedRecipe create(Supplier<IItemProvider> singleIngredient,
                                                                     UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        return create(SquidCoderMod.MOD_ID, singleIngredient, transform);
    }

    /**
     * Create a new processing recipe, with recipe definitions provided by the
     * function
     */
    protected <T extends ProcessingRecipe<?>> GeneratedRecipe create(ResourceLocation name,
                                                                     UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        ProcessingRecipeSerializer<T> serializer = getSerializer();
        GeneratedRecipe generatedRecipe =
                c -> transform.apply(new ProcessingRecipeBuilder<>(serializer.getFactory(), name))
                        .build(c);
        all.add(generatedRecipe);
        return generatedRecipe;
    }

    /**
     * Create a new processing recipe, with recipe definitions provided by the
     * function
     */
    protected <T extends ProcessingRecipe<?>> GeneratedRecipe create(String name,
                                                                     UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        return create(SquidCoderMod.asResource(name), transform);
    }

    protected  <T extends ProcessingRecipe<?>> ProcessingRecipeSerializer<T> getSerializer() {
        ProcessingRecipeSerializer<T> serializer = getRecipeType().getSerializer();
        return serializer;
    }

    @Override
    public final String getName() {
        return "SquidCoder's Processing Recipes: " + getRecipeType();
    }

    protected abstract AllRecipeTypes getRecipeType();

}
