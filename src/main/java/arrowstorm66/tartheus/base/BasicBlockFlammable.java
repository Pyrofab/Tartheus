package arrowstorm66.tartheus.base;

import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BasicBlockFlammable extends BasicBlock implements ModelRegistry {

	protected static String name;
	private int flammability;
	private int fireSpeed;

	public BasicBlockFlammable(Material material, String name, float hardness, String tool, int level, int flammability,
			int fireSpeed) {
		super(material, name, hardness, tool, level);
		BasicBlockFlammable.name = name;
	}

	public BasicBlockFlammable(Material material, String name, float hardness, int flammability, int fireSpeed) {
		super(material, name, hardness);
		BasicBlockFlammable.name = name;
	}

	@Override
	public BasicBlockFlammable setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public BasicBlockFlammable setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}

	public int getFlammability(final IBlockAccess world, final BlockPos pos, final EnumFacing side) {
		return flammability;
	}

	public int getFireSpreadSpeed(final IBlockAccess world, final BlockPos pos, final EnumFacing side) {
		return fireSpeed;
	}
}