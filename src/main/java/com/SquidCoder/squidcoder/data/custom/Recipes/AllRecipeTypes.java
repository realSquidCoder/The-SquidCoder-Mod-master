package com.SquidCoder.squidcoder.data.custom.Recipes;

import java.util.Optional;
import java.util.function.Supplier;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.data.custom.Recipes.ProcessingRecipeBuilder.ProcessingRecipeFactory;
import com.SquidCoder.squidcoder.data.custom.Recipes.ProcessingRecipeSerializer;
import com.SquidCoder.squidcoder.data.custom.Recipes.IRecipeTypeInfo;

import com.SquidCoder.squidcoder.data.util.Lang;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;

public enum AllRecipeTypes implements IRecipeTypeInfo {

    SANDPAPER_POLISHING(SandPaperPolishingRecipe::new),

    ;

    private ResourceLocation id;
    private Supplier<IRecipeSerializer<?>> serializerSupplier;
    private Supplier<IRecipeType<?>> typeSupplier;
    private IRecipeSerializer<?> serializer;
    private IRecipeType<?> type;

    AllRecipeTypes(Supplier<IRecipeSerializer<?>> serializerSupplier, Supplier<IRecipeType<?>> typeSupplier) {
        this.id = SquidCoderMod.asResource(Lang.asId(name()));
        this.serializerSupplier = serializerSupplier;
        this.typeSupplier = typeSupplier;
    }

    AllRecipeTypes(Supplier<IRecipeSerializer<?>> serializerSupplier, IRecipeType<?> existingType) {
        this(serializerSupplier, () -> existingType);
    }

    AllRecipeTypes(Supplier<IRecipeSerializer<?>> serializerSupplier) {
        this.id = SquidCoderMod.asResource(Lang.asId(name()));
        this.serializerSupplier = serializerSupplier;
        this.typeSupplier = () -> simpleType(id);
    }

    AllRecipeTypes(ProcessingRecipeFactory<?> processingFactory) {
        this(processingSerializer(processingFactory));
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IRecipeSerializer<?>> T getSerializer() {
        return (T) serializer;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IRecipeType<?>> T getType() {
        return (T) type;
    }

    public <C extends IInventory, T extends IRecipe<C>> Optional<T> find(C inv, World world) {
        return world.getRecipeManager()
                .getRecipeFor(getType(), inv, world);
    }

    public static void register(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        ShapedRecipe.setCraftingSize(9, 9);

        for (AllRecipeTypes r : AllRecipeTypes.values()) {
            r.serializer = r.serializerSupplier.get();
            r.type = r.typeSupplier.get();
            r.serializer.setRegistryName(r.id);
            event.getRegistry()
                    .register(r.serializer);
        }
    }

    private static Supplier<IRecipeSerializer<?>> processingSerializer(ProcessingRecipeFactory<?> factory) {
        return () -> new ProcessingRecipeSerializer<>(factory);
    }

    public static <T extends IRecipe<?>> IRecipeType<T> simpleType(ResourceLocation id) {
        String stringId = id.toString();
        return Registry.register(Registry.RECIPE_TYPE, id, new IRecipeType<T>() {
            @Override
            public String toString() {
                return stringId;
            }
        });
    }

}
