package com.SquidCoder.squidcoder.setup;

import com.SquidCoder.squidcoder.data.custom.blocks.OxidationLevel;
import com.SquidCoder.squidcoder.data.custom.blocks.OxidizingBlock;
import com.SquidCoder.squidcoder.data.custom.blocks.OxidizingBlock3;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final RegistryObject<Block> SILVER_BLOCK = register("silver_block", () ->
            new Block(AbstractBlock.Properties
                    .of(Material.METAL)
                    .strength(3, 10)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> SILVER_ORE = register("silver_ore", () ->
            new Block(AbstractBlock.Properties
                    .of(Material.STONE)
                    .strength(3, 3)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> COPPER_ORE = register("copper_ore", () ->
            new Block(AbstractBlock.Properties
                    .of(Material.STONE)
                    .strength(3, 3)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final RegistryObject<OxidizingBlock3> COPPER_BLOCK = register("copper_block", () ->
            new OxidizingBlock3(AbstractBlock.Properties
                    .of(Material.METAL)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .strength(3, 6)
                    .sound(ModSoundType.COPPER),
                    OxidationLevel.New));

    public static final RegistryObject<OxidizingBlock3> EXPOSED_COPPER = register("exposed_copper", () ->
            new OxidizingBlock3(AbstractBlock.Properties
                    .of(Material.METAL)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .strength(3, 6)
                    .sound(ModSoundType.COPPER),
                    OxidationLevel.Exposed));

    public static final RegistryObject<OxidizingBlock3> WEATHERED_COPPER = register("weathered_copper", () ->
            new OxidizingBlock3(AbstractBlock.Properties
                    .of(Material.METAL)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .strength(3, 6)
                    .sound(ModSoundType.COPPER),
                    OxidationLevel.Weathered));

    public static final RegistryObject<OxidizingBlock3> OXIDIZED_COPPER = register("oxidized_copper", () ->
            new OxidizingBlock3(AbstractBlock.Properties
                    .of(Material.METAL)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .strength(3, 6)
                    .sound(ModSoundType.COPPER),
                    OxidationLevel.Oxidized));

    public static final RegistryObject<Block> WAXED_COPPER_BLOCK = register("waxed_copper_block", () ->
            new Block(AbstractBlock.Properties
                    .of(Material.METAL)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .strength(3, 6)
                    .sound(ModSoundType.COPPER)));

    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER = register("waxed_exposed_copper", () ->
            new Block(AbstractBlock.Properties
                    .of(Material.METAL)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .strength(3, 6)
                    .sound(ModSoundType.COPPER)));

    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER = register("waxed_weathered_copper", () ->
            new Block(AbstractBlock.Properties
                    .of(Material.METAL)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .strength(3, 6)
                    .sound(ModSoundType.COPPER)));

    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER = register("waxed_oxidized_copper", () ->
            new Block(AbstractBlock.Properties
                    .of(Material.METAL)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .strength(3, 6)
                    .sound(ModSoundType.COPPER)));

    public static final RegistryObject<Block> RAW_COPPER_BLOCK = register("raw_copper_block", () ->
            new Block(AbstractBlock.Properties
                    .of(Material.STONE)
                    .strength(5, 6)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> RAW_IRON_BLOCK = register("raw_iron_block", () ->
            new Block(AbstractBlock.Properties
                    .of(Material.STONE)
                    .strength(5, 6)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> RAW_GOLD_BLOCK = register("raw_gold_block", () ->
            new Block(AbstractBlock.Properties
                    .of(Material.STONE)
                    .strength(5, 6)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));



    static void register() {}

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return Registration.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(ModItemGroup.SQUIDCODER_MOD)));
        return ret;
    }
}
