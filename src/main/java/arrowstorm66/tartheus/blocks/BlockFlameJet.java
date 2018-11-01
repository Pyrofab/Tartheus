package arrowstorm66.tartheus.blocks;

import net.minecraft.block.material.*;
import net.minecraft.creativetab.*;
import net.minecraft.block.state.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.entity.player.*;
import net.minecraft.block.*;
import net.minecraftforge.fluids.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.block.properties.*;
import java.util.*;

import arrowstorm66.tartheus.MCreativeTabs;
import arrowstorm66.tartheus.base.BasicBlock;
import arrowstorm66.tartheus.blocks.tile.TileEntityFlameJet;
import arrowstorm66.tartheus.blocks.tile.TileEntityPoppingJet;
import arrowstorm66.tartheus.util.ModelRegistry;
import arrowstorm66.tartheus.util.ModelUtils;

public class BlockFlameJet extends BasicBlock implements ModelRegistry {
	public static final IProperty<FireJetVariant> VARIANT;

	public BlockFlameJet(Material material, String name, float hardness, String tool, int level) {
		super(Material.ROCK, name, hardness, tool, level);
		this.setHardness(1.5f);
		this.setSoundType(SoundType.STONE);
		this.setCreativeTab((CreativeTabs) MCreativeTabs.T_DECORATION);
		this.setTickRandomly(true);
	}

	public BlockStateContainer createBlockState() {
		return new BlockStateContainer((Block) this, new IProperty[] { BlockFlameJet.VARIANT });
	}

	@Deprecated
	public IBlockState getStateFromMeta(final int meta) {
		final FireJetVariant[] values = FireJetVariant.values();
		final FireJetVariant variant = values[meta % values.length];
		return this.getDefaultState().withProperty((IProperty) BlockFlameJet.VARIANT, (Comparable) variant);
	}

	public int getMetaFromState(final IBlockState state) {
		return ((FireJetVariant) state.getValue((IProperty) BlockFlameJet.VARIANT)).ordinal();
	}

	@SuppressWarnings("incomplete-switch")
	public int damageDropped(IBlockState state) {
		switch ((FireJetVariant) state.getValue((IProperty) BlockFlameJet.VARIANT)) {
		case JET_POPPING: {
			state = state.withProperty((IProperty) BlockFlameJet.VARIANT, (Comparable) FireJetVariant.JET_IDLE);
			break;
		}
		case JET_FLAME: {
			state = state.withProperty((IProperty) BlockFlameJet.VARIANT, (Comparable) FireJetVariant.JET_IDLE);
			break;
		}
      }
		return this.getMetaFromState(state);
	}

	public int getLightValue(final IBlockState state) {
		switch ((FireJetVariant) state.getValue((IProperty) BlockFlameJet.VARIANT)) {
		case JET_FLAME: {
			return 15;
		}
		default: {
			return 8;
		}
		}
	}

	public void updateTick(final World world, final BlockPos pos, final IBlockState state, final Random random) {
		if (!world.isRemote && state.getValue((IProperty) BlockFlameJet.VARIANT) == FireJetVariant.JET_IDLE) {
			world.setBlockState(pos,
					state.withProperty((IProperty) BlockFlameJet.VARIANT, (Comparable) FireJetVariant.JET_POPPING), 2);
		}
	}

	public boolean hasTileEntity(final IBlockState state) {
		switch ((FireJetVariant) state.getValue((IProperty) BlockFlameJet.VARIANT)) {
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
		switch ((FireJetVariant) state.getValue((IProperty) BlockFlameJet.VARIANT)) {
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
		list.add((ItemStack) new ItemStack((Block) this, 1, FireJetVariant.JET_IDLE.ordinal()));
	}

	@SideOnly(Side.CLIENT)
	public void registerModel() {
		final FireJetVariant[] array;
		final FireJetVariant[] variants = array = new FireJetVariant[] { FireJetVariant.JET_IDLE };
		for (final FireJetVariant variant : array) {
			ModelUtils.registerToState(this, variant.ordinal(),
					this.getDefaultState().withProperty((IProperty) BlockFlameJet.VARIANT, (Comparable) variant));
		}
	}

	static {
		VARIANT = (IProperty) PropertyEnum.create("variant", (Class) FireJetVariant.class);
	}

	public enum FireJetVariant implements IStringSerializable {
		JET_IDLE, JET_POPPING, JET_FLAME;

		public String getName() {
			return this.name().toLowerCase(Locale.ROOT);
		}
	}
}
