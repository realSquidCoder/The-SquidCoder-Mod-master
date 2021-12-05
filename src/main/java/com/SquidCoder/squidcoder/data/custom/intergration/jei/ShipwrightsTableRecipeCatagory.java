package com.SquidCoder.squidcoder.data.custom.intergration.jei;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.data.custom.Recipes.custom.ShipwrightsTableRecipe;
import com.SquidCoder.squidcoder.data.custom.blocks.ModBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ShipwrightsTableRecipeCatagory implements IRecipeCategory<ShipwrightsTableRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(SquidCoderMod.MOD_ID, "lightning");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(SquidCoderMod.MOD_ID, "textures/gui/shipwrights_table.png");

    private final IDrawable background;
    private final IDrawable icon;

    public ShipwrightsTableRecipeCatagory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.SHIPWRIGHTS_TABLE.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends ShipwrightsTableRecipe> getRecipeClass() {
        return ShipwrightsTableRecipe.class;
    }

    @Override
    public String getTitle() {
        return ModBlocks.SHIPWRIGHTS_TABLE.get().getName().getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(ShipwrightsTableRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ShipwrightsTableRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 79, 30);
        recipeLayout.getItemStacks().init(1, true, 79, 52);

        recipeLayout.getItemStacks().init(2, false, 102, 42);
        recipeLayout.getItemStacks().set(ingredients);
    }
}
