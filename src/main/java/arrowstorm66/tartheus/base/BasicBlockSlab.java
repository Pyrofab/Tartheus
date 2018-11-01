package arrowstorm66.tartheus.base;

import net.minecraft.block.properties.*;
import net.minecraft.util.math.*;
import net.minecraft.block.material.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import java.util.*;

import arrowstorm66.tartheus.MCreativeTabs;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import net.minecraft.block.state.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.*;

public class BasicBlockSlab extends BasicBlock implements ModelRegistry {
	public static final PropertyEnum<EnumBlockHalf> HALF;
	protected static final AxisAlignedBB AABB_BOTTOM_HALF;
	protected static final AxisAlignedBB AABB_TOP_HALF;
	protected static String name;

	public BasicBlockSlab(IBlockState state, String name, float hardness, String tool, int level) {
		super(state.getMaterial(), name, hardness, tool, level);
		this.setSoundType(state.getBlock().getSoundType());
		this.setDefaultState(this.blockState.getBaseState().withProperty((IProperty) BasicBlockSlab.HALF,
				(Comparable) EnumBlockHalf.BOTTOM));
		this.setLightOpacity(0);
	}
	
	@Override
	public BasicBlockSlab setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public BasicBlockSlab setResistance(float resistance) {
		this.blockResistance = resistance * 3.0F;
		return this;
	}

	@Override
	public BasicBlockSlab setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}

	protected boolean canSilkHarvest() {
		return false;
	}

	public boolean isOpaqueCube(final IBlockState state) {
		return ((EnumBlockHalf) state.getValue((IProperty) BasicBlockSlab.HALF)).equals(EnumBlockHalf.FULL);
	}

	public boolean doesSideBlockRendering(final IBlockState state, final IBlockAccess world, final BlockPos pos,
			final EnumFacing face) {
		return (this.blockState.getBaseState().getMaterial() != Material.GLASS
				&& ((EnumBlockHalf) state.getValue((IProperty) BasicBlockSlab.HALF)).equals(EnumBlockHalf.BOTTOM)
				&& face == EnumFacing.DOWN)
				|| (((EnumBlockHalf) state.getValue((IProperty) BasicBlockSlab.HALF)).equals(EnumBlockHalf.TOP)
						&& face == EnumFacing.UP)
				|| ((EnumBlockHalf) state.getValue((IProperty) BasicBlockSlab.HALF)).equals(EnumBlockHalf.FULL);
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return (this.blockState.getBaseState().getMaterial() == Material.GLASS) ? BlockRenderLayer.TRANSLUCENT
				: BlockRenderLayer.CUTOUT;
	}

	public IBlockState getStateForPlacement(final World world, final BlockPos pos, final EnumFacing facing,
			final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer,
			final EnumHand hand) {
		final IBlockState state = this.getStateFromMeta(meta);
		return ((EnumBlockHalf) state.getValue((IProperty) BasicBlockSlab.HALF)).equals(EnumBlockHalf.FULL) ? state
				: ((facing != EnumFacing.DOWN && (facing == EnumFacing.UP || hitY <= 0.5))
						? state.withProperty((IProperty) BasicBlockSlab.HALF, (Comparable) EnumBlockHalf.BOTTOM)
						: state.withProperty((IProperty) BasicBlockSlab.HALF, (Comparable) EnumBlockHalf.TOP));
	}

	public int quantityDropped(final IBlockState state, final int fortune, final Random random) {
		return ((EnumBlockHalf) state.getValue((IProperty) BasicBlockSlab.HALF)).equals(EnumBlockHalf.FULL) ? 2 : 1;
	}

	public boolean isFullCube(final IBlockState state) {
		return ((EnumBlockHalf) state.getValue((IProperty) BasicBlockSlab.HALF)).equals(EnumBlockHalf.FULL);
	}

	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		final EnumBlockHalf half = (EnumBlockHalf) state.getValue((IProperty) BasicBlockSlab.HALF);
		switch (half) {
		case TOP: {
			return BasicBlockSlab.AABB_TOP_HALF;
		}
		case BOTTOM: {
			return BasicBlockSlab.AABB_BOTTOM_HALF;
		}
		default: {
			return BasicBlockSlab.FULL_BLOCK_AABB;
		}
		}
	}

	public BlockFaceShape func_193383_a(final IBlockAccess worldIn, final IBlockState state, final BlockPos pos,
			final EnumFacing face) {
		if (state.getValue((IProperty) BasicBlockSlab.HALF) == EnumBlockHalf.FULL) {
			return BlockFaceShape.SOLID;
		}
		if (face == EnumFacing.UP && state.getValue((IProperty) BasicBlockSlab.HALF) == EnumBlockHalf.TOP) {
			return BlockFaceShape.SOLID;
		}
		return (face == EnumFacing.DOWN && state.getValue((IProperty) BasicBlockSlab.HALF) == EnumBlockHalf.BOTTOM)
				? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
	}

	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state,
			final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY,
			final float hitZ) {
		final ItemStack heldItem = player.getHeldItem(hand);
		if (!heldItem.isEmpty() && player != null
				&& ((((EnumBlockHalf) state.getValue((IProperty) BasicBlockSlab.HALF)).equals(EnumBlockHalf.TOP)
						&& facing.equals((Object) EnumFacing.DOWN))
						|| (((EnumBlockHalf) state.getValue((IProperty) BasicBlockSlab.HALF)).equals(EnumBlockHalf.BOTTOM)
								&& facing.equals((Object) EnumFacing.UP)))
				&& heldItem.getItem() == Item.getItemFromBlock((Block) this)) {
			worldIn.setBlockState(pos, state.withProperty((IProperty) BasicBlockSlab.HALF, (Comparable) EnumBlockHalf.FULL));
			if (!player.capabilities.isCreativeMode) {
				heldItem.shrink(1);
			}
			final SoundType soundtype = this.getSoundType();
			worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS,
					(soundtype.getVolume() + 1.0f) / 2.0f, soundtype.getPitch() * 0.8f);
			return true;
		}
		return super.onBlockActivated(worldIn, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer((Block) this, new IProperty[] { BasicBlockSlab.HALF });
	}

	public IBlockState getStateFromMeta(final int meta) {
		return this.getDefaultState().withProperty((IProperty) BasicBlockSlab.HALF,
				(Comparable) EnumBlockHalf.byMetadata(meta));
	}

	public int getMetaFromState(final IBlockState state) {
		return ((EnumBlockHalf) state.getValue((IProperty) BasicBlockSlab.HALF)).ordinal();
	}

	static {
		HALF = PropertyEnum.create("half", (Class) EnumBlockHalf.class);
		AABB_BOTTOM_HALF = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0);
		AABB_TOP_HALF = new AxisAlignedBB(0.0, 0.5, 0.0, 1.0, 1.0, 1.0);
	}

	public enum EnumBlockHalf implements IStringSerializable {
		TOP("top"), BOTTOM("bottom"), FULL("full");

		private final String name;

		private EnumBlockHalf(final String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}

		public static EnumBlockHalf byMetadata(int metadata) {
			if (metadata < 0 || metadata >= values().length) {
				metadata = 0;
			}
			return values()[metadata];
		}
	}
}