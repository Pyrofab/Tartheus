package arrowstorm66.tartheus.base;

import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BasicBlockDoor extends BlockDoor implements ModelRegistry {
	protected static String name;

	public BasicBlockDoor(Material material, String name, float hardness, String tool, int level) {
		super(material);
		BasicBlockDoor.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	@Override
	public BasicBlockDoor setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public BasicBlockDoor setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}

	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return (state.getValue(BasicBlockDoor.HALF) == BlockDoor.EnumDoorHalf.UPPER) ? null
				: Item.getItemFromBlock(this);
	}

	public ItemStack getItem(final World world, final BlockPos pos, final IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this));
	}
}