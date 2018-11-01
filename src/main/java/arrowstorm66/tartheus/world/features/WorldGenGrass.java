package arrowstorm66.tartheus.world.features;

import java.util.Random;

import arrowstorm66.tartheus.MBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGrass extends WorldGenerator {
	private final BlockBush block;

	public WorldGenGrass(BlockBush block) {
		this.block = block;
	}

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		for (IBlockState iblockstate = worldIn
				.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position))
						&& position.getY() > 0; iblockstate = worldIn.getBlockState(position)) {
			position = position.down();
		}

		for (int i = 0; i < 128; ++i) {
			BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4),
					rand.nextInt(8) - rand.nextInt(8));

			if (worldIn.isAirBlock(blockpos) && blockpos.getY() < worldIn.getHeight() - 1 && block.canBlockStay(worldIn, blockpos, block.getDefaultState())) {
				worldIn.setBlockState(blockpos, block.getDefaultState(), 2);
			}
		}

		return true;
	}
}