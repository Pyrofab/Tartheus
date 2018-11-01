package arrowstorm66.tartheus.base;

import net.minecraft.block.material.*;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.*;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.creativetab.*;
import net.minecraft.block.state.*;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.util.*;
import java.util.*;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.MItems;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.util.ModelRegistry;
import arrowstorm66.tartheus.util.ModelUtils;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.player.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;

public class BasicBlockFlammable extends BasicBlock implements ModelRegistry {

	protected static String name;
	private int flammability;
	private int fireSpeed;

	public BasicBlockFlammable(Material material, String name, float hardness, String tool, int level, int flammability,
			int fireSpeed) {
		super(material, name, hardness, tool, level);
		this.name = name;
	}

	public BasicBlockFlammable(Material material, String name, float hardness, int flammability, int fireSpeed) {
		super(material, name, hardness);
		this.name = name;
	}

	@Override
	public BasicBlockFlammable setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public BasicBlockFlammable setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}

	public int getFlammability(final IBlockAccess world, final BlockPos pos, final EnumFacing side) {
		return flammability;
	}

	public int getFireSpreadSpeed(final IBlockAccess world, final BlockPos pos, final EnumFacing side) {
		return fireSpeed;
	}
}