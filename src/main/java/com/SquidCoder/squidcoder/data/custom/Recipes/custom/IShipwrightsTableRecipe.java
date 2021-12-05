package com.SquidCoder.squidcoder.data.custom.Recipes.custom;

import com.SquidCoder.squidcoder.SquidCoderMod;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public interface IShipwrightsTableRecipe extends IRecipe<IInventory> {
    ResourceLocation TYPE_ID = new ResourceLocation(SquidCoderMod.MOD_ID, "lightning");

    @Override
    default IRecipeType<?> getType(){
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }
    @Override
    default boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    default boolean isSpecial(){
        return true;
    }
}
