package arrowstorm66.tartheus.world.biomes;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.config.ConfigEntity;
import arrowstorm66.tartheus.entity.EntityLurker;
import arrowstorm66.tartheus.entity.EntityScorpion;
import arrowstorm66.tartheus.entity.EntitySolifugae;
import arrowstorm66.tartheus.entity.EntityVinegaroon;
import arrowstorm66.tartheus.world.features.WorldGenRoundPatches;
import arrowstorm66.tartheus.world.features.WorldGenSkull;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BiomeBoneyard extends BiomeTartheus {

	public BiomeBoneyard(final Biome.BiomeProperties par1) {
		super(par1);
		this.topBlock = MBlocks.BONE_PILE.getDefaultState();
		this.fillerBlock = MBlocks.BONE_PILE.getDefaultState();
        this.decorator.grassPerChunk = -999;
        this.decorator.treesPerChunk = -999;
        if (ConfigEntity.isScorpionEnabled) {
			this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityScorpion.class, 40, 1, 3));
		}
		if (ConfigEntity.isVinegaroonEnabled) {
			this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityVinegaroon.class, 5, 0, 1));
		}
		if (ConfigEntity.isSolifugaeEnabled) {
			this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntitySolifugae.class, 20, 0, 1));
		}
		if (ConfigEntity.isLurkerEnabled) {
			this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityLurker.class, 1, 0, 1));
		}
	}
	
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        return MathHelper.hsvToRGB(0, 0, 0);
    }

	public void decorate(final World par1World, final Random par2Random, final BlockPos pos) {
		super.decorate(par1World, par2Random, pos);
		for (int i1 = 0; i1 < 3; ++i1) {
			int l1 = par2Random.nextInt(16) + 8;
			int i6 = par2Random.nextInt(16) + 8;
			new WorldGenRoundPatches(MBlocks.BONE_MUSH, MBlocks.BONE_PILE, 8).generate(par1World, par2Random,
					par1World.getTopSolidOrLiquidBlock(pos.add(l1, 0, i6)));
		}
		for (int j3 = 0; j3 < 4; ++j3) {
			final int j13 = par2Random.nextInt(16) + 8;
			final int l4 = par2Random.nextInt(16) + 8;
			final int k10 = 100 + par2Random.nextInt(130 - 100);
			new WorldGenBush(MBlocks.POINTYBONES).generate(par1World, par2Random, pos.add(j13, k10, l4));
		}
		for (int j3 = 0; j3 < 3; ++j3) {
			final int j13 = par2Random.nextInt(16) + 8;
			final int l4 = par2Random.nextInt(16) + 8;
			final int k10 = 100 + par2Random.nextInt(130 - 100);
			new WorldGenSkull().generate(par1World, par2Random, pos.add(j13, k10, l4));
		}
	}
	
	@Override
	public void genTerrainBlocks(final World world, final Random rand, final ChunkPrimer primer, final int x,
			final int z, final double noiseVal) {
		this.genBoneyardBiomeTerrain(world, rand, primer, x, z, noiseVal);
	}
	
	protected void genBoneyardBiomeTerrain(final World worldIn, final Random rand, final ChunkPrimer chunkPrimerIn,
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
							iblockstate2 = MBlocks.MARROWSTONE.getDefaultState();
						} else if (j2 >= i - 4 && j2 <= i + 1) {
							iblockstate = this.topBlock;
							iblockstate2 = this.fillerBlock;
						}
						j = k;
						if (j2 >= i - 1) {
							chunkPrimerIn.setBlockState(i2, j2, l, iblockstate);
						} else if (j2 < i - 7 - k) {
							iblockstate = Blocks.AIR.getDefaultState();
							iblockstate2 = MBlocks.MARROWSTONE.getDefaultState();
							chunkPrimerIn.setBlockState(i2, j2, l, MBlocks.MARROWSTONE.getDefaultState());
						} else {
							chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						chunkPrimerIn.setBlockState(i2, j2, l, iblockstate2);
						if (j == 0 && (iblockstate2.getBlock() == MBlocks.BONE_PILE) && k > 1) {
							j = rand.nextInt(4) + Math.max(0, j2 - 100);
							iblockstate2 = MBlocks.MARROWSTONE.getDefaultState();
						}
					}
				}
			}
		}
	}
}