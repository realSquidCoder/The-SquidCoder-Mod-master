package com.SquidCoder.squidcoder.data.custom.Recipes;

import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.SquidCoder.squidcoder.data.custom.Recipes.ProcessingRecipeBuilder.ProcessingRecipeFactory;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ProcessingRecipeSerializer<T extends ProcessingRecipe<?>> extends ForgeRegistryEntry<IRecipeSerializer<?>>
        implements IRecipeSerializer<T> {

    private final ProcessingRecipeFactory<T> factory;

    public ProcessingRecipeSerializer(ProcessingRecipeFactory<T> factory) {
        this.factory = factory;
    }

    protected void writeToJson(JsonObject json, T recipe) {
        JsonArray jsonIngredients = new JsonArray();
        JsonArray jsonOutputs = new JsonArray();

        recipe.getIngredients()
                .forEach(i -> jsonIngredients.add(i.toJson()));

        recipe.getRollableResults()
                .forEach(o -> jsonOutputs.add(o.serialize()));

        json.add("ingredients", jsonIngredients);
        json.add("results", jsonOutputs);

        int processingDuration = recipe.getProcessingDuration();
        if (processingDuration > 0)
            json.addProperty("processingTime", processingDuration);


        recipe.writeAdditional(json);
    }

    protected T readFromJson(ResourceLocation recipeId, JsonObject json) {
        ProcessingRecipeBuilder<T> builder = new ProcessingRecipeBuilder<>(factory, recipeId);
        NonNullList<Ingredient> ingredients = NonNullList.create();
        NonNullList<ProcessingOutput> results = NonNullList.create();

        for (JsonElement je : JSONUtils.getAsJsonArray(json, "ingredients")) {
                ingredients.add(Ingredient.fromJson(je));
        }

        for (JsonElement je : JSONUtils.getAsJsonArray(json, "results")) {
            JsonObject jsonObject = je.getAsJsonObject();
            results.add(ProcessingOutput.deserialize(je));
        }

        builder.withItemIngredients(ingredients)
                .withItemOutputs(results);

        if (JSONUtils.isValidNode(json, "processingTime"))
            builder.duration(JSONUtils.getAsInt(json, "processingTime"));
        return builder.build();
    }

    protected void writeToBuffer(PacketBuffer buffer, T recipe) {
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        NonNullList<ProcessingOutput> outputs = recipe.getRollableResults();

        buffer.writeVarInt(ingredients.size());
        ingredients.forEach(i -> i.toNetwork(buffer));

        buffer.writeVarInt(outputs.size());
        outputs.forEach(o -> o.write(buffer));

        buffer.writeVarInt(recipe.getProcessingDuration());
    }

    protected T readFromBuffer(ResourceLocation recipeId, PacketBuffer buffer) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        NonNullList<ProcessingOutput> results = NonNullList.create();

        int size = buffer.readVarInt();
        for (int i = 0; i < size; i++)
            ingredients.add(Ingredient.fromNetwork(buffer));

        size = buffer.readVarInt();
        for (int i = 0; i < size; i++)
            results.add(ProcessingOutput.read(buffer));

        return new ProcessingRecipeBuilder<>(factory, recipeId).withItemIngredients(ingredients)
                .withItemOutputs(results)
                .duration(buffer.readVarInt())
                .build();
    }

    public final void write(JsonObject json, T recipe) {
        writeToJson(json, recipe);
    }

    @Override
    public final T fromJson(ResourceLocation id, JsonObject json) {
        return readFromJson(id, json);
    }

    @Override
    public final void toNetwork(PacketBuffer buffer, T recipe) {
        writeToBuffer(buffer, recipe);
    }

    @Override
    public final T fromNetwork(ResourceLocation id, PacketBuffer buffer) {
        return readFromBuffer(id, buffer);
    }

    public ProcessingRecipeFactory<T> getFactory() {
        return factory;
    }

}
