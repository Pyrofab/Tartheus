package arrowstorm66.tartheus.base;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Multimap;

import arrowstorm66.tartheus.MCreativeTabs;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.base.entity.EntityHostile;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BasicItemFood extends ItemFood implements ModelRegistry {

	public final int itemUseDuration;
	private final int healAmount;
	private final float saturationModifier;
	private final boolean isWolfsFavoriteMeat;
	private float effectProb;
	private String guiEffect;
	private Potion effect;
	private int duration;
	private int amp;
	public EnumAction action;
	protected String name;
	
	public BasicItemFood(String name, int itemUseDuration, int amount, float saturation, int stack, boolean isWolfFood,
			EnumAction action) {
		super(amount, saturation, isWolfFood);
		this.name = name;
		this.itemUseDuration = itemUseDuration;
		this.healAmount = amount;
		this.saturationModifier = saturation;
		this.isWolfsFavoriteMeat = isWolfFood;
		this.action = action;
		this.setMaxStackSize(stack);
		this.setCreativeTab(MCreativeTabs.T_CONSUMABLES);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	public BasicItemFood(String name, int itemUseDuration, int amount, float saturation, int stack, boolean isWolfFood,
			Potion effect, int duration, int amp, float effectProb, EnumAction action) {
		super(amount, saturation, isWolfFood);
		this.name = name;
		this.itemUseDuration = itemUseDuration;
		this.healAmount = amount;
		this.saturationModifier = saturation;
		this.isWolfsFavoriteMeat = isWolfFood;
		this.effect = effect;
		this.duration = duration;
		this.amp = amp;
		this.effectProb = effectProb;
		this.action = action;
		this.setMaxStackSize(stack);
		this.setCreativeTab(MCreativeTabs.T_CONSUMABLES);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	public BasicItemFood(String name, int itemUseDuration, int amount, float saturation, int stack, boolean isWolfFood,
			Potion effect, int duration, int amp, float effectProb, String guiEffect, EnumAction action) {
		super(amount, saturation, isWolfFood);
		this.name = name;
		this.itemUseDuration = itemUseDuration;
		this.healAmount = amount;
		this.saturationModifier = saturation;
		this.isWolfsFavoriteMeat = isWolfFood;
		this.effect = effect;
		this.duration = duration;
		this.amp = amp;
		this.effectProb = effectProb;
		this.guiEffect = guiEffect;
		this.action = action;
		this.setMaxStackSize(stack);
		this.setCreativeTab(MCreativeTabs.T_ITEMS);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	@Override
	public BasicItemFood setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return itemUseDuration;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return action;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			entityplayer.getFoodStats().addStats(this, stack);
			worldIn.playSound((EntityPlayer) null, entityplayer.posX, entityplayer.posY, entityplayer.posZ,
					SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F,
					worldIn.rand.nextFloat() * 0.1F + 0.9F);
			this.onFoodEaten(stack, worldIn, entityplayer);
			entityplayer.addStat(StatList.getObjectUseStats(this));
		}
		stack.shrink(1);
		return stack;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if (!worldIn.isRemote && this.effect != null && worldIn.rand.nextFloat() < this.effectProb) {
			player.addPotionEffect(new PotionEffect(effect, duration, amp));
			if (this.guiEffect != null) {
				Tartheus.proxy.displayMobScreen(duration * 40, guiEffect);
			}
		}
	}
}