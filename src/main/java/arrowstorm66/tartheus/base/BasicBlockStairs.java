package arrowstorm66.tartheus.base;

import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BasicBlockStairs extends BlockStairs implements ModelRegistry {

	protected static String name;
    protected static float hardness;
    protected static String tool;
    protected static int level;

	public BasicBlockStairs(IBlockState modelState, String name, float hardness, String tool, int level) {
		super(modelState);
		BasicBlockStairs.name = name;
		BasicBlockStairs.hardness = hardness;
		BasicBlockStairs.tool = tool;
		BasicBlockStairs.level = level;
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setHarvestLevel(tool, level);
		setLightOpacity(0);
	}

	public BasicBlockStairs(IBlockState modelState, String name, float hardness) {
		super(modelState);
		BasicBlockStairs.name = name;
		BasicBlockStairs.hardness = hardness;
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setLightOpacity(0);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0,
				new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}
	
	public static Block createWooden(final IBlockState modelState, String name, float hardness, String tool, int level) {
        final Block block = new BasicBlockStairs(modelState, name, hardness, tool, level);
        Blocks.FIRE.setFireInfo(block, 5, 5);
        return block;
    }

	@Override
	public BasicBlockStairs setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public BasicBlockStairs setResistance(float resistance) {
		this.blockResistance = resistance * 3.0F;
		return this;
	}

	@Override
	public BasicBlockStairs setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}
}