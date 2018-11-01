package arrowstorm66.tartheus.world.biomes;

import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.world.biome.*;
import java.util.*;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.world.features.WorldGenRoundPatches;
import arrowstorm66.tartheus.world.features.WorldGenSkull;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.*;
import net.minecraft.world.chunk.*;
import net.minecraft.util.math.*;
import net.minecraft.block.material.*;
import net.minecraft.init.*;
import net.minecraft.block.*;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;

public class BiomeTartheus extends Biome {

	public BiomeTartheus(final Biome.BiomeProperties props) {
		super(props);
		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
	}
	
	@Override
	public void decorate(final World par1World, final Random par2Random, final BlockPos pos) {
		super.decorate(par1World, par2Random, pos);
		for (int j = 0; j < 8; ++j) {
			final int veinSize = 2 + par2Random.nextInt(3);
			final int x = par2Random.nextInt(16);
			final int y = 33 + par2Random.nextInt(66 - 33);
			final int z = par2Random.nextInt(16);
			new WorldGenMinable(MBlocks.UMBRALLIUM_ORE.getDefaultState(), veinSize,
					state -> state != null && state == MBlocks.STONE_SECOND_STRATUM.getStateFromMeta(2)).generate(par1World,
							par2Random, pos.add(x, y, z));
		}
		for (int j = 0; j < 8; ++j) {
			final int veinSize = 2 + par2Random.nextInt(3);
			final int x = par2Random.nextInt(16);
			final int y = 33 + par2Random.nextInt(66 - 33);
			final int z = par2Random.nextInt(16);
			new WorldGenMinable(MBlocks.STELLARIUM_ORE.getDefaultState(), veinSize,
					state -> state != null && state == MBlocks.STONE_SECOND_STRATUM.getStateFromMeta(2)).generate(par1World,
							par2Random, pos.add(x, y, z));
		}
		for (int j = 0; j < 14; ++j) {
			final int veinSize = 2 + par2Random.nextInt(3);
			final int x = par2Random.nextInt(16);
			final int y = 33 + par2Random.nextInt(66 - 33);
			final int z = par2Random.nextInt(16);
			new WorldGenMinable(MBlocks.ATLASITE_ORE.getDefaultState(), veinSize,
					state -> state != null && state == MBlocks.STONE_SECOND_STRATUM.getStateFromMeta(2)).generate(par1World,
							par2Random, pos.add(x, y, z));
		}
		for (int j = 0; j < 18; ++j) {
			final int veinSize = 5 + par2Random.nextInt(4);
			final int x = par2Random.nextInt(16);
			final int y = 1 + par2Random.nextInt(99 - 1);
			final int z = par2Random.nextInt(16);
			new WorldGenMinable(MBlocks.BRIGHTSTONE_ORE.getDefaultState(), veinSize,
					state -> state != null && state == MBlocks.STONE_FIRST_STRATUM.getStateFromMeta(2)).generate(par1World,
							par2Random, pos.add(x, y, z));
		}
	}

	public void genTerrainBlocks(final World world, final Random rand, final ChunkPrimer primer, final int x,
			final int z, final double noiseVal) {
		this.genTartheusBiomeTerrain(world, rand, primer, x, z, noiseVal);
	}

	protected void genTartheusBiomeTerrain(final World worldIn, final Random rand, final ChunkPrimer chunkPrimerIn,
			final int x, final int z, final double noiseVal) {
		final int i = worldIn.getSeaLevel();
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate2 = this.fillerBlock;
		int j = -1;
		final int k = (int) (noiseVal / 3.0 + 3.0 + rand.nextDouble() * 0.25);
		final int l = x & 0xF;
		final int i2 = z & 0xF;
		new BlockPos.MutableBlockPos();
		for (int j2 = 255; j2 >= 0; --j2) {
			if (j2 <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(i2, j2, l, MBlocks.TARTHEUS_BEDROCK.getDefaultState());
			} else {
				final IBlockState iblockstate3 = chunkPrimerIn.getBlockState(i2, j2, l);
				if (iblockstate3.getMaterial() == Material.AIR) {
					j = -1;
				} else if (iblockstate3.getBlock() == MBlocks.STONE_FIRST_STRATUM
						|| iblockstate3.getBlock() == MBlocks.STONE_SECOND_STRATUM) {
					if (j == -1) {
						if (k <= 0) {
							iblockstate = Blocks.AIR.getDefaultState();
							iblockstate2 = MBlocks.STONE_FIRST_STRATUM.getDefaultState();
						} else if (j2 >= i - 4 && j2 <= i + 1) {
							iblockstate = this.topBlock;
							iblockstate2 = this.fillerBlock;
						}
						j = k;
						if (j2 >= i - 1) {
							chunkPrimerIn.setBlockState(i2, j2, l, iblockstate);
						} else if (j2 < i - 7 - k) {
							iblockstate = Blocks.AIR.getDefaultState();
							iblockstate2 = MBlocks.STONE_FIRST_STRATUM.getDefaultState();
							chunkPrimerIn.setBlockState(i2, j2, l, MBlocks.STONE_FIRST_STRATUM.getDefaultState());
						} else {
							chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						if (j == 0 && (iblockstate2.getBlock() == MBlocks.TARTHEUS_SAND) && k > 1) {
							j = rand.nextInt(4) + Math.max(0, j2 - 88);
							iblockstate2 = MBlocks.TARTHEUS_SANDSTONE.getDefaultState();
						}
					}
				}
			}
		}
	}
}