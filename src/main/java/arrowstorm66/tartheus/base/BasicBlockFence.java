package arrowstorm66.tartheus.base;

import net.minecraft.block.properties.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.entity.*;
import javax.annotation.*;

import arrowstorm66.tartheus.MCreativeTabs;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.world.*;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.block.state.*;
import net.minecraft.creativetab.CreativeTabs;

public class BasicBlockFence extends BlockFence implements ModelRegistry {
	
	protected static String name;

	public BasicBlockFence(Material materialIn, MapColor mapColorIn, String name, float hardness, String tool, int level) {
		super(materialIn, mapColorIn);
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(hardness);
		setHarvestLevel(tool, level);
	}
	
	@Override
	public BasicBlockFence setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public BasicBlockFence setResistance(float resistance) {
		this.blockResistance = resistance * 3.0F;
		return this;
	}

	@Override
	public BasicBlockFence setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}
}