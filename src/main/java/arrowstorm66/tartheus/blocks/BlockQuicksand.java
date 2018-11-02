package arrowstorm66.tartheus.blocks;

import arrowstorm66.tartheus.base.BasicBlock;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class BlockQuicksand extends BasicBlock implements ModelRegistry {

	protected static final AxisAlignedBB SAND_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	protected static final AxisAlignedBB SAND_SHAPE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);

	protected static String name;

	public BlockQuicksand(Material material, String name, float hardness, String tool, int level) {
		super(material, name, hardness, tool, level);
		BlockQuicksand.name = name;
		this.setLightOpacity(255);
	}

	@Override
	public BlockQuicksand setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public BlockQuicksand setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return SAND_AABB;
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return SAND_SHAPE_AABB;
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}