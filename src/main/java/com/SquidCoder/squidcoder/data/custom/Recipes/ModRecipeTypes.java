package com.SquidCoder.squidcoder.data.custom.Recipes;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.data.custom.Recipes.custom.ShipwrightsTableRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeTypes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SquidCoderMod.MOD_ID);

    public static final RegistryObject<ShipwrightsTableRecipe.Serializer> SHIPS_SERIALIZER
            = RECIPE_SERIALIZER.register("lightning", ShipwrightsTableRecipe.Serializer::new);

    public static IRecipeType<ShipwrightsTableRecipe> SHIPS_RECIPE
            = new ShipwrightsTableRecipe.ShipwrightsTableRecipeType();


    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);

        Registry.register(Registry.RECIPE_TYPE, ShipwrightsTableRecipe.TYPE_ID, SHIPS_RECIPE);
    }
}