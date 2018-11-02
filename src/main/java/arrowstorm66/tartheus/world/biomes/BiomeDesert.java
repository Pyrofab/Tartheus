package arrowstorm66.tartheus.world.biomes;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.config.ConfigEntity;
import arrowstorm66.tartheus.entity.EntityLurker;
import arrowstorm66.tartheus.entity.EntityScorpion;
import arrowstorm66.tartheus.entity.EntitySolifugae;
import arrowstorm66.tartheus.entity.EntityVinegaroon;
import arrowstorm66.tartheus.world.features.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeDesert extends BiomeTartheus {

	private final boolean isShrubland;
	private static final WorldGenAlderShrub ALDER_SHRUB = new WorldGenAlderShrub();

	public BiomeDesert(boolean isShrubland, final Biome.BiomeProperties par1) {
		super(par1);
		this.isShrubland = isShrubland;
		this.topBlock = MBlocks.TARTHEUS_SAND.getDefaultState();
		this.fillerBlock = MBlocks.TARTHEUS_SAND.getDefaultState();
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

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return ALDER_SHRUB;
	}

	public void decorate(final World par1World, final Random par2Random, final BlockPos pos) {
		super.decorate(par1World, par2Random, pos);
		if (isShrubland) {
			if (par2Random.nextInt(30) == 0) {
				int l1 = par2Random.nextInt(16) + 8;
				int i6 = par2Random.nextInt(16) + 8;
				new WorldGenSplashPatches(MBlocks.QUICKSAND, MBlocks.TARTHEUS_SAND, 6).generate(par1World, par2Random,
						par1World.getTopSolidOrLiquidBlock(pos.add(l1, 0, i6)));
			}
			if ((par2Random.nextInt(2) == 0) && (par2Random.nextInt(10 / 1) == 0)) {
				int k = par2Random.nextInt(16) + 8;
				int l = par2Random.nextInt(16) + 8;
				BlockPos blockpos = par1World.getHeight(pos.add(k, 0, l));
				new WorldGenYuccaPlant().generate(par1World, par2Random, blockpos);
			}
			if (par2Random.nextInt(55) == 0) {
				int k = par2Random.nextInt(16) + 8;
				int l = par2Random.nextInt(16) + 8;
				BlockPos blockpos = par1World.getHeight(pos.add(k, 0, l));
				new WorldGenScorpionBarrenRock(MBlocks.DESERTSTONE, MBlocks.TARTHEUS_SAND, 3).generate(par1World,
						par2Random, blockpos);
			}
			if (par2Random.nextInt(18) == 0) {
				int k = par2Random.nextInt(16) + 8;
				int l = par2Random.nextInt(16) + 8;
				BlockPos blockpos = par1World.getHeight(pos.add(k, 0, l));
				new WorldGenBarrenRock(MBlocks.DESERTSTONE, MBlocks.TARTHEUS_SAND, 2).generate(par1World, par2Random,
						blockpos);
			}
			for (int j3 = 0; j3 < 4; ++j3) {
				int k7 = par2Random.nextInt(16) + 8;
				int j11 = par2Random.nextInt(16) + 8;
				int l14 = par1World.getHeight(pos.add(k7, 0, j11)).getY() * 2;

				if (l14 > 0) {
					int i18 = par2Random.nextInt(l14);
					(new WorldGenDeadShrub()).generate(par1World, par2Random, pos.add(k7, i18, j11));
				}
			}
			for (int j = 0; j < 2; ++j) {
				final int j13 = par2Random.nextInt(16) + 8;
				final int l4 = par2Random.nextInt(16) + 8;
				final int k10 = (par1World.getHeight(pos.add(j13, 0, l4)).getY() * 2) + 8;
				if (k10 > 0) {
					final int l5 = par2Random.nextInt(k10);
					new WorldGenSingleFlower(MBlocks.ELDERSTEM).generate(par1World, par2Random, pos.add(j13, l5, l4));
				}
			}
			for (int j3 = 0; j3 < 10; ++j3) {
				final int j13 = par2Random.nextInt(16) + 8;
				final int l4 = par2Random.nextInt(16) + 8;
				final int k10 = par1World.getHeight(pos.add(j13, 0, l4)).getY() * 2;
				if (k10 > 0) {
					final int l5 = par2Random.nextInt(k10);
					new WorldGenMonactus().generate(par1World, par2Random, pos.add(j13, l5, l4));
				}
			}
		} else {
			if (par2Random.nextInt(30) == 0) {
				int l1 = par2Random.nextInt(16) + 8;
				int i6 = par2Random.nextInt(16) + 8;
				new WorldGenSplashPatches(MBlocks.QUICKSAND, MBlocks.TARTHEUS_SAND, 6).generate(par1World, par2Random,
						par1World.getTopSolidOrLiquidBlock(pos.add(l1, 0, i6)));
			}
			if (par2Random.nextInt(55) == 0) {
				int k = par2Random.nextInt(16) + 8;
				int l = par2Random.nextInt(16) + 8;
				BlockPos blockpos = par1World.getHeight(pos.add(k, 0, l));
				new WorldGenScorpionBarrenRock(MBlocks.DESERTSTONE, MBlocks.TARTHEUS_SAND, 2).generate(par1World,
						par2Random, blockpos);
			}
			if (par2Random.nextInt(18) == 0) {
				int k = par2Random.nextInt(16) + 8;
				int l = par2Random.nextInt(16) + 8;
				BlockPos blockpos = par1World.getHeight(pos.add(k, 0, l));
				new WorldGenBarrenRock(MBlocks.DESERTSTONE, MBlocks.TARTHEUS_SAND, 2).generate(par1World, par2Random,
						blockpos);
			}
			for (int j3 = 0; j3 < 4; ++j3) {
				int k7 = par2Random.nextInt(16) + 8;
				int j11 = par2Random.nextInt(16) + 8;
				int l14 = par1World.getHeight(pos.add(k7, 0, j11)).getY() * 2;

				if (l14 > 0) {
					int i18 = par2Random.nextInt(l14);
					(new WorldGenDeadShrub()).generate(par1World, par2Random, pos.add(k7, i18, j11));
				}
			}
			for (int j = 0; j < 2; ++j) {
				final int j13 = par2Random.nextInt(16) + 8;
				final int l4 = par2Random.nextInt(16) + 8;
				final int k10 = (par1World.getHeight(pos.add(j13, 0, l4)).getY() * 2) + 8;
				if (k10 > 0) {
					final int l5 = par2Random.nextInt(k10);
					new WorldGenSingleFlower(MBlocks.ELDERSTEM).generate(par1World, par2Random, pos.add(j13, l5, l4));
				}
			}
			for (int j3 = 0; j3 < 10; ++j3) {
				final int j13 = par2Random.nextInt(16) + 8;
				final int l4 = par2Random.nextInt(16) + 8;
				final int k10 = par1World.getHeight(pos.add(j13, 0, l4)).getY() * 2;
				if (k10 > 0) {
					final int l5 = par2Random.nextInt(k10);
					new WorldGenMonactus().generate(par1World, par2Random, pos.add(j13, l5, l4));
				}
			}
		}
		if (isShrubland) {
			this.decorator.treesPerChunk = 1;
		} else {
			this.decorator.treesPerChunk = -999;
		}
	}
}