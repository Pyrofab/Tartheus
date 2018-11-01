package arrowstorm66.tartheus.base;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.event.*;
import net.minecraft.block.material.*;
import arrowstorm66.tartheus.MCreativeTabs;
import arrowstorm66.tartheus.MItems;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.*;
import net.minecraft.block.properties.*;
import net.minecraft.init.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.block.state.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class BasicItemBucket extends ItemBucket implements ModelRegistry {

	protected String name;

	public BasicItemBucket(String name, final Block block) {
		super(block);
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		this.maxStackSize = 1;
		this.setCreativeTab(MCreativeTabs.T_TOOLS);
	}

	public ActionResult<ItemStack> onItemRightClick(final World worldIn, final EntityPlayer playerIn,
			final EnumHand hand) {
		final ItemStack itemstack = playerIn.getHeldItem(hand);
		final boolean flag = this == MItems.DYNAMIUM_BUCKET;
		final RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, flag);
		final ActionResult<ItemStack> ret = (ActionResult<ItemStack>) ForgeEventFactory.onBucketUse(playerIn, worldIn,
				itemstack, raytraceresult);
		if (ret != null) {
			return ret;
		}
		if (raytraceresult == null) {
			return (ActionResult<ItemStack>) new ActionResult(EnumActionResult.PASS, (Object) itemstack);
		}
		if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
			return (ActionResult<ItemStack>) new ActionResult(EnumActionResult.PASS, (Object) itemstack);
		}
		final BlockPos blockpos = raytraceresult.getBlockPos();
		if (!worldIn.isBlockModifiable(playerIn, blockpos)) {
			return (ActionResult<ItemStack>) new ActionResult(EnumActionResult.FAIL, (Object) itemstack);
		}
		if (flag) {
			if (!playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemstack)) {
				return (ActionResult<ItemStack>) new ActionResult(EnumActionResult.FAIL, (Object) itemstack);
			}
			final IBlockState iblockstate = worldIn.getBlockState(blockpos);
			final Material material = iblockstate.getMaterial();
			final Block block = iblockstate.getBlock();
			if (block == Blocks.WATER && (int) iblockstate.getValue((IProperty) BlockLiquid.LEVEL) == 0) {
				worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 11);
				playerIn.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0f, 1.0f);
				return (ActionResult<ItemStack>) new ActionResult(EnumActionResult.SUCCESS,
						(Object) this.fillBucket(itemstack, playerIn, MItems.DYNAMIUM_BUCKET_BLOOD));
			}
			if (block == Blocks.LAVA && (int) iblockstate.getValue((IProperty) BlockLiquid.LEVEL) == 0) {
				playerIn.playSound(SoundEvents.ITEM_BUCKET_FILL_LAVA, 1.0f, 1.0f);
				worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 11);
				return (ActionResult<ItemStack>) new ActionResult(EnumActionResult.SUCCESS,
						(Object) this.fillBucket(itemstack, playerIn, MItems.DYNAMIUM_BUCKET_CURSED_BLOOD));
			}
			return (ActionResult<ItemStack>) new ActionResult(EnumActionResult.FAIL, (Object) itemstack);
		} else {
			final boolean flag2 = worldIn.getBlockState(blockpos).getBlock().isReplaceable((IBlockAccess) worldIn,
					blockpos);
			final BlockPos blockpos2 = (flag2 && raytraceresult.sideHit == EnumFacing.UP) ? blockpos
					: blockpos.offset(raytraceresult.sideHit);
			if (!playerIn.canPlayerEdit(blockpos2, raytraceresult.sideHit, itemstack)) {
				return (ActionResult<ItemStack>) new ActionResult(EnumActionResult.FAIL, (Object) itemstack);
			}
			if (this.tryPlaceContainedLiquid(playerIn, worldIn, blockpos2)) {
				return (ActionResult<ItemStack>) (playerIn.capabilities.isCreativeMode
						? new ActionResult(EnumActionResult.SUCCESS, (Object) itemstack)
						: new ActionResult(EnumActionResult.SUCCESS, (Object) new ItemStack(MItems.DYNAMIUM_BUCKET)));
			}
			return (ActionResult<ItemStack>) new ActionResult(EnumActionResult.FAIL, (Object) itemstack);
		}
	}

	private ItemStack fillBucket(final ItemStack emptyBuckets, final EntityPlayer player, final Item fullBucket) {
		if (player.capabilities.isCreativeMode) {
			return emptyBuckets;
		}
		emptyBuckets.shrink(1);
		if (emptyBuckets.isEmpty()) {
			return new ItemStack(fullBucket);
		}
		if (!player.inventory.addItemStackToInventory(new ItemStack(fullBucket))) {
			player.dropItem(new ItemStack(fullBucket), false);
		}
		return emptyBuckets;
	}

	public boolean tryPlaceContainedLiquid(final EntityPlayer worldIn, final World pos, final BlockPos p_180616_3_) {
		if (this == MItems.DYNAMIUM_BUCKET) {
			return false;
		}
		final IBlockState iblockstate = pos.getBlockState(p_180616_3_);
		final Material material = iblockstate.getMaterial();
		final boolean flag = !material.isSolid();
		final boolean flag2 = iblockstate.getBlock().isReplaceable((IBlockAccess) pos, p_180616_3_);
		if (!pos.isAirBlock(p_180616_3_) && !flag && !flag2) {
			return false;
		}
		if (!pos.isRemote && (flag || flag2) && !material.isLiquid()) {
			pos.destroyBlock(p_180616_3_, true);
		}
		final SoundEvent soundevent = (this == MItems.DYNAMIUM_BUCKET_CURSED_BLOOD) ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA
				: SoundEvents.ITEM_BUCKET_EMPTY;
		pos.playSound(worldIn, p_180616_3_, soundevent, SoundCategory.BLOCKS, 1.0f, 1.0f);
		if (this == MItems.DYNAMIUM_BUCKET_BLOOD) {
			pos.setBlockState(p_180616_3_, Blocks.WATER.getDefaultState(), 11);
		}
		else if (this == MItems.DYNAMIUM_BUCKET_CURSED_BLOOD) {
			pos.setBlockState(p_180616_3_, Blocks.LAVA.getDefaultState(), 11);
		}
		return true;
	}
}