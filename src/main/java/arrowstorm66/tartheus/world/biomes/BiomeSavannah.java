package arrowstorm66.tartheus.world.biomes;

import java.util.Random;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.config.ConfigEntity;
import arrowstorm66.tartheus.entity.EntityLurker;
import arrowstorm66.tartheus.entity.EntityScorpion;
import arrowstorm66.tartheus.entity.EntitySolifugae;
import arrowstorm66.tartheus.entity.EntityVinegaroon;
import arrowstorm66.tartheus.world.features.WorldGenBarrenwoodTree;
import arrowstorm66.tartheus.world.features.WorldGenGrass;
import arrowstorm66.tartheus.world.features.WorldGenLakeMineral;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeSavannah extends BiomeTartheus {
	private static final WorldGenBarrenwoodTree BARRENWOOD_TREE = new WorldGenBarrenwoodTree(false);

	public BiomeSavannah(Biome.BiomeProperties properties) {
		super(properties);
		this.topBlock = MBlocks.TARTHEUS_GRASS.getDefaultState();
		this.fillerBlock = MBlocks.TARTHEUS_DIRT.getDefaultState();
		this.decorator.grassPerChunk = 20;
		this.decorator.treesPerChunk = 1;
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

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return (WorldGenAbstractTree) (rand.nextInt(15) > 0 ? BARRENWOOD_TREE : TREE_FEATURE);
	}

	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return (rand.nextInt(5) > 0 ? new WorldGenGrass(MBlocks.TARTHEUS_TALLGRASS)
				: new WorldGenGrass(MBlocks.TARTHEUS_SHORTGRASS));
	}

	public void decorate(final World par1World, final Random par2Random, final BlockPos pos) {
		super.decorate(par1World, par2Random, pos);
		for (int i = 0; i < 3; ++i) {
			int j = par2Random.nextInt(16) + 8;
			int k = par2Random.nextInt(16) + 8;
			new WorldGenLakeMineral(MBlocks.TARTHEUS_SAND, 7).generate(par1World, par2Random,
					par1World.getTopSolidOrLiquidBlock(pos.add(j, 0, k)));
		}
		for (int j = 0; j < 15; ++j) {
			final int veinSize = 2 + par2Random.nextInt(3);
			final int x = par2Random.nextInt(16);
			final int y = 33 + par2Random.nextInt(66 - 33);
			final int z = par2Random.nextInt(16);
			new WorldGenMinable(MBlocks.UMBRALLIUM_ORE.getDefaultState(), veinSize,
					state -> state != null && state == MBlocks.STONE_SECOND_STRATUM.getStateFromMeta(2)).generate(par1World,
							par2Random, pos.add(x, y, z));
		}
	}
}