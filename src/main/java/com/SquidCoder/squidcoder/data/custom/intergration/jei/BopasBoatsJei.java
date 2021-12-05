package com.SquidCoder.squidcoder.data.custom.intergration.jei;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.data.custom.Recipes.ModRecipeTypes;
import com.SquidCoder.squidcoder.data.custom.Recipes.custom.ShipwrightsTableRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class BopasBoatsJei implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(SquidCoderMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new ShipwrightsTableRecipeCatagory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        registration.addRecipes(rm.getAllRecipesFor(ModRecipeTypes.SHIPS_RECIPE).stream()
                        .filter(r -> r instanceof ShipwrightsTableRecipe).collect(Collectors.toList()),
                ShipwrightsTableRecipeCatagory.UID);
    }
}
