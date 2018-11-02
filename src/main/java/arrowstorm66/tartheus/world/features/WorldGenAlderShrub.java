package arrowstorm66.tartheus.world.features;

import arrowstorm66.tartheus.MBlocks;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

import java.util.Random;

public class WorldGenAlderShrub extends WorldGenTrees {
	private static final IBlockState TRUNK = MBlocks.ALDER_LOG.getDefaultState();
	private static final IBlockState LEAF = MBlocks.ALDER_LEAVES.getDefaultState()
			.withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.FALSE);

    public WorldGenAlderShrub() {
		super(false);
	}

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		for (IBlockState iblockstate = worldIn
				.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position)
						|| iblockstate.getBlock().isLeaves(iblockstate, worldIn, position))
						&& position.getY() > 0; iblockstate = worldIn.getBlockState(position)) {
			position = position.down();
		}

		IBlockState state = worldIn.getBlockState(position);

		if (state.getBlock().canSustainPlant(state, worldIn, position, EnumFacing.UP, MBlocks.ALDER_SAPLING)) {
			position = position.up();
			this.setBlockAndNotifyAdequately(worldIn, position, TRUNK);

			for (int i = position.getY(); i <= position.getY() + 2; ++i) {
				int j = i - position.getY();
				int k = 2 - j;

				for (int l = position.getX() - k; l <= position.getX() + k; ++l) {
					int i1 = l - position.getX();

					for (int j1 = position.getZ() - k; j1 <= position.getZ() + k; ++j1) {
						int k1 = j1 - position.getZ();

						if (Math.abs(i1) != k || Math.abs(k1) != k || rand.nextInt(2) != 0) {
							BlockPos blockpos = new BlockPos(l, i, j1);
							state = worldIn.getBlockState(blockpos);

							if (state.getBlock().canBeReplacedByLeaves(state, worldIn, blockpos)) {
								this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF);
							}
						}
					}
				}
			}
		}
		return true;
	}
}