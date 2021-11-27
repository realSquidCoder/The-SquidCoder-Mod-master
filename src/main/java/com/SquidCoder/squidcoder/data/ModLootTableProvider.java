package com.SquidCoder.squidcoder.data;

import com.SquidCoder.squidcoder.data.custom.blocks.ModBlocks;
import com.SquidCoder.squidcoder.data.custom.items.ModItems;
import com.SquidCoder.squidcoder.setup.Registration;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationTracker) {
        map.forEach((resourceLocation, lootTable) -> LootTableManager.validate(validationTracker, resourceLocation, lootTable));
    }

    public static class ModBlockLootTables extends BlockLootTables {
        @Override
        protected void addTables() {
            dropSelf(ModBlocks.SILVER_ORE.get());
            dropSelf(ModBlocks.SILVER_BLOCK.get());

            dropSelf(ModBlocks.COPPER_BLOCK.get());
            dropSelf(ModBlocks.EXPOSED_COPPER.get());
            dropSelf(ModBlocks.WEATHERED_COPPER.get());
            dropSelf(ModBlocks.OXIDIZED_COPPER.get());
            dropSelf(ModBlocks.WAXED_COPPER_BLOCK.get());
            dropSelf(ModBlocks.WAXED_EXPOSED_COPPER.get());
            dropSelf(ModBlocks.WAXED_WEATHERED_COPPER.get());
            dropSelf(ModBlocks.WAXED_OXIDIZED_COPPER.get());

            dropSelf(ModBlocks.SHIPWRIGHTS_TABLE.get());

            dropSelf(ModBlocks.RAW_COPPER_BLOCK.get());
            dropSelf(ModBlocks.RAW_IRON_BLOCK.get());
            dropSelf(ModBlocks.RAW_GOLD_BLOCK.get());
            dropRaw(ModBlocks.COPPER_ORE.get(), ModItems.RAW_COPPER.get(), 2, 5);

        }

        private void dropRaw(Block minedBlock, IItemProvider droppingItem, float minItemsToDrop, float maxItemsToDrop) {
            add(minedBlock, createSilkTouchDispatchTable(minedBlock, applyExplosionDecay(minedBlock, ItemLootEntry
                    .lootTableItem(droppingItem).apply(SetCount
                            .setCount(RandomValueRange
                                    .between(minItemsToDrop, maxItemsToDrop)))
                    .apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));

        }

        @Override
        @ParametersAreNonnullByDefault
        protected Iterable<Block> getKnownBlocks() {
            return Registration.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }
}
