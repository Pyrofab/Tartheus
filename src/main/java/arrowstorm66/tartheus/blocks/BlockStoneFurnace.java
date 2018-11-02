package arrowstorm66.tartheus.blocks;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.MCreativeTabs;
import arrowstorm66.tartheus.blocks.tile.TileEntityStoneFurnace;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockStoneFurnace extends BlockContainer implements ModelRegistry {
	public static final PropertyDirection FACING;
	private final boolean isBurning;
	private static boolean keepInventory;
	protected static String name;

	public BlockStoneFurnace(String name, float hardness, String tool, int level, final boolean isBurning) {
		super(Material.ROCK);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockStoneFurnace.FACING,
				EnumFacing.NORTH));
		this.isBurning = isBurning;
		this.setCreativeTab(MCreativeTabs.T_DECORATION);
		BlockStoneFurnace.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setHarvestLevel(tool, level);
	}

	@Override
	public BlockStoneFurnace setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}

	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return Item.getItemFromBlock(MBlocks.STONE_FURNACE);
	}

	public void onBlockAdded(final World worldIn, final BlockPos pos, final IBlockState state) {
		this.setDefaultFacing(worldIn, pos, state);
	}

	private void setDefaultFacing(final World worldIn, final BlockPos pos, final IBlockState state) {
		if (!worldIn.isRemote) {
			final IBlockState iblockstate = worldIn.getBlockState(pos.north());
			final IBlockState iblockstate2 = worldIn.getBlockState(pos.south());
			final IBlockState iblockstate3 = worldIn.getBlockState(pos.west());
			final IBlockState iblockstate4 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = (EnumFacing) state.getValue((IProperty) BlockStoneFurnace.FACING);
			if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate2.isFullBlock()) {
				enumfacing = EnumFacing.SOUTH;
			} else if (enumfacing == EnumFacing.SOUTH && iblockstate2.isFullBlock() && !iblockstate.isFullBlock()) {
				enumfacing = EnumFacing.NORTH;
			} else if (enumfacing == EnumFacing.WEST && iblockstate3.isFullBlock() && !iblockstate4.isFullBlock()) {
				enumfacing = EnumFacing.EAST;
			} else if (enumfacing == EnumFacing.EAST && iblockstate4.isFullBlock() && !iblockstate3.isFullBlock()) {
				enumfacing = EnumFacing.WEST;
			}
			worldIn.setBlockState(pos, state.withProperty(BlockStoneFurnace.FACING, enumfacing), 2);
		}
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("incomplete-switch")
	public void randomDisplayTick(final IBlockState stateIn, final World worldIn, final BlockPos pos,
			final Random rand) {
		if (this.isBurning) {
			final EnumFacing enumfacing = (EnumFacing) stateIn.getValue((IProperty) BlockStoneFurnace.FACING);
			final double d0 = pos.getX() + 0.5;
			final double d2 = pos.getY() + rand.nextDouble() * 6.0 / 16.0;
			final double d3 = pos.getZ() + 0.5;
			final double d4 = 0.52;
			final double d5 = rand.nextDouble() * 0.6 - 0.3;
			if (rand.nextDouble() < 0.1) {
				worldIn.playSound(pos.getX() + 0.5, (double) pos.getY(), pos.getZ() + 0.5,
						SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
			}
			switch (enumfacing) {
			case WEST: {
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52, d2, d3 + d5, 0.0, 0.0, 0.0
				);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52, d2, d3 + d5, 0.0, 0.0, 0.0);
				break;
			}
			case EAST: {
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52, d2, d3 + d5, 0.0, 0.0, 0.0
				);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52, d2, d3 + d5, 0.0, 0.0, 0.0);
				break;
			}
			case NORTH: {
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d5, d2, d3 - 0.52, 0.0, 0.0, 0.0
				);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d5, d2, d3 - 0.52, 0.0, 0.0, 0.0);
				break;
			}
			case SOUTH: {
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d5, d2, d3 + 0.52, 0.0, 0.0, 0.0
				);
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d5, d2, d3 + 0.52, 0.0, 0.0, 0.0);
				break;
			}
			}
		}
	}

	public boolean onBlockActivated(final World worldIn, final BlockPos pos, final IBlockState state,
			final EntityPlayer playerIn, final EnumHand hand, final EnumFacing facing, final float hitX,
			final float hitY, final float hitZ) {
		if (worldIn.isRemote) {
			return true;
		}
		final TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof TileEntityStoneFurnace) {
			playerIn.displayGUIChest((IInventory) tileentity);
			playerIn.addStat(StatList.FURNACE_INTERACTION);
		}
		return true;
	}

	public static void setState(final boolean active, final World worldIn, final BlockPos pos) {
		final IBlockState iblockstate = worldIn.getBlockState(pos);
		final TileEntity tileentity = worldIn.getTileEntity(pos);
		BlockStoneFurnace.keepInventory = true;
		if (active) {
			worldIn.setBlockState(pos, MBlocks.STONE_FURNACE_ACTIVE.getDefaultState().withProperty(
					BlockStoneFurnace.FACING, iblockstate.getValue(BlockStoneFurnace.FACING)), 3);
			worldIn.setBlockState(pos, MBlocks.STONE_FURNACE_ACTIVE.getDefaultState().withProperty(
					BlockStoneFurnace.FACING, iblockstate.getValue(BlockStoneFurnace.FACING)), 3);
		} else {
			worldIn.setBlockState(pos, MBlocks.STONE_FURNACE.getDefaultState().withProperty(
					BlockStoneFurnace.FACING, iblockstate.getValue(BlockStoneFurnace.FACING)), 3);
			worldIn.setBlockState(pos, MBlocks.STONE_FURNACE.getDefaultState().withProperty(
					BlockStoneFurnace.FACING, iblockstate.getValue(BlockStoneFurnace.FACING)), 3);
		}
		BlockStoneFurnace.keepInventory = false;
		if (tileentity != null) {
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	public TileEntity createNewTileEntity(final World worldIn, final int meta) {
		return new TileEntityStoneFurnace();
	}

	public IBlockState onBlockPlaced(final World worldIn, final BlockPos pos, final EnumFacing facing, final float hitX,
			final float hitY, final float hitZ, final int meta, final EntityLivingBase placer) {
		return this.getDefaultState().withProperty(BlockStoneFurnace.FACING,
				placer.getHorizontalFacing().getOpposite());
	}

	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state,
			final EntityLivingBase placer, final ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(BlockStoneFurnace.FACING,
				placer.getHorizontalFacing().getOpposite()), 2);
		if (stack.hasDisplayName()) {
			final TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof TileEntityStoneFurnace) {
				((TileEntityStoneFurnace) tileentity).setCustomInventoryName(stack.getDisplayName());
			}
		}
	}

	public void breakBlock(final World worldIn, final BlockPos pos, final IBlockState state) {
		if (!BlockStoneFurnace.keepInventory) {
			final TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof TileEntityStoneFurnace) {
				InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}
		}
		super.breakBlock(worldIn, pos, state);
	}

	public boolean hasComparatorInputOverride(final IBlockState state) {
		return true;
	}

	public int getComparatorInputOverride(final IBlockState blockState, final World worldIn, final BlockPos pos) {
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	public ItemStack getItem(final World worldIn, final BlockPos pos, final IBlockState state) {
		return new ItemStack(MBlocks.STONE_FURNACE);
	}

	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	public IBlockState getStateFromMeta(final int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);
		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}
		return this.getDefaultState().withProperty(BlockStoneFurnace.FACING, enumfacing);
	}

	public int getMetaFromState(final IBlockState state) {
		return ((EnumFacing) state.getValue((IProperty) BlockStoneFurnace.FACING)).getIndex();
	}

	public IBlockState withRotation(final IBlockState state, final Rotation rot) {
		return state.withProperty(BlockStoneFurnace.FACING,
				rot.rotate((EnumFacing) state.getValue((IProperty) BlockStoneFurnace.FACING)));
	}

	public IBlockState withMirror(final IBlockState state, final Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue((IProperty) BlockStoneFurnace.FACING)));
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockStoneFurnace.FACING);
	}

	static {
		FACING = BlockHorizontal.FACING;
	}
}