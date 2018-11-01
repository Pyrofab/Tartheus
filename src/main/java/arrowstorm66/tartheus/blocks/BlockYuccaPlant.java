package arrowstorm66.tartheus.blocks;

import net.minecraft.block.properties.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import java.util.*;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.MItems;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.base.BasicBlock;
import arrowstorm66.tartheus.base.BasicBlockCrop;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.block.state.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;

public class BlockYuccaPlant extends BasicBlockCrop implements IGrowable {
	public static final PropertyInteger YUCCA_AGE = PropertyInteger.create("age", 0, 11);
	private static final AxisAlignedBB[] YUCCA_AABB = new AxisAlignedBB[] {
			new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0),
			new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0),
			new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0),
			new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0),
			new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0),
			new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0) };

	public BlockYuccaPlant(String name, float hardness, String tool, int level) {
		super(Material.VINE, name, hardness, tool, level);
		this.setDefaultState(
				this.blockState.getBaseState().withProperty((IProperty) BlockYuccaPlant.YUCCA_AGE, (Comparable) 0));
		this.setTickRandomly(true);
		this.disableStats();
	}

	@Override
	public BlockYuccaPlant setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}
	
    public int getFlammability(final IBlockAccess world, final BlockPos pos, final EnumFacing side) {
        return 15;
    }
    
    public int getFireSpreadSpeed(final IBlockAccess world, final BlockPos pos, final EnumFacing side) {
        return 10;
    }

	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos pos) {
		return BlockYuccaPlant.YUCCA_AABB[(int) state.getValue((IProperty) this.getAgeProperty())];
	}

	protected PropertyInteger getAgeProperty() {
		return BlockYuccaPlant.YUCCA_AGE;
	}

	public int getMaxAge() {
		return 11;
	}

	protected int getAge(final IBlockState state) {
		return (int) state.getValue((IProperty) this.getAgeProperty());
	}

	public IBlockState withAge(final int age) {
		return this.getDefaultState().withProperty((IProperty) this.getAgeProperty(), (Comparable) age);
	}

	public boolean isMaxAge(final IBlockState state) {
		return (int) state.getValue((IProperty) this.getAgeProperty()) >= this.getMaxAge();
	}

	public void updateTick(final World worldIn, final BlockPos pos, final IBlockState state, final Random rand) {
		final int age = (int) state.getValue((IProperty) BlockYuccaPlant.YUCCA_AGE);
		if (age < 9 && (worldIn.getBlockState(pos.down()).getBlock() == this || this.canBlockStay(worldIn, pos, state))
				&& worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(2) == 0) {
			if (age < 3) {
				worldIn.setBlockState(pos, this.getStateFromMeta(age + 1));
			} else if (age == 3 && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable()) {
				worldIn.setBlockState(pos, this.getStateFromMeta(4));
				worldIn.setBlockState(pos.up(), this.getStateFromMeta(5));
			}
			if (age == 5) {
				worldIn.setBlockState(pos, this.getStateFromMeta(6));
			}
			if (age == 6 && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable()) {
				worldIn.setBlockState(pos, this.getStateFromMeta(7));
				worldIn.setBlockState(pos.up(), this.getStateFromMeta(8));
			}
			if (age == 8) {
				worldIn.setBlockState(pos, this.getStateFromMeta(11));
				worldIn.setBlockState(pos.down(), this.getStateFromMeta(10));
				worldIn.setBlockState(pos.down(2), this.getStateFromMeta(9));
			}
		}
	}

	private void dropcorn(final World world, final BlockPos pos, final IBlockState state) {
		final int s = (int) state.getValue((IProperty) BlockYuccaPlant.YUCCA_AGE);
		if (s > 9) {
			final float chance = world.rand.nextInt(4);
			final ItemStack out = new ItemStack(this.getCrop(), (chance > 2.0f) ? 2 : 1);
			Block.spawnAsEntity(world, pos, out);
		}
	}

	protected Item getSeed() {
		return MItems.YUCCA_SEEDS;
	}

	protected Item getCrop() {
		Item wood = Item.getItemFromBlock(MBlocks.BARRENWOOD_LOG);
		return wood;
	}

	public boolean isFertile(final World world, final BlockPos pos) {
		return false;
	}

	public boolean canBlockStay(final World world, final BlockPos pos, final IBlockState state) {
		return world.getBlockState(pos.down()).getBlock() == this
				|| world.getBlockState(pos.down()).getBlock() == MBlocks.TARTHEUS_SAND
				|| world.getBlockState(pos.down()).getBlock() == MBlocks.HELL_SAND
				|| world.getBlockState(pos.down()).getBlock().isFertile(world, pos) || this.canPlaceBlockAt(world, pos);
	}

	@Override
	protected boolean canSustainBush(final IBlockState ground) {
		return ground.getBlock() == MBlocks.TARTHEUS_SAND || ground.getBlock() == MBlocks.HELL_SAND;
	}

	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		final int s = (int) state.getValue((IProperty) BlockYuccaPlant.YUCCA_AGE);
		if (s > 8) {
			return this.getCrop();
		}
		return this.getSeed();
	}

	public boolean canGrow(final World world, final BlockPos pos, final IBlockState state, final boolean isClient) {
		return (int) state.getValue((IProperty) BlockYuccaPlant.YUCCA_AGE) < 9
				&& world.getBlockState(pos.up()).getMaterial().isReplaceable();
	}

	public IBlockState getStateFromMeta(final int meta) {
		return this.getDefaultState().withProperty((IProperty) BlockYuccaPlant.YUCCA_AGE, (Comparable) meta);
	}

	public int getMetaFromState(final IBlockState state) {
		return (int) state.getValue((IProperty) BlockYuccaPlant.YUCCA_AGE);
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer((Block) this, new IProperty[] { BlockYuccaPlant.YUCCA_AGE });
	}

	public boolean canPlaceBlockAt(final World world, final BlockPos pos) {
		final Block block = world.getBlockState(pos.down()).getBlock();
		return block.canSustainPlant(world.getBlockState(pos.down()), (IBlockAccess) world, pos, EnumFacing.UP,
				(IPlantable) this) || (block == this && world.getBlockState(pos).getMaterial().isReplaceable());
	}

	public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
		if (world.getBlockState(pos.up()).getBlock() == this) {
			this.dropcorn(world, pos, state);
			world.setBlockToAir(pos.up());
		}
		if (world.getBlockState(pos.up(2)).getBlock() == this) {
			this.dropcorn(world, pos, state);
			world.setBlockToAir(pos.up(2));
		}
		if (world.getBlockState(pos.down()).getBlock() == this) {
			this.dropcorn(world, pos, state);
			world.setBlockToAir(pos.down());
		}
		if (world.getBlockState(pos.down(2)).getBlock() == this) {
			this.dropcorn(world, pos, state);
			world.setBlockToAir(pos.down(2));
		}
	}

	public boolean canUseBonemeal(final World worldIn, final Random rand, final BlockPos pos, final IBlockState state) {
		return true;
	}

	public void grow(final World worldIn, final Random rand, final BlockPos pos, final IBlockState state) {
		this.updateTick(worldIn, pos, state, rand);
	}
}