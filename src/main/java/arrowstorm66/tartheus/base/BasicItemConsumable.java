package arrowstorm66.tartheus.base;

import java.util.List;

import arrowstorm66.tartheus.MCreativeTabs;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BasicItemConsumable extends BasicItem implements ModelRegistry {

	protected String name;
	protected Potion counteract;
	protected Potion target;

	public BasicItemConsumable(String name, Potion counteract, Potion target) {
		super(name);
		this.name = name;
		this.counteract = counteract;
		this.target = target;
		maxStackSize = 10;
		setCreativeTab(MCreativeTabs.T_TOOLS);
	}

	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (!worldIn.isRemote)
			worldIn.playSound((EntityPlayer) null, entityLiving.posX, entityLiving.posY, entityLiving.posZ,
					SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 0.5F,
					worldIn.rand.nextFloat() * 0.1F + 0.9F);
		entityLiving.addPotionEffect(new PotionEffect(counteract, 3600));
		((EntityPlayer) entityLiving).getCooldownTracker().setCooldown((Item) this, 600);

		if (entityLiving instanceof EntityPlayer && !((EntityPlayer) entityLiving).capabilities.isCreativeMode) {
			stack.shrink(1);
		}
		return stack;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (playerIn.isPotionActive(target) && (!playerIn.isPotionActive(counteract))) {
			playerIn.setActiveHand(handIn);
			return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
		if (playerIn.isPotionActive(counteract)) {
			return new ActionResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
		}
		if (playerIn.isPotionActive(counteract) && (playerIn.isPotionActive(target))){
			return new ActionResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
		}
		return new ActionResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
	}

	public int getMaxItemUseDuration(ItemStack stack) {
		return 50;
	}

	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}
}