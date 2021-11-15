package com.SquidCoder.squidcoder.data.custom.blocks;

import com.SquidCoder.squidcoder.setup.ModParticleTypes;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.Random;

public class OxidizingBlock3 extends Block {
    private BlockState currentState;
    private ArrayList<BlockState> possibleStates = new ArrayList<>(4);
    private final float chance = 64f / 1125f;

    public OxidizingBlock3(Properties properties, float chance) {
        super(properties);
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

    private int getOxiLevel(BlockState state) {
        return possibleStates.indexOf(state.getBlockState());
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

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState stateIn, ServerWorld worldIn, BlockPos pos, Random randIn) {
        if(randIn.nextFloat() > chance){
            System.out.println("Block at pos: " + pos.toShortString()+" entered pre-oxidation state.");
            runPreOxiTests(pos, stateIn, worldIn);
        }
        super.randomTick(stateIn, worldIn, pos, randIn);
    }

    private void runPreOxiTests(BlockPos pos, BlockState stateIn, ServerWorld worldIn) {
        for(int y=-4; y<=4;y++){
            for(int x=-4; x<=4; x++){
                for(int z=-4; z<=4;z++){
                    if(y==0 && x==0 && z==0){
                        continue;
                    }
                    Vector3i chkPos = new Vector3i(x,y,z);
                    //System.out.println("ChkPos:" + chkPos + ". Info: " + pos.distManhattan(chkPos));

                }
            }
        }
    }

    private BlockState getNextState(BlockState currentState) {
        int oxidationValue = possibleStates.indexOf(currentState);
        if(oxidationValue < 3){
            return possibleStates.get(oxidationValue+1);
        }
        return this.currentState;
    }
    private BlockState getPreviousState(BlockState currentState) {
        int oxidationValue = possibleStates.indexOf(currentState);
        if(oxidationValue > 0){
            return possibleStates.get(oxidationValue-1);
        }
        return this.currentState;
    }
}
