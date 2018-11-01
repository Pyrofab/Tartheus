package arrowstorm66.tartheus;

import javax.annotation.Nullable;

import arrowstorm66.tartheus.base.BasicBlock;
import arrowstorm66.tartheus.base.BasicBlockCrop;
import arrowstorm66.tartheus.base.BasicBlockDoor;
import arrowstorm66.tartheus.base.BasicBlockFalling;
import arrowstorm66.tartheus.base.BasicBlockFence;
import arrowstorm66.tartheus.base.BasicBlockFlammable;
import arrowstorm66.tartheus.base.BasicBlockGrass;
import arrowstorm66.tartheus.base.BasicBlockOre;
import arrowstorm66.tartheus.base.BasicBlockOreTransparent;
import arrowstorm66.tartheus.base.BasicBlockPane;
import arrowstorm66.tartheus.base.BasicBlockSlab;
import arrowstorm66.tartheus.base.BasicBlockStairs;
import arrowstorm66.tartheus.base.BasicBlockTallGrass;
import arrowstorm66.tartheus.base.BasicBlockTorch;
import arrowstorm66.tartheus.base.BasicBlockTransparent;
import arrowstorm66.tartheus.base.BasicBlockUnbreakable;
import arrowstorm66.tartheus.blocks.BlockAlderLeaves;
import arrowstorm66.tartheus.blocks.BlockAlderLog;
import arrowstorm66.tartheus.blocks.BlockAlderSapling;
import arrowstorm66.tartheus.blocks.BlockAsh;
import arrowstorm66.tartheus.blocks.BlockBarrenwoodLeaves;
import arrowstorm66.tartheus.blocks.BlockBarrenwoodLog;
import arrowstorm66.tartheus.blocks.BlockBarrenwoodSapling;
import arrowstorm66.tartheus.blocks.BlockDeadShrub;
import arrowstorm66.tartheus.blocks.BlockFlameJet;
import arrowstorm66.tartheus.blocks.BlockFlower;
import arrowstorm66.tartheus.blocks.BlockMonactusPlant;
import arrowstorm66.tartheus.blocks.BlockPointyBones;
import arrowstorm66.tartheus.blocks.BlockQuicksand;
import arrowstorm66.tartheus.blocks.BlockScorpionSpawner;
import arrowstorm66.tartheus.blocks.BlockStoneFurnace;
import arrowstorm66.tartheus.blocks.BlockYuccaPlant;
import arrowstorm66.tartheus.world.portal.BlockTartheusPortal;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@ObjectHolder(Tartheus.MODID)
public class MBlocks {

	@ObjectHolder("tartheus_portal")
	public static BlockTartheusPortal TARTHEUS_PORTAL = new BlockTartheusPortal(Material.PORTAL, "tartheus_portal",
			MDimensions.tartheusDimensionID, 100, 0).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("scorpion_spawner")
	public static BlockScorpionSpawner SCORPION_SPAWNER = new BlockScorpionSpawner("scorpion_spawner")
			.setSoundType(MMaterialSounds.METAL);
	@ObjectHolder("brightstone_torch")
	public static BasicBlockTorch BRIGHTSTONE_TORCH = new BasicBlockTorch("brightstone_torch")
			.setSoundType(SoundType.WOOD);
	@ObjectHolder("stone_furnace")
	public static BlockStoneFurnace STONE_FURNACE = new BlockStoneFurnace("stone_furnace", 2.0f, "pickaxe", 1, false)
			.setSoundType(MMaterialSounds.STONE);
	@ObjectHolder("stone_furnace_active")
	public static BlockStoneFurnace STONE_FURNACE_ACTIVE = new BlockStoneFurnace("stone_furnace_active", 2.0f,
			"pickaxe", 1, true).setSoundType(MMaterialSounds.STONE);

	@ObjectHolder("brightstone_ore")
	public static BasicBlockOre BRIGHTSTONE_ORE = new BasicBlockOre(Material.ROCK, "ore_brightstone", 3.0f, "pickaxe",
			0, MItems.BRIGHTSTONE).setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("atlasite_ore")
	public static BasicBlockOre ATLASITE_ORE = new BasicBlockOre(Material.ROCK, "ore_atlasite", 3.0f, "pickaxe", 1,
			MItems.RAW_ATLASITE).setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("stellarium_ore")
	public static BasicBlockOre STELLARIUM_ORE = new BasicBlockOre(Material.ROCK, "ore_stellarium", 3.0f, "pickaxe", 1,
			MItems.RAW_STELLARIUM).setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("umbrallium_ore")
	public static BasicBlockOreTransparent UMBRALLIUM_ORE = new BasicBlockOreTransparent(Material.ROCK,
			"ore_umbrallium", 3.0f, "pickaxe", 1, MItems.RAW_UMBRALLIUM).setSoundType(MMaterialSounds.STONE)
					.setCreativeTab(MCreativeTabs.T_BLOCKS);

	@ObjectHolder("tartheus_tallgrass")
	public static BasicBlockTallGrass TARTHEUS_TALLGRASS = new BasicBlockTallGrass("tartheus_tallgrass", 0.82f,
			"machete", 0, MBlocks.TARTHEUS_TALLGRASS).setSoundType(MMaterialSounds.PLANT)
					.setCreativeTab(MCreativeTabs.T_DECORATION);
	@ObjectHolder("tartheus_shortgrass")
	public static BasicBlockTallGrass TARTHEUS_SHORTGRASS = new BasicBlockTallGrass("tartheus_shortgrass", 0.82f,
			"machete", 0, MBlocks.TARTHEUS_SHORTGRASS).setSoundType(MMaterialSounds.PLANT)
					.setCreativeTab(MCreativeTabs.T_DECORATION);
	@ObjectHolder("tartheus_grass")
	public static BasicBlock TARTHEUS_GRASS = new BasicBlockGrass(Material.GRASS, "tartheus_grass", 0.82f, "shovel", 0)
			.setSoundType(MMaterialSounds.PLANT).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("tartheus_coarse_dirt")
	public static BasicBlock TARTHEUS_COARSE_DIRT = new BasicBlockGrass(Material.GRASS, "tartheus_coarse_dirt", 0.82f,
			"shovel", 0).setSoundType(MMaterialSounds.PLANT).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("tartheus_dirt")
	public static BasicBlock TARTHEUS_DIRT = new BasicBlock(Material.GROUND, "tartheus_dirt", 0.82f, "shovel", 0)
			.setSoundType(MMaterialSounds.GROUND).setCreativeTab(MCreativeTabs.T_BLOCKS);

	@ObjectHolder("alder_sapling")
	public static BlockAlderSapling ALDER_SAPLING = new BlockAlderSapling("alder_sapling", 0.22f, "machete", 0)
			.setSoundType(MMaterialSounds.PLANT).setCreativeTab(MCreativeTabs.T_DECORATION);
	@ObjectHolder("alder_planks")
	public static BasicBlockFlammable ALDER_PLANKS = new BasicBlockFlammable(Material.WOOD, "alder_planks", 2.0f,
			"machete", 0, 20, 5).setSoundType(SoundType.WOOD).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("alder_log")
	public static BlockAlderLog ALDER_LOG = new BlockAlderLog(Material.WOOD, "alder_log", 0.62f, "machete", 0)
			.setSoundType(MMaterialSounds.WOOD).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("alder_leaves")
	public static BlockAlderLeaves ALDER_LEAVES = new BlockAlderLeaves(Material.GRASS, "alder_leaves", 0.22f, "machete",
			0).setSoundType(MMaterialSounds.GROUND).setCreativeTab(MCreativeTabs.T_DECORATION);
	@ObjectHolder("barrenwood_sapling")
	public static BlockBarrenwoodSapling BARRENWOOD_SAPLING = new BlockBarrenwoodSapling("barrenwood_sapling", 0.22f,
			"machete", 0).setSoundType(MMaterialSounds.PLANT).setCreativeTab(MCreativeTabs.T_DECORATION);
	@ObjectHolder("barrenwood_planks")
	public static BasicBlockFlammable BARRENWOOD_PLANKS = new BasicBlockFlammable(Material.WOOD, "barrenwood_planks",
			2.0f, "machete", 0, 20, 5).setSoundType(SoundType.WOOD).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("barrenwood_stairs")
	public static BasicBlockStairs BARRENWOOD_STAIRS = (BasicBlockStairs) BasicBlockStairs
			.createWooden(MBlocks.BARRENWOOD_PLANKS.getDefaultState(), "barrenwood_stairs", 2.0f, "machete", 0)
			.setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("barrenwood_slab")
	public static BasicBlockSlab BARRENWOOD_SLAB = new BasicBlockSlab(MBlocks.BARRENWOOD_PLANKS.getDefaultState(),
			"barrenwood_slab", 2.0f, "machete", 0).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("barrenwood_fence")
	public static BasicBlockFence BARRENWOOD_FENCE = new BasicBlockFence(Material.WOOD, MapColor.BROWN,
			"barrenwood_fence", 2.0f, "machete", 0).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("barrenwood_door")
	public static BasicBlockDoor BARRENWOOD_DOOR = new BasicBlockDoor(Material.WOOD, "barrenwood_door", 3.0f, "machete",
			0).setSoundType(SoundType.WOOD);
	@ObjectHolder("barrenwood_log")
	public static BlockBarrenwoodLog BARRENWOOD_LOG = new BlockBarrenwoodLog(Material.WOOD, "barrenwood_log", 0.62f,
			"machete", 0).setSoundType(MMaterialSounds.WOOD).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("barrenwood_leaves")
	public static BlockBarrenwoodLeaves BARRENWOOD_LEAVES = new BlockBarrenwoodLeaves(Material.GRASS,
			"barrenwood_leaves", 0.22f, "machete", 0).setCreativeTab(MCreativeTabs.T_DECORATION);

	@ObjectHolder("tartheus_glass")
	public static BasicBlockTransparent TARTHEUS_GLASS = new BasicBlockTransparent(Material.GLASS, "tartheus_glass",
			0.2f, "shovel", 0, false).setSoundType(MMaterialSounds.GLASS).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("tartheus_sand")
	public static BasicBlockFalling TARTHEUS_SAND = new BasicBlockFalling(Material.SAND, "tartheus_sand", 0.82f,
			"shovel", 0).setSoundType(MMaterialSounds.SAND).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("quicksand")
	public static BlockQuicksand QUICKSAND = new BlockQuicksand(Material.SAND, "quicksand", 0.7f, "shovel", 0)
			.setSoundType(MMaterialSounds.SAND).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("tartheus_sandstone")
	public static BasicBlock TARTHEUS_SANDSTONE = new BasicBlock(Material.ROCK, "tartheus_sandstone", 0.94f, "pickaxe",
			1).setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("cut_sandstone")
	public static BasicBlock CUT_SANDSTONE = new BasicBlock(Material.ROCK, "cut_sandstone", 1.0f, "pickaxe", 1)
			.setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("cut_sandstone_stairs")
	public static BasicBlockStairs CUT_SANDSTONE_STAIRS = new BasicBlockStairs(MBlocks.CUT_SANDSTONE.getDefaultState(),
			"cut_sandstone_stairs", 1.0f, "pickaxe", 1).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("cut_sandstone_slab")
	public static BasicBlockSlab CUT_SANDSTONE_SLAB = new BasicBlockSlab(MBlocks.CUT_SANDSTONE.getDefaultState(),
			"cut_sandstone_slab", 1.0f, "pickaxe", 1).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("cut_sandstone_door")
	public static BasicBlockDoor CUT_SANDSTONE_DOOR = new BasicBlockDoor(Material.WOOD, "cut_sandstone_door", 3.0f,
			"machete", 0).setSoundType(SoundType.STONE);
	@ObjectHolder("desertstone")
	public static BasicBlock DESERTSTONE = new BasicBlock(Material.ROCK, "desertstone", 1.8f, "pickaxe", 1)
			.setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);

	@ObjectHolder("hell_sand")
	public static BasicBlockFalling HELL_SAND = new BasicBlockFalling(Material.SAND, "hell_sand", 0.82f, "shovel", 0)
			.setSoundType(MMaterialSounds.SAND).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("terracotta")
	public static BasicBlock TERRACOTTA = new BasicBlock(Material.ROCK, "terracotta", 0.94f, "pickaxe", 1)
			.setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("terracotta_crimson")
	public static BasicBlock TERRACOTTA_CRIMSON = new BasicBlock(Material.ROCK, "terracotta_crimson", 0.94f, "pickaxe",
			1).setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("terracotta_tangerine")
	public static BasicBlock TERRACOTTA_TANGERINE = new BasicBlock(Material.ROCK, "terracotta_tangerine", 0.94f,
			"pickaxe", 1).setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("terracotta_hazel")
	public static BasicBlock TERRACOTTA_HAZEL = new BasicBlock(Material.ROCK, "terracotta_hazel", 0.94f, "pickaxe", 1)
			.setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("terracotta_pale")
	public static BasicBlock TERRACOTTA_PALE = new BasicBlock(Material.ROCK, "terracotta_pale", 0.94f, "pickaxe", 1)
			.setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("terracotta_gray")
	public static BasicBlock TERRACOTTA_GRAY = new BasicBlock(Material.ROCK, "terracotta_gray", 0.94f, "pickaxe", 1)
			.setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);

	@ObjectHolder("marrowstone")
	public static BasicBlock MARROWSTONE = new BasicBlock(Material.ROCK, "marrowstone", 1.62f, "pickaxe", 2)
			.setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("bone_pile")
	public static BasicBlock BONE_PILE = new BasicBlock(Material.ROCK, "bone_pile", 0.8f, "pickaxe", 2)
			.setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("bonemush")
	public static BasicBlockFalling BONE_MUSH = new BasicBlockFalling(Material.CLAY, "bone_mush", 0.6f, "shovel", 2)
			.setSoundType(MMaterialSounds.SNOW).setCreativeTab(MCreativeTabs.T_BLOCKS);

	@ObjectHolder("basalt")
	public static BasicBlock BASALT = new BasicBlock(Material.ROCK, "basalt", 2.32f, "pickaxe", 2)
			.setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("ash")
	public static BlockAsh ASH = new BlockAsh(Material.SAND, "ash", 1.62f, "shovel", 2)
			.setSoundType(MMaterialSounds.SAND).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("flame_jet")
	public static BlockFlameJet FLAME_JET = new BlockFlameJet(Material.ROCK, "flame_jet", 1.62f, "shovel", 2);

	@ObjectHolder("stone_first_stratum")
	public static BasicBlock STONE_FIRST_STRATUM = new BasicBlock(Material.ROCK, "stone_first_stratum", 1.5f, "pickaxe",
			0).setResistance(1000.0F).setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("stone_second_stratum")
	public static BasicBlock STONE_SECOND_STRATUM = new BasicBlock(Material.ROCK, "stone_second_stratum", 2.0f,
			"pickaxe", 1).setResistance(2000.0F).setSoundType(MMaterialSounds.STONE)
					.setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("stone_third_stratum")
	public static BasicBlock STONE_THIRD_STRATUM = new BasicBlock(Material.ROCK, "stone_third_stratum", 2.5f, "pickaxe",
			2).setResistance(3000.0F).setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);
	@ObjectHolder("tartheus_bedrock")
	public static BasicBlockUnbreakable TARTHEUS_BEDROCK = new BasicBlockUnbreakable(Material.ROCK, "tartheus_bedrock",
			-1, "pickaxe", -1).setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_BLOCKS);

	@ObjectHolder("dead_shrub")
	public static BlockDeadShrub DEAD_SAPLING = new BlockDeadShrub("dead_sapling", 0.66f, "machete", 0)
			.setSoundType(MMaterialSounds.PLANT).setCreativeTab(MCreativeTabs.T_DECORATION);
	@ObjectHolder("monactus_plant")
	public static BlockMonactusPlant MONACTUS_PLANT = new BlockMonactusPlant("monactus_plant", 2.12f, "machete", 1)
			.setSoundType(MMaterialSounds.PLANT);
	@ObjectHolder("yucca_plant")
	public static BlockYuccaPlant YUCCA_PLANT = new BlockYuccaPlant("yucca_plant", 2.12f, "machete", 1)
			.setSoundType(MMaterialSounds.PLANT);
	@ObjectHolder("elderstem")
	public static BlockFlower ELDERSTEM = new BlockFlower("elderstem", 0.47f, "machete", 1)
			.setSoundType(MMaterialSounds.PLANT).setCreativeTab(MCreativeTabs.T_DECORATION);
	@ObjectHolder("pointybones")
	public static BlockPointyBones POINTYBONES = new BlockPointyBones(Material.PLANTS, "pointybones", 1.05f, "machete",
			1).setSoundType(MMaterialSounds.STONE).setCreativeTab(MCreativeTabs.T_DECORATION);

	@Mod.EventBusSubscriber(modid = Tartheus.MODID)
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> blocks = event.getRegistry();
			blocks.registerAll(TARTHEUS_PORTAL, SCORPION_SPAWNER, BRIGHTSTONE_TORCH, STONE_FURNACE,
					STONE_FURNACE_ACTIVE, BRIGHTSTONE_ORE, ATLASITE_ORE, STELLARIUM_ORE, UMBRALLIUM_ORE,
					TARTHEUS_TALLGRASS, TARTHEUS_SHORTGRASS, TARTHEUS_GRASS, TARTHEUS_COARSE_DIRT, TARTHEUS_DIRT,
					ALDER_SAPLING, ALDER_PLANKS, ALDER_LOG, ALDER_LEAVES, BARRENWOOD_SAPLING, BARRENWOOD_PLANKS,
					BARRENWOOD_STAIRS, BARRENWOOD_SLAB, BARRENWOOD_DOOR, BARRENWOOD_LOG, BARRENWOOD_LEAVES,
					TARTHEUS_GLASS, TARTHEUS_SAND, QUICKSAND, TARTHEUS_SANDSTONE, CUT_SANDSTONE, CUT_SANDSTONE_STAIRS,
					CUT_SANDSTONE_SLAB, CUT_SANDSTONE_DOOR, DESERTSTONE, HELL_SAND, TERRACOTTA, TERRACOTTA_CRIMSON,
					TERRACOTTA_TANGERINE, TERRACOTTA_HAZEL, TERRACOTTA_PALE, TERRACOTTA_GRAY, MARROWSTONE, BONE_PILE,
					BONE_MUSH, BASALT, ASH, FLAME_JET, STONE_FIRST_STRATUM, STONE_SECOND_STRATUM, STONE_THIRD_STRATUM,
					TARTHEUS_BEDROCK, DEAD_SAPLING, MONACTUS_PLANT, YUCCA_PLANT, ELDERSTEM, POINTYBONES);
		}
	}
}