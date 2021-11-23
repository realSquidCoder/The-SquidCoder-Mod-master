package com.SquidCoder.squidcoder.data.custom.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class OxidizingBlock3 extends Block{
    private final float chance = 64f / 1125f;
    private static final IntegerProperty OXIDIZATION = IntegerProperty.create("oxidization", 0, 3);
    private final OxidationLevel oxiLevel;

    public OxidizingBlock3(Properties properties, OxidationLevel oxiLevel) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(OXIDIZATION, oxiLevel.getOxidationState()));
        this.oxiLevel = oxiLevel;
    }


    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return super.isRandomlyTicking(state) || getOxiLevel(state) < 3;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isClientSide()){

            if(player.getItemInHand(handIn).getItem() == Items.HONEYCOMB){
                System.out.println("Trigger Wax Event");
                if (!player.isCreative())
                    player.getItemInHand(handIn).shrink(1);
                worldIn.addParticle(ParticleTypes.END_ROD, pos.getX() + Math.random(),
                        pos.getY() + 1.5D, pos.getZ() + Math.random(),
                        0d,0.05d,0d);
                return ActionResultType.CONSUME;
            }
            if (getOxiLevel(state) > 0 && player.getItemInHand(handIn)
                    .getItem() instanceof AxeItem) {
                if (!player.isCreative())
                    player.getItemInHand(handIn)
                            .hurtAndBreak(1, player, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
                worldIn.setBlockAndUpdate(pos, getPreviousState(state));
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.PASS;
        }
        return ActionResultType.FAIL;
    }

    private int getOxiLevel(BlockState state) {return state.getValue(OXIDIZATION);}

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState stateIn, ServerWorld worldIn, BlockPos pos, Random randIn) {
        if(randIn.nextFloat() > chance){
            System.out.println("Block at pos: " + pos.toShortString()+" entered pre-oxidation state.");
            boolean willOxidize = runPreOxiTests(pos, stateIn, worldIn);
            if(willOxidize){
                worldIn.setBlockAndUpdate(pos, getNextState(stateIn));
            }
        }
        super.randomTick(stateIn, worldIn, pos, randIn);
    }

    private boolean runPreOxiTests(BlockPos posIn, BlockState stateIn, ServerWorld worldIn) {
        for(int y=-4; y<=4;y++){
            for(int x=-4; x<=4; x++){
                for(int z=-4; z<=4;z++){
                    BlockPos checkPos = new BlockPos(x,y,z);
                    if(checkPos.equals(posIn)){
                        continue;
                    }
                    if(getOxiLevel(worldIn.getBlockState(checkPos)) > getOxiLevel(stateIn)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private BlockState getNextState(BlockState currentState) {
        if(getOxiLevel(currentState) < 3){
            return this.oxiLevel.getNextBlock().get().defaultBlockState();
        }else {
            return currentState;
        }
    }
    private BlockState getPreviousState(BlockState currentState) {
        if(getOxiLevel(currentState) > 0){
            return this.oxiLevel.getOldBlock().get().defaultBlockState();
        }
        return currentState;
    }
}
