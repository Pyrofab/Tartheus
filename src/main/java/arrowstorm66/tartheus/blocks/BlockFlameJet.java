package arrowstorm66.tartheus.blocks;

import arrowstorm66.tartheus.MCreativeTabs;
import arrowstorm66.tartheus.base.BasicBlock;
import arrowstorm66.tartheus.blocks.tile.TileEntityFlameJet;
import arrowstorm66.tartheus.blocks.tile.TileEntityPoppingJet;
import arrowstorm66.tartheus.util.ModelRegistry;
import arrowstorm66.tartheus.util.ModelUtils;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Locale;
import java.util.Random;

public class BlockFlameJet extends BasicBlock implements ModelRegistry {
	public static final IProperty<FireJetVariant> VARIANT;

	public BlockFlameJet(Material material, String name, float hardness, String tool, int level) {
		super(Material.ROCK, name, hardness, tool, level);
		this.setHardness(1.5f);
		this.setSoundType(SoundType.STONE);
		this.setCreativeTab(MCreativeTabs.T_DECORATION);
		this.setTickRandomly(true);
	}

	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BlockFlameJet.VARIANT);
	}

	@Deprecated
	public IBlockState getStateFromMeta(final int meta) {
		final FireJetVariant[] values = FireJetVariant.values();
		final FireJetVariant variant = values[meta % values.length];
		return this.getDefaultState().withProperty(BlockFlameJet.VARIANT, variant);
	}

	public int getMetaFromState(final IBlockState state) {
		return state.getValue(BlockFlameJet.VARIANT).ordinal();
	}

	@SuppressWarnings("incomplete-switch")
	public int damageDropped(IBlockState state) {
		switch (state.getValue(BlockFlameJet.VARIANT)) {
		case JET_POPPING: {
			state = state.withProperty(BlockFlameJet.VARIANT, FireJetVariant.JET_IDLE);
			break;
		}
		case JET_FLAME: {
			state = state.withProperty(BlockFlameJet.VARIANT, FireJetVariant.JET_IDLE);
			break;
		}
      }
		return this.getMetaFromState(state);
	}

	public int getLightValue(final IBlockState state) {
		switch (state.getValue(BlockFlameJet.VARIANT)) {
		case JET_FLAME: {
			return 15;
		}
		default: {
			return 8;
		}
		}
	}

	public void updateTick(final World world, final BlockPos pos, final IBlockState state, final Random random) {
		if (!world.isRemote && state.getValue(BlockFlameJet.VARIANT) == FireJetVariant.JET_IDLE) {
			world.setBlockState(pos,
					state.withProperty(BlockFlameJet.VARIANT, FireJetVariant.JET_POPPING), 2);
		}
	}

	public boolean hasTileEntity(final IBlockState state) {
		switch (state.getValue(BlockFlameJet.VARIANT)) {
		case JET_POPPING:
		case JET_FLAME: {
			return true;
		}
		default: {
			return false;
		}
		}
	}

	public TileEntity createTileEntity(final World world, final IBlockState state) {
		switch (state.getValue(BlockFlameJet.VARIANT)) {
		case JET_POPPING: {
			return new TileEntityPoppingJet(FireJetVariant.JET_FLAME);
		}
		case JET_FLAME: {
			return new TileEntityFlameJet(FireJetVariant.JET_IDLE);
		}
		default: {
			return null;
		}
		}
	}

	public void getSubBlocks(final CreativeTabs creativeTab, final NonNullList<ItemStack> list) {
		list.add(new ItemStack(this, 1, FireJetVariant.JET_IDLE.ordinal()));
	}

	@SideOnly(Side.CLIENT)
	public void registerModel() {
		final FireJetVariant[] array;
		final FireJetVariant[] variants = array = new FireJetVariant[] { FireJetVariant.JET_IDLE };
		for (final FireJetVariant variant : array) {
			ModelUtils.registerToState(this, variant.ordinal(),
					this.getDefaultState().withProperty(BlockFlameJet.VARIANT, variant));
		}
	}

	static {
		VARIANT = PropertyEnum.create("variant", FireJetVariant.class);
	}

	public enum FireJetVariant implements IStringSerializable {
		JET_IDLE, JET_POPPING, JET_FLAME;

		public String getName() {
			return this.name().toLowerCase(Locale.ROOT);
		}
	}
}
