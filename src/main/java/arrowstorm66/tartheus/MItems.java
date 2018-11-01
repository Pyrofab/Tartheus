package arrowstorm66.tartheus;

import java.util.List;

import arrowstorm66.tartheus.base.BasicItem;
import arrowstorm66.tartheus.base.BasicItemBucket;
import arrowstorm66.tartheus.base.BasicItemConsumable;
import arrowstorm66.tartheus.base.BasicItemFood;
import arrowstorm66.tartheus.base.BasicItemSeeds;
import arrowstorm66.tartheus.base.BasicItemTool;
import arrowstorm66.tartheus.base.gear.BasicItemArmor;
import arrowstorm66.tartheus.base.gear.BasicItemMeleeWeapon;
import arrowstorm66.tartheus.base.gear.BasicItemShield;
import arrowstorm66.tartheus.blocks.ItemBarrenwoodDoor;
import arrowstorm66.tartheus.blocks.ItemCutSandstoneDoor;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@ObjectHolder(Tartheus.MODID)
public class MItems {

	// Melee
	@ObjectHolder("wooden_dagger")
	public static BasicItemMeleeWeapon WOODEN_DAGGER = new BasicItemMeleeWeapon("wooden_dagger", MToolMaterial.WOODEN,
			3, 1.6, -1.5f, 0.3f);
	@ObjectHolder("wooden_spear")
	public static BasicItemMeleeWeapon WOODEN_SPEAR = new BasicItemMeleeWeapon("wooden_spear", MToolMaterial.WOODEN, 2,
			2.0, 1.0f, 0.25f);
	@ObjectHolder("wooden_sword")
	public static BasicItemMeleeWeapon WOODEN_SWORD = new BasicItemMeleeWeapon("wooden_sword", MToolMaterial.WOODEN, 4,
			2.4, 0.0f, 0.2f);
	@ObjectHolder("wooden_battleaxe")
	public static BasicItemMeleeWeapon WOODEN_BATTLEAXE = new BasicItemMeleeWeapon("wooden_battleaxe",
			MToolMaterial.WOODEN, 6, 2.9, -0.5f, 0.15f);
	@ObjectHolder("wooden_mace")
	public static BasicItemMeleeWeapon WOODEN_MACE = new BasicItemMeleeWeapon("wooden_mace", MToolMaterial.WOODEN, 8,
			3.4, -1.0f, 0.1f);

	@ObjectHolder("chitin_dagger")
	public static BasicItemMeleeWeapon CHITIN_DAGGER = new BasicItemMeleeWeapon("chitin_dagger", MToolMaterial.CHITIN,
			4, 1.6, -1.5f, 0.3f);
	@ObjectHolder("chitin_spear")
	public static BasicItemMeleeWeapon CHITIN_SPEAR = new BasicItemMeleeWeapon("chitin_spear", MToolMaterial.CHITIN, 3,
			2.0, 1.0f, 0.25f);
	@ObjectHolder("chitin_sword")
	public static BasicItemMeleeWeapon CHITIN_SWORD = new BasicItemMeleeWeapon("chitin_sword", MToolMaterial.CHITIN, 5,
			2.4, 0.0f, 0.2f);
	@ObjectHolder("chitin_battleaxe")
	public static BasicItemMeleeWeapon CHITIN_BATTLEAXE = new BasicItemMeleeWeapon("chitin_battleaxe",
			MToolMaterial.CHITIN, 7, 2.9, -0.5f, 0.15f);
	@ObjectHolder("chitin_mace")
	public static BasicItemMeleeWeapon CHITIN_MACE = new BasicItemMeleeWeapon("chitin_mace", MToolMaterial.CHITIN, 9,
			3.4, -1.0f, 0.1f);

	@ObjectHolder("atlasite_dagger")
	public static BasicItemMeleeWeapon ATLASITE_DAGGER = new BasicItemMeleeWeapon("atlasite_dagger",
			MToolMaterial.ATLASITE, 5, 1.6, -1.5f, 0.3f);
	@ObjectHolder("atlasite_spear")
	public static BasicItemMeleeWeapon ATLASITE_SPEAR = new BasicItemMeleeWeapon("atlasite_spear",
			MToolMaterial.ATLASITE, 4, 2.0, 1.0f, 0.25f);
	@ObjectHolder("atlasite_sword")
	public static BasicItemMeleeWeapon ATLASITE_SWORD = new BasicItemMeleeWeapon("atlasite_sword",
			MToolMaterial.ATLASITE, 6, 2.4, 0.0f, 0.2f);
	@ObjectHolder("atlasite_battleaxe")
	public static BasicItemMeleeWeapon ATLASITE_BATTLEAXE = new BasicItemMeleeWeapon("atlasite_battleaxe",
			MToolMaterial.ATLASITE, 8, 2.9, -0.5f, 0.15f);
	@ObjectHolder("atlasite_mace")
	public static BasicItemMeleeWeapon ATLASITE_MACE = new BasicItemMeleeWeapon("atlasite_mace", MToolMaterial.ATLASITE,
			10, 3.4, -1.0f, 0.1f);

	@ObjectHolder("stellarium_dagger")
	public static BasicItemMeleeWeapon STELLARIUM_DAGGER = new BasicItemMeleeWeapon("stellarium_dagger",
			MToolMaterial.STELLARIUM, 6, 1.6, -1.5f, 0.3f);
	@ObjectHolder("stellarium_spear")
	public static BasicItemMeleeWeapon STELLARIUM_SPEAR = new BasicItemMeleeWeapon("stellarium_spear",
			MToolMaterial.STELLARIUM, 5, 2.0, 1.0f, 0.25f);
	@ObjectHolder("stellarium_sword")
	public static BasicItemMeleeWeapon STELLARIUM_SWORD = new BasicItemMeleeWeapon("stellarium_sword",
			MToolMaterial.STELLARIUM, 7, 2.4, 0.0f, 0.2f);
	@ObjectHolder("stellarium_battleaxe")
	public static BasicItemMeleeWeapon STELLARIUM_BATTLEAXE = new BasicItemMeleeWeapon("stellarium_battleaxe",
			MToolMaterial.STELLARIUM, 9, 2.9, -0.5f, 0.15f);
	@ObjectHolder("stellarium_mace")
	public static BasicItemMeleeWeapon STELLARIUM_MACE = new BasicItemMeleeWeapon("stellarium_mace",
			MToolMaterial.STELLARIUM, 11, 3.4, -1.0f, 0.1f);

	@ObjectHolder("umbrallium_dagger")
	public static BasicItemMeleeWeapon UMBRALLIUM_DAGGER = new BasicItemMeleeWeapon("umbrallium_dagger",
			MToolMaterial.UMBRALLIUM, 6, 1.6, -1.5f, 0.3f);
	@ObjectHolder("umbrallium_spear")
	public static BasicItemMeleeWeapon UMBRALLIUM_SPEAR = new BasicItemMeleeWeapon("umbrallium_spear",
			MToolMaterial.UMBRALLIUM, 5, 2.0, 1.0f, 0.25f);
	@ObjectHolder("umbrallium_sword")
	public static BasicItemMeleeWeapon UMBRALLIUM_SWORD = new BasicItemMeleeWeapon("umbrallium_sword",
			MToolMaterial.UMBRALLIUM, 7, 2.4, 0.0f, 0.2f);
	@ObjectHolder("umbrallium_battleaxe")
	public static BasicItemMeleeWeapon UMBRALLIUM_BATTLEAXE = new BasicItemMeleeWeapon("umbrallium_battleaxe",
			MToolMaterial.UMBRALLIUM, 9, 2.9, -0.5f, 0.15f);
	@ObjectHolder("umbrallium_mace")
	public static BasicItemMeleeWeapon UMBRALLIUM_MACE = new BasicItemMeleeWeapon("umbrallium_mace",
			MToolMaterial.UMBRALLIUM, 11, 3.4, -1.0f, 0.1f);

	// Shield
	@ObjectHolder("wooden_shield")
	public static BasicItemShield WOODEN_SHIELD = new BasicItemShield("wooden_shield", 120, 0);
	@ObjectHolder("chitin_shield")
	public static BasicItemShield CHITIN_SHIELD = new BasicItemShield("chitin_shield", 100, 1);
	@ObjectHolder("atlasite_shield")
	public static BasicItemShield ATLASITE_SHIELD = new BasicItemShield("atlasite_shield", 80, 1);
	@ObjectHolder("stellarium_shield")
	public static BasicItemShield STELLARIUM_SHIELD = new BasicItemShield("stellarium_shield", 60, 1);
	@ObjectHolder("umbrallium_shield")
	public static BasicItemShield UMBRALLIUM_SHIELD = new BasicItemShield("umbrallium_shield", 60, 1);

	// Tools
	@ObjectHolder("wooden_pickaxe")
	public static BasicItemTool WOODEN_PICKAXE = new BasicItemTool("wooden_pickaxe", MToolMaterial.WOODEN, 0.5f, -2.8f,
			0);
	@ObjectHolder("wooden_machete")
	public static BasicItemTool WOODEN_MACHETE = new BasicItemTool("wooden_machete", MToolMaterial.WOODEN, 0.7f, -2.2f,
			2);
	@ObjectHolder("wooden_shovel")
	public static BasicItemTool WOODEN_SHOVEL = new BasicItemTool("wooden_shovel", MToolMaterial.WOODEN, 0.3f, -3.0f,
			1);

	@ObjectHolder("chitin_pickaxe")
	public static BasicItemTool CHITIN_PICKAXE = new BasicItemTool("chitin_pickaxe", MToolMaterial.CHITIN, 0.5f, -2.8f,
			0);
	@ObjectHolder("chitin_machete")
	public static BasicItemTool CHITIN_MACHETE = new BasicItemTool("chitin_machete", MToolMaterial.CHITIN, 0.7f, -2.2f,
			2);
	@ObjectHolder("chitin_shovel")
	public static BasicItemTool CHITIN_SHOVEL = new BasicItemTool("chitin_shovel", MToolMaterial.CHITIN, 0.3f, -3.0f,
			1);

	@ObjectHolder("atlasite_pickaxe")
	public static BasicItemTool ATLASITE_PICKAXE = new BasicItemTool("atlasite_pickaxe", MToolMaterial.ATLASITE, 0.5f,
			-2.8f, 0);
	@ObjectHolder("atlasite_machete")
	public static BasicItemTool ATLASITE_MACHETE = new BasicItemTool("atlasite_machete", MToolMaterial.ATLASITE, 0.7f,
			-2.2f, 2);
	@ObjectHolder("atlasite_shovel")
	public static BasicItemTool ATLASITE_SHOVEL = new BasicItemTool("atlasite_shovel", MToolMaterial.ATLASITE, 0.3f,
			-3.0f, 1);

	@ObjectHolder("stellarium_pickaxe")
	public static BasicItemTool STELLARIUM_PICKAXE = new BasicItemTool("stellarium_pickaxe", MToolMaterial.STELLARIUM,
			0.5f, -2.8f, 0);
	@ObjectHolder("stellarium_machete")
	public static BasicItemTool STELLARIUM_MACHETE = new BasicItemTool("stellarium_machete", MToolMaterial.STELLARIUM,
			0.7f, -2.2f, 2);
	@ObjectHolder("stellarium_shovel")
	public static BasicItemTool STELLARIUM_SHOVEL = new BasicItemTool("stellarium_shovel", MToolMaterial.STELLARIUM,
			0.3f, -3.0f, 1);

	@ObjectHolder("umbrallium_pickaxe")
	public static BasicItemTool UMBRALLIUM_PICKAXE = new BasicItemTool("umbrallium_pickaxe", MToolMaterial.UMBRALLIUM,
			0.5f, -2.8f, 0);
	@ObjectHolder("umbrallium_machete")
	public static BasicItemTool UMBRALLIUM_MACHETE = new BasicItemTool("umbrallium_machete", MToolMaterial.UMBRALLIUM,
			0.7f, -2.2f, 2);
	@ObjectHolder("umbrallium_shovel")
	public static BasicItemTool UMBRALLIUM_SHOVEL = new BasicItemTool("umbrallium_shovel", MToolMaterial.UMBRALLIUM,
			0.3f, -3.0f, 1);

	// Armor
	@ObjectHolder("wooden_helmet")
	public static BasicItemArmor WOODEN_HELMET = new BasicItemArmor("wooden_helmet", MArmorMaterials.WOODEN, 0,
			EntityEquipmentSlot.HEAD, Tartheus.MODID + ":textures/armor/wooden_leggings.png",
			Tartheus.MODID + ":textures/armor/wooden_armor.png");

	// Minerals
	@ObjectHolder("brightstone")
	public static BasicItem BRIGHTSTONE = new BasicItem("brightstone").setCreativeTab(MCreativeTabs.T_ITEMS);

	@ObjectHolder("raw_atlasite")
	public static BasicItem RAW_ATLASITE = new BasicItem("raw_atlasite").setCreativeTab(MCreativeTabs.T_ITEMS);
	@ObjectHolder("raw_stellarium")
	public static BasicItem RAW_STELLARIUM = new BasicItem("raw_stellarium").setCreativeTab(MCreativeTabs.T_ITEMS);
	@ObjectHolder("raw_umbrallium")
	public static BasicItem RAW_UMBRALLIUM = new BasicItem("raw_umbrallium").setCreativeTab(MCreativeTabs.T_ITEMS);
	@ObjectHolder("atlasite_ingot")
	public static BasicItem ATLASITE_INGOT = new BasicItem("atlasite_ingot").setCreativeTab(MCreativeTabs.T_ITEMS);
	@ObjectHolder("stellarium_ingot")
	public static BasicItem STELLARIUM_INGOT = new BasicItem("stellarium_ingot").setCreativeTab(MCreativeTabs.T_ITEMS);
	@ObjectHolder("umbrallium_ingot")
	public static BasicItem UMBRALLIUM_INGOT = new BasicItem("umbrallium_ingot").setCreativeTab(MCreativeTabs.T_ITEMS);

	@ObjectHolder("tartheus_stick")
	public static BasicItem TARTHEUS_STICK = new BasicItem("tartheus_stick").setCreativeTab(MCreativeTabs.T_ITEMS);
	@ObjectHolder("chitin")
	public static BasicItem CHITIN = new BasicItem("chitin").setCreativeTab(MCreativeTabs.T_ITEMS);
	@ObjectHolder("ashes")
	public static BasicItem ASHES = new BasicItem("ashes").setCreativeTab(MCreativeTabs.T_ITEMS);

	// Buckets
	@ObjectHolder("dynamium_bucket")
	public static BasicItemBucket DYNAMIUM_BUCKET = new BasicItemBucket("dynamium_bucket", Blocks.AIR);
	@ObjectHolder("dynamium_bucket_blood")
	public static BasicItemBucket DYNAMIUM_BUCKET_BLOOD = (BasicItemBucket) new BasicItemBucket("dynamium_bucket_blood",
			Blocks.WATER).setContainerItem(MItems.DYNAMIUM_BUCKET);
	@ObjectHolder("dynamium_bucket_cursed_blood")
	public static BasicItemBucket DYNAMIUM_BUCKET_CURSED_BLOOD = (BasicItemBucket) new BasicItemBucket(
			"dynamium_bucket_cursed_blood", Blocks.LAVA).setContainerItem(MItems.DYNAMIUM_BUCKET);

	// Materials
	@ObjectHolder("monactus_flesh")
	public static BasicItemFood MONACTUS_FLESH = new BasicItemFood("monactus_flesh", 30, 2, 0.9f, 16, true,
			MobEffects.INSTANT_DAMAGE, 10, 0, 1, EnumAction.EAT);
	@ObjectHolder("cooked_monactus_flesh")
	public static BasicItemFood COOKED_MONACTUS_FLESH = new BasicItemFood("cooked_monactus_flesh", 30, 5, 0.75f, 16,
			true, EnumAction.EAT);
	@ObjectHolder("monactus_seeds")
	public static BasicItemSeeds MONACTUS_SEEDS = new BasicItemSeeds("monactus_seeds", MBlocks.MONACTUS_PLANT,
			MBlocks.TARTHEUS_SAND).setCreativeTab(MCreativeTabs.T_CONSUMABLES);
	@ObjectHolder("yucca_seeds")
	public static BasicItemSeeds YUCCA_SEEDS = new BasicItemSeeds("yucca_seeds", MBlocks.YUCCA_PLANT,
			MBlocks.TARTHEUS_SAND).setCreativeTab(MCreativeTabs.T_CONSUMABLES);

	// Food
	@ObjectHolder("flask")
	public static BasicItem FLASK = new BasicItem("flask").setCreativeTab(MCreativeTabs.T_CONSUMABLES);
	@ObjectHolder("monactus_juice")
	public static BasicItemFood MONACTUS_JUICE = new BasicItemFood("monactus_juice", 20, 1, 1.5f, 64, true,
			EnumAction.DRINK);

	// Blocks
	@ObjectHolder("item_barrenwood_door")
	public static ItemBarrenwoodDoor ITEM_BARRENWOOD_DOOR = new ItemBarrenwoodDoor("item_barrenwood_door");
	@ObjectHolder("item_cut_sandstone_door")
	public static ItemCutSandstoneDoor ITEM_CUT_SANDSTONE_DOOR = new ItemCutSandstoneDoor("item_cut_sandstone_door");

	@Mod.EventBusSubscriber(modid = Tartheus.MODID)
	public static class ItemsRegistrationHandler {

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> items = event.getRegistry();
			final ItemBlockRegistry itemblocks = new ItemBlockRegistry((IForgeRegistry<Item>) event.getRegistry());
			items.registerAll(WOODEN_DAGGER, WOODEN_SPEAR, WOODEN_SWORD, WOODEN_BATTLEAXE, WOODEN_MACE, WOODEN_SHIELD,
					WOODEN_PICKAXE, WOODEN_MACHETE, WOODEN_SHOVEL, CHITIN_DAGGER, CHITIN_SPEAR, CHITIN_SWORD,
					CHITIN_BATTLEAXE, CHITIN_MACE, CHITIN_SHIELD, CHITIN_PICKAXE, CHITIN_MACHETE, CHITIN_SHOVEL,
					ATLASITE_DAGGER, ATLASITE_SPEAR, ATLASITE_SWORD, ATLASITE_BATTLEAXE, ATLASITE_MACE, ATLASITE_SHIELD,
					ATLASITE_PICKAXE, ATLASITE_MACHETE, ATLASITE_SHOVEL, STELLARIUM_DAGGER, STELLARIUM_SPEAR,
					STELLARIUM_SWORD, STELLARIUM_BATTLEAXE, STELLARIUM_MACE, STELLARIUM_SHIELD, STELLARIUM_PICKAXE,
					STELLARIUM_MACHETE, STELLARIUM_SHOVEL, UMBRALLIUM_DAGGER, UMBRALLIUM_SPEAR, UMBRALLIUM_SWORD,
					UMBRALLIUM_BATTLEAXE, UMBRALLIUM_MACE, UMBRALLIUM_SHIELD, UMBRALLIUM_PICKAXE, UMBRALLIUM_MACHETE,
					UMBRALLIUM_SHOVEL, WOODEN_HELMET, BRIGHTSTONE, RAW_ATLASITE, RAW_STELLARIUM, RAW_UMBRALLIUM,
					ATLASITE_INGOT, STELLARIUM_INGOT, UMBRALLIUM_INGOT, TARTHEUS_STICK, CHITIN, ASHES,
					ITEM_BARRENWOOD_DOOR, ITEM_CUT_SANDSTONE_DOOR, MONACTUS_FLESH, COOKED_MONACTUS_FLESH,
					MONACTUS_SEEDS, YUCCA_SEEDS, FLASK, MONACTUS_JUICE);

			itemblocks.registerItemBlock(MBlocks.TARTHEUS_PORTAL);
			itemblocks.registerItemBlock(MBlocks.SCORPION_SPAWNER);
			itemblocks.registerItemBlock(MBlocks.BRIGHTSTONE_TORCH);
			itemblocks.registerItemBlock(MBlocks.STONE_FURNACE);

			itemblocks.registerItemBlock(MBlocks.BRIGHTSTONE_ORE);
			itemblocks.registerItemBlock(MBlocks.ATLASITE_ORE);
			itemblocks.registerItemBlock(MBlocks.STELLARIUM_ORE);
			itemblocks.registerItemBlock(MBlocks.UMBRALLIUM_ORE);

			itemblocks.registerItemBlock(MBlocks.TARTHEUS_TALLGRASS);
			itemblocks.registerItemBlock(MBlocks.TARTHEUS_SHORTGRASS);
			itemblocks.registerItemBlock(MBlocks.TARTHEUS_GRASS);
			itemblocks.registerItemBlock(MBlocks.TARTHEUS_COARSE_DIRT);
			itemblocks.registerItemBlock(MBlocks.TARTHEUS_DIRT);

			itemblocks.registerItemBlock(MBlocks.ALDER_SAPLING);
			itemblocks.registerItemBlock(MBlocks.ALDER_PLANKS);
			itemblocks.registerItemBlock(MBlocks.ALDER_LOG);
			itemblocks.registerItemBlock(MBlocks.ALDER_LEAVES);
			itemblocks.registerItemBlock(MBlocks.BARRENWOOD_SAPLING);
			itemblocks.registerItemBlock(MBlocks.BARRENWOOD_PLANKS);
			itemblocks.registerItemBlock(MBlocks.BARRENWOOD_STAIRS);
			itemblocks.registerItemBlock(MBlocks.BARRENWOOD_SLAB);
			itemblocks.registerItemBlock(MBlocks.BARRENWOOD_LOG);
			itemblocks.registerItemBlock(MBlocks.BARRENWOOD_LEAVES);

			itemblocks.registerItemBlock(MBlocks.TARTHEUS_GLASS);
			itemblocks.registerItemBlock(MBlocks.TARTHEUS_SAND);
			itemblocks.registerItemBlock(MBlocks.QUICKSAND);
			itemblocks.registerItemBlock(MBlocks.TARTHEUS_SANDSTONE);
			itemblocks.registerItemBlock(MBlocks.CUT_SANDSTONE);
			itemblocks.registerItemBlock(MBlocks.CUT_SANDSTONE_STAIRS);
			itemblocks.registerItemBlock(MBlocks.CUT_SANDSTONE_SLAB);
			itemblocks.registerItemBlock(MBlocks.DESERTSTONE);

			itemblocks.registerItemBlock(MBlocks.HELL_SAND);
			itemblocks.registerItemBlock(MBlocks.TERRACOTTA);
			itemblocks.registerItemBlock(MBlocks.TERRACOTTA_CRIMSON);
			itemblocks.registerItemBlock(MBlocks.TERRACOTTA_GRAY);
			itemblocks.registerItemBlock(MBlocks.TERRACOTTA_HAZEL);
			itemblocks.registerItemBlock(MBlocks.TERRACOTTA_PALE);
			itemblocks.registerItemBlock(MBlocks.TERRACOTTA_TANGERINE);

			itemblocks.registerItemBlock(MBlocks.MARROWSTONE);
			itemblocks.registerItemBlock(MBlocks.BONE_PILE);
			itemblocks.registerItemBlock(MBlocks.BONE_MUSH);

			itemblocks.registerItemBlock(MBlocks.BASALT);
			itemblocks.registerItemBlock(MBlocks.ASH);
			itemblocks.registerItemBlock(MBlocks.FLAME_JET);

			itemblocks.registerItemBlock(MBlocks.STONE_FIRST_STRATUM);
			itemblocks.registerItemBlock(MBlocks.STONE_SECOND_STRATUM);
			itemblocks.registerItemBlock(MBlocks.STONE_THIRD_STRATUM);
			itemblocks.registerItemBlock(MBlocks.TARTHEUS_BEDROCK);

			itemblocks.registerItemBlock(MBlocks.DEAD_SAPLING);
			itemblocks.registerItemBlock(MBlocks.ELDERSTEM);
			itemblocks.registerItemBlock(MBlocks.POINTYBONES);
		}

		private static class ItemBlockRegistry {
			private final IForgeRegistry<Item> registry;

			ItemBlockRegistry(final IForgeRegistry<Item> registry) {
				this.registry = registry;
			}

			private void registerItemBlock(final Block block) {
				final ItemBlock itemBlock = new ItemBlock(block);
				itemBlock.setRegistryName(itemBlock.getBlock().getRegistryName());
				itemBlock.setUnlocalizedName(itemBlock.getBlock().getUnlocalizedName());
				this.registry.register((Item) itemBlock);
			}
		}
	}

	static {

	}
}