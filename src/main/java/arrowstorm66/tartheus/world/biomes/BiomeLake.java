package arrowstorm66.tartheus.world.biomes;

import net.minecraft.world.biome.*;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.init.*;
import net.minecraft.world.*;
import java.util.*;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.config.ConfigEntity;
import arrowstorm66.tartheus.entity.EntityLurker;
import arrowstorm66.tartheus.entity.EntityScorpion;
import arrowstorm66.tartheus.entity.EntityVinegaroon;
import arrowstorm66.tartheus.world.features.TartheusLakes;
import arrowstorm66.tartheus.world.features.WorldGenBarrenRock;
import arrowstorm66.tartheus.world.features.WorldGenBarrenwoodTree;
import arrowstorm66.tartheus.world.features.WorldGenDeadShrub;
import arrowstorm66.tartheus.world.features.WorldGenGrass;
import arrowstorm66.tartheus.world.features.WorldGenLakeMineral;
import arrowstorm66.tartheus.world.features.WorldGenMonactus;
import arrowstorm66.tartheus.world.features.WorldGenRoundPatches;
import arrowstorm66.tartheus.world.features.WorldGenScorpionBarrenRock;
import arrowstorm66.tartheus.world.features.WorldGenSingleFlower;
import arrowstorm66.tartheus.world.features.WorldGenSplashPatches;
import arrowstorm66.tartheus.world.features.WorldGenYuccaPlant;
import net.minecraft.util.math.*;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.relauncher.*;

public class BiomeLake extends BiomeTartheus {

	public BiomeLake(final Biome.BiomeProperties par1) {
		super(par1);
		this.topBlock = MBlocks.TARTHEUS_SAND.getDefaultState();
		this.fillerBlock = MBlocks.TARTHEUS_SAND.getDefaultState();
		this.decorator.treesPerChunk = -999;
		this.decorator.grassPerChunk = -999;
	}
	
	@Override
	public void genTerrainBlocks(final World world, final Random rand, final ChunkPrimer primer, final int x,
			final int z, final double noiseVal) {
		this.genLakeBiomeTerrain(world, rand, primer, x, z, noiseVal);
	}

	protected void genLakeBiomeTerrain(final World worldIn, final Random rand, final ChunkPrimer chunkPrimerIn,
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
							iblockstate2 = MBlocks.TARTHEUS_SAND.getDefaultState();
						} else if (j2 >= i - 4 && j2 <= i + 1) {
							iblockstate = this.topBlock;
							iblockstate2 = this.fillerBlock;
						}
						j = k;
						if (j2 >= i - 1) {
							chunkPrimerIn.setBlockState(i2, j2, l, iblockstate);
						} else if (j2 < i - 7 - k) {
							iblockstate = Blocks.AIR.getDefaultState();
							iblockstate2 = MBlocks.TARTHEUS_SAND.getDefaultState();
							chunkPrimerIn.setBlockState(i2, j2, l, MBlocks.TARTHEUS_SAND.getDefaultState());
						} else {
							chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						if (j == 0 && (iblockstate2.getBlock() == MBlocks.TARTHEUS_SAND) && k > 1) {
							j = rand.nextInt(4) + Math.max(0, j2 - 66);
							iblockstate2 = MBlocks.TARTHEUS_SANDSTONE.getDefaultState();
						}
					}
				}
			}
		}
	}
}