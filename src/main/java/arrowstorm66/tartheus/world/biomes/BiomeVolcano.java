package arrowstorm66.tartheus.world.biomes;

import net.minecraft.world.biome.*;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.*;
import net.minecraft.world.*;
import java.util.*;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.blocks.BlockFlameJet.FireJetVariant;
import arrowstorm66.tartheus.config.ConfigEntity;
import arrowstorm66.tartheus.entity.EntityLurker;
import arrowstorm66.tartheus.entity.EntityScorpion;
import arrowstorm66.tartheus.entity.EntitySolifugae;
import arrowstorm66.tartheus.entity.EntityVinegaroon;
import arrowstorm66.tartheus.world.features.TartheusLakes;
import arrowstorm66.tartheus.world.features.WorldGenBarrenRock;
import arrowstorm66.tartheus.world.features.WorldGenFlameJet;
import arrowstorm66.tartheus.world.features.WorldGenLakeMineral;
import arrowstorm66.tartheus.world.features.WorldGenRoundPatches;
import arrowstorm66.tartheus.world.features.WorldGenSingleFlower;
import arrowstorm66.tartheus.world.features.WorldGenSkull;
import arrowstorm66.tartheus.world.features.WorldGenSplashPatches;
import net.minecraft.util.math.*;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.relauncher.*;

public class BiomeVolcano extends BiomeTartheus {

	public BiomeVolcano(final Biome.BiomeProperties par1) {
		super(par1);
		this.topBlock = MBlocks.BASALT.getDefaultState();
		this.fillerBlock = MBlocks.BASALT.getDefaultState();
        this.decorator.grassPerChunk = -999;
        this.decorator.treesPerChunk = -999;
        if (ConfigEntity.isScorpionEnabled == true) {
			this.spawnableMonsterList.add(new Biome.SpawnListEntry((Class) EntityScorpion.class, 40, 1, 3));
		}
		if (ConfigEntity.isVinegaroonEnabled == true) {
			this.spawnableMonsterList.add(new Biome.SpawnListEntry((Class) EntityVinegaroon.class, 5, 0, 1));
		}
		if (ConfigEntity.isSolifugaeEnabled == true) {
			this.spawnableMonsterList.add(new Biome.SpawnListEntry((Class) EntitySolifugae.class, 20, 0, 1));
		}
		if (ConfigEntity.isLurkerEnabled == true) {
			this.spawnableMonsterList.add(new Biome.SpawnListEntry((Class) EntityLurker.class, 1, 0, 1));
		}
	}
	
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        return MathHelper.hsvToRGB(0, 0, 0);
    }

	public void decorate(final World par1World, final Random par2Random, final BlockPos pos) {
		super.decorate(par1World, par2Random, pos);
		for (int i1 = 0; i1 < 1; ++i1) {
			int l1 = par2Random.nextInt(16) + 8;
			int i6 = par2Random.nextInt(16) + 8;
			new WorldGenSplashPatches(MBlocks.ASH, MBlocks.BASALT, 4).generate(par1World, par2Random,
					par1World.getTopSolidOrLiquidBlock(pos.add(l1, 0, i6)));
		}
		final WorldGenFlameJet genFireJet = new WorldGenFlameJet(FireJetVariant.JET_IDLE);
        for (int l = 0; l < 35; ++l) {
            final int m = pos.getX() + par2Random.nextInt(14) + 8;
            final int k10 = 100 + par2Random.nextInt(150 - 100);
            final int k2 = pos.getZ() + par2Random.nextInt(14) + 8;
            genFireJet.generate(par1World, par2Random, new BlockPos(m, k10, k2));
        }
	}
	
	@Override
	public void genTerrainBlocks(final World world, final Random rand, final ChunkPrimer primer, final int x,
			final int z, final double noiseVal) {
		this.genVolcanoBiomeTerrain(world, rand, primer, x, z, noiseVal);
	}
	
	protected void genVolcanoBiomeTerrain(final World worldIn, final Random rand, final ChunkPrimer chunkPrimerIn,
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
							iblockstate2 = MBlocks.BASALT.getDefaultState();
						} else if (j2 >= i - 4 && j2 <= i + 1) {
							iblockstate = this.topBlock;
							iblockstate2 = this.fillerBlock;
						}
						j = k;
						if (j2 >= i - 1) {
							chunkPrimerIn.setBlockState(i2, j2, l, iblockstate);
						} else if (j2 < i - 7 - k) {
							iblockstate = Blocks.AIR.getDefaultState();
							iblockstate2 = MBlocks.BASALT.getDefaultState();
							chunkPrimerIn.setBlockState(i2, j2, l, MBlocks.BASALT.getDefaultState());
						} else {
							chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						if (j == 0 && (iblockstate2.getBlock() == MBlocks.BASALT) && k > 1) {
							j = rand.nextInt(4) + Math.max(0, j2 - 100);
							iblockstate2 = MBlocks.BASALT.getDefaultState();
						}
					}
				}
			}
		}
	}
}