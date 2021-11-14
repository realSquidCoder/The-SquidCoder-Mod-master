package com.SquidCoder.squidcoder.setup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup SQUIDCODER_MOD = new ItemGroup("squidcoder_modtab") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SILVER_INGOT.get());
        }
    };
    
    public static final ItemGroup CAVES_AND_CLIFFS_BACKPORT = new ItemGroup("caves_and_cliffsModTab") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SPYGLASS.get());
        }
    };
}
