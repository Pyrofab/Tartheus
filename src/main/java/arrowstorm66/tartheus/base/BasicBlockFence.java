package arrowstorm66.tartheus.base;

import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BasicBlockFence extends BlockFence implements ModelRegistry {
	
	protected static String name;

	public BasicBlockFence(Material materialIn, MapColor mapColorIn, String name, float hardness, String tool, int level) {
		super(materialIn, mapColorIn);
		BasicBlockFence.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setHarvestLevel(tool, level);
	}
	
	@Override
	public BasicBlockFence setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public BasicBlockFence setResistance(float resistance) {
		this.blockResistance = resistance * 3.0F;
		return this;
	}

	@Override
	public BasicBlockFence setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}
}