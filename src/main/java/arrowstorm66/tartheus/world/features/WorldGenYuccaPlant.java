package arrowstorm66.tartheus.world.features;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.blocks.BlockYuccaPlant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenYuccaPlant extends WorldGenerator {
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		for (int i = 0; i < 64; ++i) {
			BlockPos blockpos = position.add(0, rand.nextInt(4) - rand.nextInt(4), 0);
			if (worldIn.isAirBlock(blockpos)
					&& (worldIn.getBlockState(blockpos.down()).getBlock() == MBlocks.TARTHEUS_SAND
							|| worldIn.getBlockState(blockpos.down()).getBlock() == MBlocks.HELL_SAND)) {
				IBlockState yuccaTop = MBlocks.YUCCA_PLANT.getDefaultState().withProperty(BlockYuccaPlant.YUCCA_AGE,
						11);
				IBlockState yuccaMid = MBlocks.YUCCA_PLANT.getDefaultState().withProperty(BlockYuccaPlant.YUCCA_AGE,
						10);
				IBlockState yuccaBot = MBlocks.YUCCA_PLANT.getDefaultState().withProperty(BlockYuccaPlant.YUCCA_AGE,
						9);
				worldIn.setBlockState(blockpos.up(2), yuccaTop, 2);
				worldIn.setBlockState(blockpos.up(1), yuccaMid, 2);
				worldIn.setBlockState(blockpos, yuccaBot, 2);
			}
		}

		return true;
	}
}