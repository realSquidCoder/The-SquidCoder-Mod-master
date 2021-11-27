package com.SquidCoder.squidcoder.data.custom.blocks.custom;

import java.util.LinkedList;
import java.util.OptionalDouble;
import java.util.Random;


import com.SquidCoder.squidcoder.data.util.BlockHelper;
import com.SquidCoder.squidcoder.data.util.Iterate;
import com.SquidCoder.squidcoder.data.custom.particles.ModParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class OxidizingBlock extends Block {

    public static final IntegerProperty OXIDIZATION = IntegerProperty.create("oxidization", 0, 3);
    private float chance;

    public OxidizingBlock(Properties properties, float chance) {
        super(properties);
        this.chance = chance;
        registerDefaultState(defaultBlockState().setValue(OXIDIZATION, 0));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(OXIDIZATION));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return super.isRandomlyTicking(state) || state.getValue(OXIDIZATION) < 3;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (worldIn.getRandom()
                .nextFloat() <= chance) {
            int currentState = state.getValue(OXIDIZATION);
            boolean canIncrease = false;
            LinkedList<Integer> neighbors = new LinkedList<>();
            for (Direction facing : Iterate.directions) {
                BlockPos neighbourPos = pos.relative(facing);
                if (!worldIn.isAreaLoaded(neighbourPos, 0))
                    continue;
                if (!worldIn.isLoaded(neighbourPos))
                    continue;
                BlockState neighborState = worldIn.getBlockState(neighbourPos);
                if (neighborState.hasProperty(OXIDIZATION) && neighborState.getValue(OXIDIZATION) != 0) {
                    neighbors.add(neighborState.getValue(OXIDIZATION));
                }
                if (BlockHelper.hasBlockSolidSide(neighborState, worldIn, neighbourPos, facing.getOpposite())) {
                    continue;
                }
                canIncrease = true;
            }
            if (canIncrease) {
                OptionalDouble average = neighbors.stream()
                        .mapToInt(v -> v)
                        .average();
                if (average.orElse(3d) >= currentState)
                    worldIn.setBlockAndUpdate(pos, state.setValue(OXIDIZATION, Math.min(currentState + 1, 3)));
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isClientSide()){

            if(player.getItemInHand(handIn).getItem() == Items.HONEYCOMB){
                System.out.println("Used a honeycomb item on copper block.");
                if (!player.isCreative())
                    player.getItemInHand(handIn).shrink(1);
                return ActionResultType.CONSUME;
            }
            if (state.getValue(OXIDIZATION) > 0 && player.getItemInHand(handIn)
                    .getItem() instanceof AxeItem) {
                if (!player.isCreative())
                    player.getItemInHand(handIn)
                            .hurtAndBreak(1, player, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
                worldIn.setBlockAndUpdate(pos, state.setValue(OXIDIZATION, state.getValue(OXIDIZATION)-1));
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.PASS;
        }
        return ActionResultType.FAIL;
    }


    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        float chance = 0.00f;
        if(chance < rand.nextFloat()) {
            worldIn.addParticle(ParticleTypes.END_ROD, pos.getX() + rand.nextDouble(),
                    pos.getY() + 0.5D, pos.getZ() + rand.nextDouble(),
                    0d,0.05d,0d);

            worldIn.addParticle(new BlockParticleData(ModParticleTypes.BLOCK, stateIn), pos.getX() + rand.nextDouble(),
                    pos.getY() + 0.5D, pos.getZ() + rand.nextDouble(),
                    0.0D, 0.05D, 0.0D);
        }

        super.animateTick(stateIn, worldIn, pos, rand);
    }
}
