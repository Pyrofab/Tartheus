package arrowstorm66.tartheus.world;

import arrowstorm66.tartheus.MBiomes;
import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.world.features.TartheusCave;
import arrowstorm66.tartheus.world.features.TartheusLakes;
import arrowstorm66.tartheus.world.features.TartheusRavine;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class TartheusChunkGenerator implements IChunkGenerator {
	private final Random rand;
	private NoiseGeneratorOctaves minLimitPerlinNoise;
	private NoiseGeneratorOctaves maxLimitPerlinNoise;
	private NoiseGeneratorOctaves mainPerlinNoise;
	private NoiseGeneratorPerlin surfaceNoise;
	public NoiseGeneratorOctaves scaleNoise;
	public NoiseGeneratorOctaves depthNoise;
	public NoiseGeneratorOctaves forestNoise;
	private final World world;
	private final boolean mapFeaturesEnabled;
	private final WorldType terrainType;
	private final double[] heightMap;
	private final float[] biomeWeights;
	private IBlockState oceanBlock;
	private double[] depthBuffer;
	private MapGenBase caveGenerator = new TartheusCave();
	private MapGenBase ravineGenerator = new TartheusRavine();
	private Biome[] biomesForGeneration;
	double[] mainNoiseRegion;
	double[] minLimitRegion;
	double[] maxLimitRegion;
	double[] depthRegion;
	private float depthBaseSize;

	public TartheusChunkGenerator(final World worldIn, final long seed, final boolean mapFeaturesEnabledIn) {
		worldIn.setSeaLevel(90);
		this.oceanBlock = Blocks.WATER.getDefaultState();
		this.depthBuffer = new double[256];
		this.caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, InitMapGenEvent.EventType.CAVE);
		this.ravineGenerator = TerrainGen.getModdedMapGen(ravineGenerator, InitMapGenEvent.EventType.RAVINE);
		this.world = worldIn;
		this.mapFeaturesEnabled = mapFeaturesEnabledIn;
		this.terrainType = worldIn.getWorldInfo().getTerrainType();
		this.rand = new Random(seed);
		this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
		this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
		this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
		this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
		this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
		this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
		this.forestNoise = new NoiseGeneratorOctaves(this.rand, 8);
		this.heightMap = new double[825];
		this.biomeWeights = new float[25];
		this.depthBaseSize = 12.8f;
		for (int i = -2; i <= 2; ++i) {
			for (int j = -2; j <= 2; ++j) {
				float f = 10.0F / MathHelper.sqrt((float) (i * i + j * j) + 0.2F);
				this.biomeWeights[i + 2 + (j + 2) * 5] = f;
			}
		}
		InitNoiseGensEvent.ContextOverworld ctx = new InitNoiseGensEvent.ContextOverworld(this.minLimitPerlinNoise,
				this.maxLimitPerlinNoise, this.mainPerlinNoise, this.surfaceNoise, this.scaleNoise, this.depthNoise,
				this.forestNoise);
		ctx = (InitNoiseGensEvent.ContextOverworld) TerrainGen.getModdedNoiseGenerators(worldIn, this.rand,
				(InitNoiseGensEvent.Context) ctx);
		this.minLimitPerlinNoise = ctx.getLPerlin1();
		this.maxLimitPerlinNoise = ctx.getLPerlin2();
		this.mainPerlinNoise = ctx.getPerlin();
		this.surfaceNoise = ctx.getHeight();
		this.scaleNoise = ctx.getScale();
		this.depthNoise = ctx.getDepth();
		this.forestNoise = ctx.getForest();
	}

	public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
		this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration,
				x * 4 - 2, z * 4 - 2, 10, 10);
		this.generateHeightmap(x * 4, 0, z * 4);

		for (int i = 0; i < 4; ++i) {
			int j = i * 5;
			int k = (i + 1) * 5;

			for (int l = 0; l < 4; ++l) {
				int i1 = (j + l) * 33;
				int j1 = (j + l + 1) * 33;
				int k1 = (k + l) * 33;
				int l1 = (k + l + 1) * 33;

				for (int i2 = 0; i2 < 32; ++i2) {
					double d0 = 0.125D;
					double d1 = this.heightMap[i1 + i2];
					double d2 = this.heightMap[j1 + i2];
					double d3 = this.heightMap[k1 + i2];
					double d4 = this.heightMap[l1 + i2];
					double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
					double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
					double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
					double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;

					for (int j2 = 0; j2 < 8; ++j2) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * 0.25D;
						double d13 = (d4 - d2) * 0.25D;

						for (int k2 = 0; k2 < 4; ++k2) {
							double d14 = 0.25D;
							double d16 = (d11 - d10) * 0.25D;
							double lvt_45_1_ = d10 - d16;

							for (int l2 = 0; l2 < 4; ++l2) {
								if ((lvt_45_1_ += d16) > 0.0D) {
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2,
											MBlocks.STONE_FIRST_STRATUM.getDefaultState());
								} else if (i2 * 8 + j2 < world.getSeaLevel()) {
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.oceanBlock);
								}
								if ((primer.getBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2) == MBlocks.STONE_FIRST_STRATUM
										.getDefaultState()) && (i2 * 8 + j2 < 66 + rand.nextInt(6))) {
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2,
											MBlocks.STONE_SECOND_STRATUM.getDefaultState());
								}
								if ((primer.getBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2) == MBlocks.STONE_SECOND_STRATUM
										.getDefaultState()) && (i2 * 8 + j2 < 33 + rand.nextInt(6))) {
									primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2,
											MBlocks.STONE_THIRD_STRATUM.getDefaultState());
								}
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	private void generateHeightmap(final int p_185978_1_, final int p_185978_2_, final int p_185978_3_) {
		this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, p_185978_1_, p_185978_3_, 5, 5, 200.0,
				200.0, 0.5);
		this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, p_185978_1_, p_185978_2_,
				p_185978_3_, 5, 33, 5, 8.555150000000001, 4.277575000000001, 8.555150000000001);
		this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, p_185978_1_,
				p_185978_2_, p_185978_3_, 5, 33, 5, 684.412, 684.412, 684.412);
		this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, p_185978_1_,
				p_185978_2_, p_185978_3_, 5, 33, 5, 684.412, 684.412, 684.412);
		int i = 0;
		int j = 0;
		for (int k = 0; k < 5; ++k) {
			for (int l = 0; l < 5; ++l) {
				float f2 = 0.0f;
				float f3 = 0.0f;
				float f4 = 0.0f;
				final int i2 = 2;
				final Biome biome = this.biomesForGeneration[k + 2 + (l + 2) * 10];
				for (int j2 = -2; j2 <= 2; ++j2) {
					for (int k2 = -2; k2 <= 2; ++k2) {
						final Biome biome2 = this.biomesForGeneration[k + j2 + 2 + (l + k2 + 2) * 10];
						float f5 = biome2.getBaseHeight();
						float f6 = biome2.getHeightVariation();
						if (this.terrainType == WorldType.AMPLIFIED && f5 > 0.0f) {
							f5 = 1.0f + f5 * 2.0f;
							f6 = 1.0f + f6 * 4.0f;
						}
						float f7 = this.biomeWeights[j2 + 2 + (k2 + 2) * 5] / (f5 + 2.0f);
						if (biome2.getBaseHeight() > biome.getBaseHeight()) {
							f7 /= 2.0f;
						}
						f2 += f6 * f7;
						f3 += f5 * f7;
						f4 += f7;
					}
				}
				f2 /= f4;
				f3 /= f4;
				f2 = f2 * 0.9f + 0.1f;
				f3 = (f3 * 4.0f - 1.0f) / 8.0f;
				double d7 = this.depthRegion[j] / 8000.0;
				if (d7 < 0.0) {
					d7 = -d7 * 0.3;
				}
				d7 = d7 * 3.0 - 2.0;
				if (d7 < 0.0) {
					d7 /= 2.0;
					if (d7 < -1.0) {
						d7 = -1.0;
					}
					d7 /= 1.4;
					d7 /= 2.0;
				} else {
					if (d7 > 1.0) {
						d7 = 1.0;
					}
					d7 /= 8.0;
				}
				++j;
				double d8 = f3;
				final double d9 = f2;
				d8 += d7 * 0.2;
				d8 = d8 * this.depthBaseSize / 8.0;
				final double d10 = this.depthBaseSize + d8 * 4.0;
				for (int l2 = 0; l2 < 33; ++l2) {
					double d11 = (l2 - d10) * 12.0 * 128.0 / 256.0 / d9;
					if (d11 < 0.0) {
						d11 *= 4.0;
					}
					final double d12 = this.minLimitRegion[i] / 512.0;
					final double d13 = this.maxLimitRegion[i] / 512.0;
					final double d14 = (this.mainNoiseRegion[i] / 10.0 + 1.0) / 2.0;
					double d15 = MathHelper.clampedLerp(d12, d13, d14) - d11;
					if (l2 > 29) {
						final double d16 = (l2 - 29) / 3.0f;
						d15 = d15 * (1.0 - d16) + -10.0 * d16;
					}
					this.heightMap[i] = d15;
					++i;
				}
			}
		}
	}

	public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn) {
		if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.world))
			return;
		double d0 = 0.03125D;
		this.depthBuffer = this.surfaceNoise.getRegion(this.depthBuffer, (double) (x * 16), (double) (z * 16), 16, 16,
				0.0625D, 0.0625D, 1.0D);

		for (int i = 0; i < 16; ++i) {
			for (int j = 0; j < 16; ++j) {
				Biome biome = biomesIn[j + i * 16];
				biome.genTerrainBlocks(this.world, this.rand, primer, x * 16 + i, z * 16 + j,
						this.depthBuffer[j + i * 16]);
			}
		}
	}

	public Chunk loadChunk(final int par1, final int par2) {
		return this.generateChunk(par1, par2);
	}

	public Chunk generateChunk(final int x, final int z) {
		this.rand.setSeed(x * 341873128712L + z * 132897987541L);
		final ChunkPrimer chunkprimer = new ChunkPrimer();
		this.setBlocksInChunk(x, z, chunkprimer);
		this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16,
				16);
		this.replaceBiomeBlocks(x, z, chunkprimer, this.biomesForGeneration);
		this.caveGenerator.generate(this.world, x, z, chunkprimer);
		this.ravineGenerator.generate(this.world, x, z, chunkprimer);

		final Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
		final byte[] abyte = chunk.getBiomeArray();
		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
		}
		chunk.generateSkylightMap();
		return chunk;
	}

	public void populate(final int x, final int z) {
		BlockFalling.fallInstantly = true;
		final int i = x * 16;
		final int j = z * 16;
		BlockPos blockpos = new BlockPos(i, 0, j);
		final Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
		this.rand.setSeed(this.world.getSeed());
		final long k = this.rand.nextLong() / 2L * 2L + 1L;
		final long l = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed(x * k + z * l ^ this.world.getSeed());
		boolean flag = false;
		final ChunkPos chunkpos = new ChunkPos(x, z);
		ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, flag);
		if (!flag && biome != MBiomes.DESERT && biome != MBiomes.DESERT_HILLS && biome != MBiomes.DESERT_SHRUBLAND
				&& biome != MBiomes.BADLANDS && biome != MBiomes.BADLANDS_SPIRES && biome != MBiomes.BADLANDS_PLATEAU
				&& biome != MBiomes.BONEYARD && biome != MBiomes.BONEYARD_HILLS && biome != MBiomes.VOLCANO
				&& biome != MBiomes.VOLCANO_PLATEAU && biome != MBiomes.RIVER && biome != MBiomes.LAKE && !flag
				&& this.rand.nextInt(8) == 0)
			if (TerrainGen.populate(this, this.world, this.rand, x, z, flag,
					PopulateChunkEvent.Populate.EventType.LAKE)) {
				int i1 = this.rand.nextInt(16) + 8;
				int j1 = this.rand.nextInt(256);
				int k1 = this.rand.nextInt(16) + 8;
				new TartheusLakes(Blocks.WATER).generate(this.world, this.rand, blockpos.add(i1, j1, k1));
			}
		if (!flag && (biome == MBiomes.VOLCANO || biome == MBiomes.VOLCANO_PLATEAU) && !flag
				&& this.rand.nextInt(4) == 0)
			if (TerrainGen.populate(this, this.world, this.rand, x, z, flag,
					PopulateChunkEvent.Populate.EventType.LAKE)) {
				int i1 = this.rand.nextInt(16) + 8;
				int j1 = this.rand.nextInt(256);
				int k1 = this.rand.nextInt(16) + 8;
				new TartheusLakes(Blocks.LAVA).generate(this.world, this.rand, blockpos.add(i1, j1, k1));
			}
		if (!flag && biome != MBiomes.SHRUBLAND && biome != MBiomes.SHRUBLAND_HILLS && biome != MBiomes.SAVANNA
				&& biome != MBiomes.SAVANNA_PLATEAU && this.rand.nextInt(80 / 10) == 0)
			if (TerrainGen.populate(this, this.world, this.rand, x, z, flag,
					PopulateChunkEvent.Populate.EventType.LAVA)) {
				int i2 = this.rand.nextInt(16) + 8;
				int l2 = this.rand.nextInt(this.rand.nextInt(248) + 8);
				int k3 = this.rand.nextInt(16) + 8;

				if (l2 < this.world.getSeaLevel() || this.rand.nextInt(80 / 8) == 0) {
					new TartheusLakes(Blocks.LAVA).generate(this.world, this.rand, blockpos.add(i2, l2, k3));
				}
			}
		biome.decorate(this.world, this.rand, blockpos);
		ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, flag);
		BlockFalling.fallInstantly = false;
	}

	public List<Biome.SpawnListEntry> getPossibleCreatures(final EnumCreatureType creatureType, final BlockPos pos) {
		final Biome biome = this.world.getBiome(pos);
		return biome.getSpawnableList(creatureType);
	}

	public void recreateStructures(final Chunk chunkIn, final int x, final int z) {
	}

	@Nullable
	public BlockPos getNearestStructurePos(final World worldIn, final String structureName, final BlockPos position,
			final boolean p_180513_4_) {
		return null;
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}

	public boolean generateStructures(final Chunk chunkIn, final int x, final int z) {
		return false;
	}
}