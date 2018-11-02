package arrowstorm66.tartheus.blocks;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.MCreativeTabs;
import arrowstorm66.tartheus.base.BasicItem;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCutSandstoneDoor extends BasicItem implements ModelRegistry {
	protected String name;

	public ItemCutSandstoneDoor(final String name) {
		super(name);
		this.name = name;
		this.setCreativeTab(MCreativeTabs.T_DECORATION);
	}

	public EnumActionResult onItemUse(final EntityPlayer player, final World world, BlockPos pos, final EnumHand hand,
			final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		final ItemStack stack = player.getHeldItem(hand);
		if (facing != EnumFacing.UP) {
			return EnumActionResult.FAIL;
		}
		final IBlockState iblockstate = world.getBlockState(pos);
		final Block block = iblockstate.getBlock();
		if (!block.isReplaceable(world, pos)) {
			pos = pos.offset(facing);
		}
		if (player.canPlayerEdit(pos, facing, stack) && MBlocks.CUT_SANDSTONE_DOOR.canPlaceBlockAt(world, pos)) {
			final EnumFacing enumfacing = EnumFacing.fromAngle((double) player.rotationYaw);
			final int i = enumfacing.getFrontOffsetX();
			final int j = enumfacing.getFrontOffsetZ();
			final boolean flag = (i < 0 && hitZ < 0.5f) || (i > 0 && hitZ > 0.5f) || (j < 0 && hitX > 0.5f)
					|| (j > 0 && hitX < 0.5f);
			ItemDoor.placeDoor(world, pos, enumfacing, MBlocks.CUT_SANDSTONE_DOOR, flag);
			final SoundType soundtype = MBlocks.CUT_SANDSTONE_DOOR.getSoundType();
			world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS,
					(soundtype.getVolume() + 1.0f) / 2.0f, soundtype.getPitch() * 0.8f);
			stack.shrink(1);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}

}