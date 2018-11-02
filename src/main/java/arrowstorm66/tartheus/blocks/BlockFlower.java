package arrowstorm66.tartheus.blocks;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFlower extends BlockBush implements ModelRegistry {
	protected static final AxisAlignedBB FLOWER_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D,
			0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
	protected static String name;

	public BlockFlower(String name, float hardness, String tool, int level) {
		super(Material.VINE);
		BlockFlower.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setHarvestLevel(tool, level);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0,
				new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}

	@Override
	public BlockFlower setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public BlockFlower setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}
	
    public int getFlammability(final IBlockAccess world, final BlockPos pos, final EnumFacing side) {
        return 100;
    }
    
    public int getFireSpreadSpeed(final IBlockAccess world, final BlockPos pos, final EnumFacing side) {
        return 60;
    }

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FLOWER_AABB;
	}

	public MapColor getMapColor(IBlockState state) {
		return MapColor.GREEN;
	}

	@Override
	protected boolean canSustainBush(final IBlockState ground) {
		return ground.getBlock() == MBlocks.TARTHEUS_SAND || ground.getBlock() == MBlocks.HELL_SAND;
	}

	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}
}