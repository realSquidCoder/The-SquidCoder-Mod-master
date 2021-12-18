package com.SquidCoder.squidcoder.data.custom.tile_entities;

import com.SquidCoder.squidcoder.data.custom.Recipes.ModRecipeTypes;
import com.SquidCoder.squidcoder.data.custom.Recipes.custom.ShipwrightsTableRecipe;
import com.SquidCoder.squidcoder.data.custom.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class ShipwrightsTableTile extends TileEntity implements ITickableTileEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public ShipwrightsTableTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public ShipwrightsTableTile() {
        this(ModTileEntities.SHIPWRIGHT_TABLE_TILE.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.save(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(20) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot){
                    case 2:
                    case 7:
                    case 12:
                        return stack.getItem() == Items.OAK_LOG;
                    case 3:
                        return stack.getItem() == Items.WHITE_WOOL;
                    case 5:
                    case 10:
                    case 14:
                    case 16:
                    case 17:
                    case 18:
                        return stack.getItem() == Items.OAK_PLANKS || stack.getItem() == Items.IRON_INGOT;
                    case 11:
                        return stack.getItem() == Items.FURNACE;
                    case 6:
                        return stack.getItem() == Items.CHEST;
                    case 15:
                        return stack.getItem() == ModItems.PROPELLER.get();
                    case 13:
                        return false;//stack.getItem() == ModItems.ANCHOR.get();

                    default: return false;
                }
                //return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }
    public void craft() {
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<ShipwrightsTableRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(ModRecipeTypes.SHIPS_RECIPE, inv, level);

        recipe.ifPresent(iRecipe -> {
            ItemStack output = iRecipe.getResultItem();
            craftTheItem(output);
            });
            setChanged();
        };

    private void craftTheItem(ItemStack output) {
        itemHandler.extractItem(0, 1, false);
        itemHandler.extractItem(1, 1, false);
        itemHandler.insertItem(1, output, false);
    }

    @Override
    public void tick() {
        if(level.isClientSide)
            return;

        craft();
    }
}