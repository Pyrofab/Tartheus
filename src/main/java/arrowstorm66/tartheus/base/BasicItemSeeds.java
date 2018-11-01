package arrowstorm66.tartheus.base;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.MCreativeTabs;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class BasicItemSeeds extends BasicItem implements net.minecraftforge.common.IPlantable, ModelRegistry {
	private final Block crop;
	/** BlockID of the block the seeds can be planted on. */
	private final Block soilBlockID;
	protected String name;

	public BasicItemSeeds(String name, Block crop, Block soil) {
		super(name);
		this.name = name;
		this.crop = crop;
		this.soilBlockID = soil;
	}

	@Override
	public BasicItemSeeds setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);
		net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
		if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack)
				&& (crop != null && crop.canSustainPlant(state, worldIn, pos, EnumFacing.UP, (IPlantable) crop))
				&& worldIn.isAirBlock(pos.up())) {
			worldIn.setBlockState(pos.up(), this.crop.getDefaultState());
			itemstack.shrink(1);
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

	@Override
	public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos) {
		return net.minecraftforge.common.EnumPlantType.Crop;
	}

	@Override
	public net.minecraft.block.state.IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos) {
		return this.crop.getDefaultState();
	}
}