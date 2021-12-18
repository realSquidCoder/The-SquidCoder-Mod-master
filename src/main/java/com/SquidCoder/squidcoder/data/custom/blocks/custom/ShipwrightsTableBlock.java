package com.SquidCoder.squidcoder.data.custom.blocks.custom;

import com.SquidCoder.squidcoder.data.custom.containers.ShipwrightsTableContainer;
import com.SquidCoder.squidcoder.data.custom.tile_entities.ModTileEntities;
import com.SquidCoder.squidcoder.data.custom.tile_entities.ShipwrightsTableTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class ShipwrightsTableBlock extends HorizontalBlock {
    public ShipwrightsTableBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape SHAPE_N(){
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0.75, 0, 1, 1, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0, 0, 0.25, 0.75, 0.25), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.75, 0, 0, 1, 0.75, 0.25), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.75, 0, 0.75, 1, 0.75, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0, 0.75, 0.25, 0.75, 1), IBooleanFunction.OR);

        return shape;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_N();
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection());
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isClientSide()) {
            TileEntity tileEntity = worldIn.getBlockEntity(pos);
                if(tileEntity instanceof ShipwrightsTableTile) {
                    INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);

                    NetworkHooks.openGui(((ServerPlayerEntity)player), containerProvider, tileEntity.getBlockPos());
                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.squidcoder.shipwrights_table");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new ShipwrightsTableContainer(i, worldIn, pos, playerInventory, playerEntity);
            }
        };
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.SHIPWRIGHT_TABLE_TILE.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
