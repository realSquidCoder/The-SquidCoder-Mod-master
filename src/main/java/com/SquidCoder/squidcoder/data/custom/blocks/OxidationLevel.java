package com.SquidCoder.squidcoder.data.custom.blocks;

import com.SquidCoder.squidcoder.setup.ModBlocks;
import net.minecraftforge.common.util.Lazy;

public enum OxidationLevel {
    New(Lazy.of(ModBlocks.COPPER_BLOCK), 0, Lazy.of(ModBlocks.EXPOSED_COPPER), Lazy.of(ModBlocks.COPPER_BLOCK)),
    Exposed(Lazy.of(ModBlocks.EXPOSED_COPPER), 1, Lazy.of(ModBlocks.WEATHERED_COPPER), Lazy.of(ModBlocks.COPPER_BLOCK)),
    Weathered(Lazy.of(ModBlocks.WEATHERED_COPPER), 2, Lazy.of(ModBlocks.OXIDIZED_COPPER), Lazy.of(ModBlocks.EXPOSED_COPPER)),
    Oxidized(Lazy.of(ModBlocks.OXIDIZED_COPPER), 3, Lazy.of(ModBlocks.OXIDIZED_COPPER), Lazy.of(ModBlocks.WEATHERED_COPPER))
    ;

    private final Lazy<OxidizingBlock3> block;
    private final Lazy<OxidizingBlock3> nextBlock;
    private final Lazy<OxidizingBlock3> oldBlock;
    private final int OxidationState;

    <T> OxidationLevel(Lazy<OxidizingBlock3> block, int oxidationState, Lazy<OxidizingBlock3> nextBlock, Lazy<OxidizingBlock3> oldBlock) {
        this.block = block;
        this.nextBlock = nextBlock;
        this.oldBlock = oldBlock;
        OxidationState = oxidationState;
    }

    public int getOxidationState() {
        return OxidationState;
    }

    public Lazy<OxidizingBlock3> getBlock() {
        return block;
    }

    public Lazy<OxidizingBlock3> getNextBlock() {
        return nextBlock;
    }

    public Lazy<OxidizingBlock3> getOldBlock() {
        return oldBlock;
    }
}
