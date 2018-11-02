package arrowstorm66.tartheus.world.features;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.blocks.BlockMonactusPlant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenMonactus extends WorldGenerator {
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		for (int i = 0; i < 10; ++i) {
			BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4),
					rand.nextInt(8) - rand.nextInt(8));
			if (worldIn.isAirBlock(blockpos)) {
				if (MBlocks.MONACTUS_PLANT.canBlockStay(worldIn, blockpos, MBlocks.MONACTUS_PLANT.getDefaultState())) {
					IBlockState monactusState = MBlocks.MONACTUS_PLANT.getDefaultState()
							.withProperty(BlockMonactusPlant.MONACTUS_AGE, 2);
					worldIn.setBlockState(blockpos, monactusState, 2);
				}
			}
		}
		return true;
	}
}