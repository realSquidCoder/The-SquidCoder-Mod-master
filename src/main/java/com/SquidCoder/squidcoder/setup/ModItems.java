package com.SquidCoder.squidcoder.setup;

import com.SquidCoder.squidcoder.data.custom.items.SandPaperItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {
    public static final RegistryObject<Item> SILVER_INGOT = Registration.ITEMS.register("silver_ingot", () ->
            new Item(new Item.Properties().tab(ModItemGroup.SQUIDCODER_MOD)));
    public static final RegistryObject<Item> SILVER_DUST = Registration.ITEMS.register("silver_dust", () ->
            new Item(new Item.Properties().tab(ModItemGroup.SQUIDCODER_MOD)));
    public static final RegistryObject<Item> SILVER_NUGGET = Registration.ITEMS.register("silver_nugget", () ->
            new Item(new Item.Properties().tab(ModItemGroup.SQUIDCODER_MOD)));
    public static final RegistryObject<Item> RAW_COPPER = Registration.ITEMS.register("raw_copper", () ->
            new Item(new Item.Properties().tab(ModItemGroup.SQUIDCODER_MOD)));
    public static final RegistryObject<Item> RAW_IRON = Registration.ITEMS.register("raw_iron", () ->
            new Item(new Item.Properties().tab(ModItemGroup.SQUIDCODER_MOD)));
    public static final RegistryObject<Item> RAW_GOLD = Registration.ITEMS.register("raw_gold", () ->
            new Item(new Item.Properties().tab(ModItemGroup.SQUIDCODER_MOD)));
    public static final RegistryObject<Item> COPPER_INGOT = Registration.ITEMS.register("copper_ingot", () ->
            new Item(new Item.Properties().tab(ModItemGroup.SQUIDCODER_MOD)));

    public static final RegistryObject<Item> SPYGLASS = Registration.ITEMS.register("spyglass", () ->
            new Item(new Item.Properties().tab(ModItemGroup.SQUIDCODER_MOD)));

    public static final RegistryObject<Item> AMETHYST_SHARD = Registration.ITEMS.register("amethyst_shard", () ->
            new Item(new Item.Properties().tab(ModItemGroup.SQUIDCODER_MOD))/*{
                @Override
                public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
                    if(Screen.hasAltDown()){
                        tooltip.add(new TranslationTextComponent("tooltip.squidcoder.alt"));
                    }
                    if(Screen.hasControlDown()){
                        tooltip.add(new TranslationTextComponent("tooltip.squidcoder.ctrl"));
                    }
                    if(Screen.hasShiftDown()){
                        tooltip.add(new TranslationTextComponent("tooltip.squidcoder.shift"));
                    }
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            }*/);

    public static final RegistryObject<SandPaperItem> SAND_PAPER = Registration.ITEMS.register("sand_paper", () ->
            new SandPaperItem(new Item.Properties().tab(ModItemGroup.SQUIDCODER_MOD)));
    public static final RegistryObject<SandPaperItem> RED_SAND_PAPER = Registration.ITEMS.register("red_sand_paper", () ->
            new SandPaperItem(new Item.Properties().tab(ModItemGroup.SQUIDCODER_MOD)));

    static void register() {}
}
