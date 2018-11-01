package arrowstorm66.tartheus.base;

import arrowstorm66.tartheus.MMaterialSounds;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BasicBlock extends Block implements ModelRegistry {

	protected static String name;

	public BasicBlock(Material material, String name, float hardness, String tool, int level) {
		super(material);
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setHarvestLevel(tool, level);
	}

	public BasicBlock(Material material, String name, float hardness) {
		super(material);
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
	}

	@Override
	public BasicBlock setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public BasicBlock setResistance(float resistance) {
		this.blockResistance = resistance * 3.0F;
		return this;
	}

	@Override
	public BasicBlock setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}
}