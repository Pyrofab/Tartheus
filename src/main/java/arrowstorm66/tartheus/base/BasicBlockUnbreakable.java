package arrowstorm66.tartheus.base;

import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

public class BasicBlockUnbreakable extends BasicBlock implements ModelRegistry {

	protected static String name;

	public BasicBlockUnbreakable(Material material, String name, float hardness, String tool, int level) {
		super(material, name, hardness, tool, level);
		BasicBlockUnbreakable.name = name;
		this.disableStats();
		this.setResistance(6000000.0F);
		this.setBlockUnbreakable();
	}

	@Override
	public BasicBlockUnbreakable setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public BasicBlockUnbreakable setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}
    
    public int quantityDropped(Random random)
    {
        return 0;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.AIR;
    }
    
    public MapColor getMapColor(IBlockState state)
    {
        return MapColor.MAGENTA;
    }
}