package arrowstorm66.tartheus.world.features;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.blocks.BlockFlameJet;
import arrowstorm66.tartheus.blocks.BlockFlameJet.FireJetVariant;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenFlameJet extends WorldGenerator {
	private final FireJetVariant variant;

	public WorldGenFlameJet(final FireJetVariant variant) {
		this.variant = variant;
	}

	public boolean generate(final World world, final Random rand, final BlockPos pos) {
		final BlockPos dPos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4),
				rand.nextInt(8) - rand.nextInt(8));
		if (world.isAirBlock(dPos) && world.canSeeSky(dPos)
				&& world.getBlockState(dPos.down()).getMaterial() == Material.ROCK
				&& world.getBlockState(dPos.east().down()).getMaterial() == Material.ROCK
				&& world.getBlockState(dPos.west().down()).getMaterial() == Material.ROCK
				&& world.getBlockState(dPos.south().down()).getMaterial() == Material.ROCK
				&& world.getBlockState(dPos.north().down()).getMaterial() == Material.ROCK) {
			world.setBlockState(dPos.down(), MBlocks.FLAME_JET.getDefaultState()
					.withProperty(BlockFlameJet.VARIANT, this.variant), 0);
		}
		return true;
	}
}
