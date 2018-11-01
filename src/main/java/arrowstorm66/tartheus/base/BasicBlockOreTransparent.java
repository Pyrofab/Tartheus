package arrowstorm66.tartheus.base;

import java.util.Random;

import arrowstorm66.tartheus.MMaterialSounds;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BasicBlockOreTransparent extends BasicBlock implements ModelRegistry {

	protected static String name;
	protected Item ore;

	public BasicBlockOreTransparent(Material material, String name, float hardness, String tool, int level, Item ore) {
		super(material, name, hardness, tool, level);
		this.name = name;
		this.ore = ore;
	}

	public BasicBlockOreTransparent(Material material, String name, float hardness, Item ore) {
		super(material, name, hardness);
		this.name = name;
		this.ore = ore;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ore;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public BasicBlockOreTransparent setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public BasicBlockOreTransparent setResistance(float resistance) {
		this.blockResistance = resistance * 3.0F;
		return this;
	}

	@Override
	public BasicBlockOreTransparent setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}
	
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
    
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}