package arrowstorm66.tartheus.world.biomes;

import java.util.Arrays;
import java.util.Random;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.config.ConfigEntity;
import arrowstorm66.tartheus.config.ConfigMisc;
import arrowstorm66.tartheus.entity.EntityLurker;
import arrowstorm66.tartheus.entity.EntityScorpion;
import arrowstorm66.tartheus.entity.EntitySolifugae;
import arrowstorm66.tartheus.entity.EntityVinegaroon;
import arrowstorm66.tartheus.world.features.WorldGenBarrenRock;
import arrowstorm66.tartheus.world.features.WorldGenDeadShrub;
import arrowstorm66.tartheus.world.features.WorldGenGrass;
import arrowstorm66.tartheus.world.features.WorldGenMonactus;
import arrowstorm66.tartheus.world.features.WorldGenScorpionBarrenRock;
import arrowstorm66.tartheus.world.features.WorldGenSingleFlower;
import arrowstorm66.tartheus.world.features.WorldGenYuccaPlant;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeBadlands extends BiomeTartheus {
	protected static final IBlockState COARSE_DIRT = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT,
			BlockDirt.DirtType.COARSE_DIRT);
	protected static final IBlockState GRASS = Blocks.GRASS.getDefaultState();
	protected static final IBlockState HARDENED_CLAY = MBlocks.TERRACOTTA.getDefaultState();
	protected static final IBlockState STAINED_HARDENED_CLAY = MBlocks.TERRACOTTA_PALE.getDefaultState();
	protected static final IBlockState ORANGE_STAINED_HARDENED_CLAY = MBlocks.TERRACOTTA_TANGERINE.getDefaultState();
	protected static final IBlockState RED_SAND = MBlocks.HELL_SAND.getDefaultState();
	private IBlockState[] clayBands;
	private long worldSeed;
	private NoiseGeneratorPerlin pillarNoise;
	private NoiseGeneratorPerlin pillarRoofNoise;
	private NoiseGeneratorPerlin clayBandsOffsetNoise;
	private final boolean pillars;

	public BiomeBadlands(boolean pillars, Biome.BiomeProperties properties) {
		super(properties);
		this.pillars = pillars;
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
		this.topBlock = RED_SAND;
		this.fillerBlock = STAINED_HARDENED_CLAY;
	}

	public void decorate(final World par1World, final Random par2Random, final BlockPos pos) {
		super.decorate(par1World, par2Random, pos);
		for (int j3 = 0; j3 < 4; ++j3) {
			int k7 = par2Random.nextInt(16) + 8;
			int j11 = par2Random.nextInt(16) + 8;
			int l14 = par1World.getHeight(pos.add(k7, 0, j11)).getY() * 2;

			if (l14 > 0) {
				int i18 = par2Random.nextInt(l14);
				(new WorldGenDeadShrub()).generate(par1World, par2Random, pos.add(k7, i18, j11));
			}
		}
		for (int j3 = 0; j3 < 5; ++j3) {
			final int j13 = par2Random.nextInt(16) + 8;
			final int l4 = par2Random.nextInt(16) + 8;
			final int k10 = par1World.getHeight(pos.add(j13, 0, l4)).getY() * 2;
			if (k10 > 0) {
				final int l5 = par2Random.nextInt(k10);
				new WorldGenMonactus().generate(par1World, par2Random, pos.add(j13, l5, l4));
			}
		}
	}

	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		if (this.clayBands == null || this.worldSeed != worldIn.getSeed()) {
			this.generateBands(worldIn.getSeed());
		}

		if (this.pillarNoise == null || this.pillarRoofNoise == null || this.worldSeed != worldIn.getSeed()) {
			Random random = new Random(this.worldSeed);
			this.pillarNoise = new NoiseGeneratorPerlin(random, 4);
			this.pillarRoofNoise = new NoiseGeneratorPerlin(random, 1);
		}

		this.worldSeed = worldIn.getSeed();
		double d4 = 0.0D;

		if (this.pillars) {
			int i = (x & -16) + (z & 15);
			int j = (z & -16) + (x & 15);
			double d0 = Math.min(Math.abs(noiseVal), this.pillarNoise.getValue((double) i * 0.25D, (double) j * 0.25D));

			if (d0 > 0.0D) {
				double d1 = 0.001953125D;
				double d2 = Math
						.abs(this.pillarRoofNoise.getValue((double) i * 0.001953125D, (double) j * 0.001953125D));
				d4 = d0 * d0 * 2.5D;
				double d3 = Math.ceil(d2 * 50.0D) + 14.0D;

				if (d4 > d3) {
					d4 = d3;
				}

				d4 = d4 + 90.0D;
			}
		}

		int k1 = x & 15;
		int l1 = z & 15;
		int i2 = worldIn.getSeaLevel();
		IBlockState iblockstate = STAINED_HARDENED_CLAY;
		IBlockState iblockstate3 = this.fillerBlock;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		boolean flag = Math.cos(noiseVal / 3.0D * Math.PI) > 0.0D;
		int l = -1;
		boolean flag1 = false;
		int i1 = 0;

		for (int j1 = 255; j1 >= 0; --j1) {
			if (chunkPrimerIn.getBlockState(l1, j1, k1).getMaterial() == Material.AIR && j1 < (int) d4) {
				chunkPrimerIn.setBlockState(l1, j1, k1, MBlocks.STONE_FIRST_STRATUM.getDefaultState());
			}

			if (j1 <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(l1, j1, k1, MBlocks.TARTHEUS_BEDROCK.getDefaultState());
			} else if (i1 < 15 || this.pillars) {
				IBlockState iblockstate1 = chunkPrimerIn.getBlockState(l1, j1, k1);

				if (iblockstate1.getMaterial() == Material.AIR) {
					l = -1;
				} else if (iblockstate1.getBlock() == MBlocks.STONE_FIRST_STRATUM) {
					if (l == -1) {
						flag1 = false;

						if (k <= 0) {
							iblockstate = AIR;
							iblockstate3 = MBlocks.STONE_FIRST_STRATUM.getDefaultState();
						} else if (j1 >= i2 - 4 && j1 <= i2 + 1) {
							iblockstate = STAINED_HARDENED_CLAY;
							iblockstate3 = this.fillerBlock;
						}

						if (j1 < i2 && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
							iblockstate = WATER;
						}

						l = k + Math.max(0, j1 - i2);

						if (j1 >= i2 - 1) {
							if (j1 > i2 + 3 + k) {
								IBlockState iblockstate2;

								if (j1 >= 90 && j1 <= 179) {
									if (flag) {
										iblockstate2 = HARDENED_CLAY;
									} else {
										iblockstate2 = this.getBand(x, j1, z);
									}
								} else {
									iblockstate2 = ORANGE_STAINED_HARDENED_CLAY;
								}

								chunkPrimerIn.setBlockState(l1, j1, k1, iblockstate2);
							} else {
								chunkPrimerIn.setBlockState(l1, j1, k1, this.topBlock);
								flag1 = true;
							}
						} else {
							chunkPrimerIn.setBlockState(l1, j1, k1, iblockstate3);

							if (iblockstate3.getBlock() == Blocks.STAINED_HARDENED_CLAY) {
								chunkPrimerIn.setBlockState(l1, j1, k1, ORANGE_STAINED_HARDENED_CLAY);
							}
						}
					} else if (l > 0) {
						--l;

						if (flag1) {
							chunkPrimerIn.setBlockState(l1, j1, k1, ORANGE_STAINED_HARDENED_CLAY);
						} else {
							chunkPrimerIn.setBlockState(l1, j1, k1, this.getBand(x, j1, z));
						}
					}

					++i1;
				}
			}
		}
	}

	public void generateBands(long p_150619_1_) {
		this.clayBands = new IBlockState[90];
		Arrays.fill(this.clayBands, HARDENED_CLAY);
		Random random = new Random(p_150619_1_);
		this.clayBandsOffsetNoise = new NoiseGeneratorPerlin(random, 1);

		for (int l1 = 0; l1 < 90; ++l1) {
			l1 += random.nextInt(5) + 1;

			if (l1 < 90) {
				this.clayBands[l1] = ORANGE_STAINED_HARDENED_CLAY;
			}
		}

		int j2 = random.nextInt(4) + 2;

		for (int k2 = 0; k2 < j2; ++k2) {
			int i3 = random.nextInt(3) + 2;
			int l3 = random.nextInt(90);

			for (int i1 = 0; l3 + i1 < 90 && i1 < i3; ++i1) {
				this.clayBands[l3 + i1] = MBlocks.TERRACOTTA_HAZEL.getDefaultState();
			}
		}

		int l2 = random.nextInt(4) + 2;

		for (int j3 = 0; j3 < l2; ++j3) {
			int i4 = random.nextInt(3) + 1;
			int k4 = random.nextInt(90);

			for (int j1 = 0; k4 + j1 < 90 && j1 < i4; ++j1) {
				this.clayBands[k4 + j1] = MBlocks.TERRACOTTA_CRIMSON.getDefaultState();
			}
		}

		int k3 = random.nextInt(3) + 3;
		int j4 = 0;

		for (int l4 = 0; l4 < k3; ++l4) {
			int i5 = 1;
			j4 += random.nextInt(16) + 4;

			for (int k1 = 0; j4 + k1 < 90 && k1 < 1; ++k1) {
				this.clayBands[j4 + k1] = MBlocks.TERRACOTTA_PALE.getDefaultState();

				if (j4 + k1 > 1 && random.nextBoolean()) {
					this.clayBands[j4 + k1 - 1] = MBlocks.TERRACOTTA_GRAY.getDefaultState();
				}

				if (j4 + k1 < 89 && random.nextBoolean()) {
					this.clayBands[j4 + k1 + 1] = MBlocks.TERRACOTTA_GRAY.getDefaultState();
				}
			}
		}
	}

	public IBlockState getBand(int p_180629_1_, int p_180629_2_, int p_180629_3_) {
		int i = (int) Math
				.round(this.clayBandsOffsetNoise.getValue((double) p_180629_1_ / 512.0D, (double) p_180629_1_ / 512.0D)
						* 2.0D);
		return this.clayBands[(p_180629_2_ + i + 90) % 90];
	}
}