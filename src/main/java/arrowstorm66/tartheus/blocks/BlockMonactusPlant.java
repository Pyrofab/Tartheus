package arrowstorm66.tartheus.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.MItems;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.base.BasicBlockCrop;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMonactusPlant extends BasicBlockCrop implements IGrowable {

	public static final PropertyInteger MONACTUS_AGE = PropertyInteger.create("age", 0, 2);
	private static final AxisAlignedBB[] MONACTUS_AABB = new AxisAlignedBB[] {
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D),
			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D) };

	protected static String name;

	public BlockMonactusPlant(String name, float hardness, String tool, int level) {
		super(Material.VINE, name, hardness, tool, level);
		this.name = name;
		this.setTickRandomly(true);
		this.disableStats();
		this.setDefaultState(this.blockState.getBaseState().withProperty((IProperty) BlockMonactusPlant.MONACTUS_AGE, (Comparable) 0));
	}
	
	@Override
	protected int getBonemealAgeIncrease(World worldIn) {
		return MathHelper.getInt(worldIn.rand, 0, 1);
	}

	@Override
	public BlockMonactusPlant setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}

	protected PropertyInteger getAgeProperty() {
		return BlockMonactusPlant.MONACTUS_AGE;
	}

	public int getMaxAge() {
		return 2;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { MONACTUS_AGE });
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return MONACTUS_AABB[((Integer) state.getValue(this.getAgeProperty())).intValue()];
	}

	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return MONACTUS_AABB[((Integer) blockState.getValue(this.getAgeProperty())).intValue()];
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0F);
	}

	@Override
	protected boolean canSustainBush(final IBlockState ground) {
		return ground.getBlock() == MBlocks.TARTHEUS_SAND || ground.getBlock() == MBlocks.HELL_SAND;
	}

	protected Item getSeed() {
		return MItems.MONACTUS_SEEDS;
	}

	protected Item getCrop() {
		return MItems.MONACTUS_FLESH;
	}
}