package arrowstorm66.tartheus.world.features;

import java.util.Random;

import arrowstorm66.tartheus.MBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.oredict.OreDictionary;

public class WorldGenSkull extends WorldGenerator {

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		for (int i = 0; i < 64; ++i) {
			BlockPos blockpos = position.add(0, rand.nextInt(4) - rand.nextInt(4), 0);

			if (worldIn.isAirBlock(blockpos) && (worldIn.getBlockState(blockpos.down()).getBlock() == MBlocks.BONE_MUSH
					|| worldIn.getBlockState(blockpos.down()).getBlock() == MBlocks.BONE_PILE)) {
				worldIn.setBlockState(blockpos, Blocks.SKULL.getDefaultState().withProperty(BlockSkull.FACING, EnumFacing.UP), 2);
				TileEntitySkull te = (TileEntitySkull) worldIn.getTileEntity(blockpos);
				if (te != null) {
					te.setSkullRotation(rand.nextInt(16));
				}
			}
		}

		return true;
	}
}