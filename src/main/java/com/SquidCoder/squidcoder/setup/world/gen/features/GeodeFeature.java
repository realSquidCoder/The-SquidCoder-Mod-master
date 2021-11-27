package com.SquidCoder.squidcoder.setup.world.gen.features;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class GeodeFeature extends Feature<NoFeatureConfig> {

	private static final BlockState STONE = Blocks.STONE.defaultBlockState();
	private static final BlockState AIR = Blocks.AIR.defaultBlockState();
	private int radius;


	public GeodeFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@SuppressWarnings("deprecation")
	public boolean isAirOrLeaves(IWorldGenerationBaseReader reader, BlockPos pos) {
		if (!(reader instanceof IWorldReader)) {
			return reader.isStateAtPosition(pos, state -> state.isAir() || state.is(BlockTags.LEAVES));
		} else {
			return reader.isStateAtPosition(pos, state -> state.canBeReplacedByLeaves((IWorldReader) reader, pos));
		}
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
    	System.out.println("geode placed in world at: " + pos.toShortString());

		while (pos.getY() > 1 && isAirOrLeaves(reader, pos)) {
			pos = pos.below();
		}
		//move up by (1 + radius) blocks to not generate in surface.
		pos = pos.above(radius + 1);

		//make sphere of stone
		for(double X = -radius; X < radius; X++ ) {
			  for(double Y = -radius; Y < radius; Y++ ) {
				  for(double Z = -radius; Z < radius; Z++ ) {
			          if(Math.sqrt((X * X) + (Y * Y) + (Z * Z)) <= radius) {
			            setBlock(reader, new BlockPos(pos.getX() + X,pos.getY() + Y,pos.getZ() + Z), AIR);
			          }
			      }
			  }
		}
		radius--;

	    //Carve out the sphere
	    for(double Y = -radius; Y < radius; Y++ ) {
	      for(double X = -radius; X < radius; X++ ) {
	        for(double Z = -radius; Z < radius; Z++ ) {
	          if(Math.sqrt((X * X) + (Y * Y) + (Z * Z)) <= radius) {
	            setBlock(reader, new BlockPos(pos.getX() + X,pos.getY() + Y,pos.getZ() + Z), AIR);
	          }
	        }
	      }
	    }

	    radius--;
		return false;
	}

}