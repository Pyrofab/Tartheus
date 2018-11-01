package arrowstorm66.tartheus.base;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.MCreativeTabs;
import arrowstorm66.tartheus.MItems;
import arrowstorm66.tartheus.MToolMaterial;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BasicItemTool extends BasicItem implements ModelRegistry {

	protected String name;
	public MToolMaterial material;
	public int tooltype;
	protected float efficiency;
	protected float damageVsEntity;
	protected float attackSpeed;
	public String toolClass;

	public BasicItemTool(String name, MToolMaterial material, float attackDamageIn, float attackSpeedIn,
			int tooltype) {
		super(name);
		this.name = name;
		this.material = material;
		this.tooltype = tooltype;
		this.efficiency = 4.0F;
		this.maxStackSize = 1;
		this.setMaxDamage(material.getMaxUses());
		this.efficiency = material.getEfficiency();
		this.damageVsEntity = attackDamageIn + material.getAttackDamage();
		this.attackSpeed = attackSpeedIn;
		this.setCreativeTab(MCreativeTabs.T_TOOLS);
		if (this.tooltype == 0) {
			toolClass = "pickaxe";
		}
		if (this.tooltype == 1) {
			toolClass = "shovel";
		}
		if (this.tooltype == 2) {
			toolClass = "machete";
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		// durability
		tooltip.add(TextFormatting.GRAY + I18n.format("Durability") + ": "
				+ (stack.getMaxDamage() - stack.getItemDamage()) + " / " + stack.getMaxDamage());
	}

	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		if (tooltype == 0) {
			Material material = state.getMaterial();
			return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK
					? super.getDestroySpeed(stack, state) : this.efficiency;
		}
		if (tooltype == 1) {
			Material material = state.getMaterial();
			return material != Material.SAND && material != Material.SNOW && material != Material.CLAY
					&& material != Material.GROUND ? super.getDestroySpeed(stack, state) : this.efficiency;
		}
		if (tooltype == 2) {
			Material material = state.getMaterial();
			return material != Material.WEB && material != Material.GOURD && material != Material.CACTUS
					&& material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state)
							: this.efficiency;
		}
		return 0;
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(2, attacker);
		return true;
	}

	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		if (!worldIn.isRemote && (double) state.getBlockHardness(worldIn, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
		}

		return true;
	}

	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
					new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", (double) this.damageVsEntity, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
					new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double) this.attackSpeed, 0));
		}

		return multimap;
	}

	public boolean canHarvestBlock(IBlockState blockIn) {
		if (tooltype == 1) {
			Block block = blockIn.getBlock();
			return block == Blocks.SNOW_LAYER ? true : block == Blocks.SNOW;
		}
		return true;
	}

	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (tooltype == 1) {
			ItemStack itemstack = player.getHeldItem(hand);

			if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
				return EnumActionResult.FAIL;
			} else {
				IBlockState iblockstate = worldIn.getBlockState(pos);
				Block block = iblockstate.getBlock();

				if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR
						&& block == Blocks.GRASS) {
					IBlockState iblockstate1 = Blocks.GRASS_PATH.getDefaultState();
					worldIn.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

					if (!worldIn.isRemote) {
						worldIn.setBlockState(pos, iblockstate1, 11);
						itemstack.damageItem(1, player);
					}

					return EnumActionResult.SUCCESS;
				} else {
					return EnumActionResult.PASS;
				}
			}
		}
		return null;
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass,
			@javax.annotation.Nullable net.minecraft.entity.player.EntityPlayer player,
			@javax.annotation.Nullable IBlockState blockState) {
		int level = super.getHarvestLevel(stack, toolClass, player, blockState);
		if (level == -1 && toolClass.equals(this.toolClass)) {
			return this.material.getHarvestLevel();
		} else {
			return level;
		}
	}

	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		return toolClass != null ? com.google.common.collect.ImmutableSet.of(toolClass) : super.getToolClasses(stack);
	}

	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		ItemStack mat = this.material.getRepairItemStack();
		if (!mat.isEmpty() && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false))
			return true;
		return super.getIsRepairable(toRepair, repair);
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	public MToolMaterial getToolMaterial() {
		return this.material;
	}

	public int getItemEnchantability() {
		return this.material.getEnchantability();
	}

	public String getToolMaterialName() {
		return this.material.toString();
	}
}