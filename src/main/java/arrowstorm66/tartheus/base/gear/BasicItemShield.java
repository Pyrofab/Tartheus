package arrowstorm66.tartheus.base.gear;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import arrowstorm66.tartheus.MCreativeTabs;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BasicItemShield extends ItemShield implements ModelRegistry {

	protected String name;
	protected int timer = 0;
	public final int recoveryRate;
	protected int effect;

	public BasicItemShield(String name, int recoveryRate, int effect) {
		this.name = name;
		this.maxStackSize = 1;
		this.recoveryRate = recoveryRate;
		this.effect = effect;
		this.setMaxDamage(336);
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setCreativeTab(MCreativeTabs.T_WEAPONS);
	}

	@Override
	public BasicItemShield setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public int getEntityLifespan(ItemStack itemStack, World world) {
		return 24000;
	}

	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BLOCK;
	}

	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Item.getItemFromBlock(Blocks.PLANKS) ? true
				: super.getIsRepairable(toRepair, repair);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name").trim();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		// durability
		tooltip.add(TextFormatting.GRAY + "Durability" + ": " + (stack.getMaxDamage() - stack.getItemDamage()) + " / "
				+ stack.getMaxDamage());
		tooltip.add(TextFormatting.GRAY + "Recovery Rate" + ": " + (recoveryRate / 20) + " seconds");
		if (effect == 1) {
			tooltip.add("");
			tooltip.add("Absorbs poison");
		}
		if (effect == 2) {
			tooltip.add("");
			tooltip.add("Provides resistance after blocking a hit.");
		}
	}

	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		timer += 1;
		if (timer >= 5 && ((EntityPlayer) player).getCooldownTracker().hasCooldown(stack.getItem())
				&& player instanceof EntityPlayer) {
			player.resetActiveHand();

			if (effect == 1 && player.isPotionActive(MobEffects.POISON)) {
				player.removePotionEffect(MobEffects.POISON);
			}
			
			if (effect == 2) {
				player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 0));
			}
		}
	}
}