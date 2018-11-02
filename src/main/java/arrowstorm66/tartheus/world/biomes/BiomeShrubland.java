package arrowstorm66.tartheus.world.biomes;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.config.ConfigEntity;
import arrowstorm66.tartheus.entity.EntityLurker;
import arrowstorm66.tartheus.entity.EntityScorpion;
import arrowstorm66.tartheus.entity.EntitySolifugae;
import arrowstorm66.tartheus.entity.EntityVinegaroon;
import arrowstorm66.tartheus.world.features.WorldGenAlderShrub;
import arrowstorm66.tartheus.world.features.WorldGenGrass;
import arrowstorm66.tartheus.world.features.WorldGenLakeMineral;
import arrowstorm66.tartheus.world.features.WorldGenRoundPatches;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeShrubland extends BiomeTartheus {

	private static final WorldGenAlderShrub ALDER_SHRUB = new WorldGenAlderShrub();

	public BiomeShrubland(final Biome.BiomeProperties par1) {
		super(par1);
		this.topBlock = MBlocks.TARTHEUS_COARSE_DIRT.getDefaultState();
		this.fillerBlock = MBlocks.TARTHEUS_DIRT.getDefaultState();
		this.decorator.treesPerChunk = 7;
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

	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return (rand.nextInt(3) > 0 ? new WorldGenGrass(MBlocks.TARTHEUS_SHORTGRASS)
				: new WorldGenGrass(MBlocks.TARTHEUS_TALLGRASS));
	}

	public void decorate(final World par1World, final Random par2Random, final BlockPos pos) {
		super.decorate(par1World, par2Random, pos);
		for (int i1 = 0; i1 < 2; ++i1) {
			int l1 = par2Random.nextInt(16) + 8;
			int i6 = par2Random.nextInt(16) + 8;
			new WorldGenRoundPatches(MBlocks.TARTHEUS_GRASS, MBlocks.TARTHEUS_COARSE_DIRT, 10).generate(par1World,
					par2Random, par1World.getTopSolidOrLiquidBlock(pos.add(l1, 0, i6)));
		}
		for (int i = 0; i < 3; ++i) {
			int j = par2Random.nextInt(16) + 8;
			int k = par2Random.nextInt(16) + 8;
			new WorldGenLakeMineral(MBlocks.TARTHEUS_SAND, 7).generate(par1World, par2Random,
					par1World.getTopSolidOrLiquidBlock(pos.add(j, 0, k)));
		}
		double d0 = GRASS_COLOR_NOISE.getValue((double) (pos.getX() + 8) / 200.0D, (double) (pos.getZ() + 8) / 200.0D);
		if (d0 < -0.8D) {
			this.decorator.grassPerChunk = 7;
		} else {
			this.decorator.grassPerChunk = 10;
		}
	}
}