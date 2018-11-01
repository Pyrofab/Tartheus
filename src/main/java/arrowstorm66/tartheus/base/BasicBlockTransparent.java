package arrowstorm66.tartheus.base;

import java.util.Random;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.MMaterialSounds;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BasicBlockTransparent extends BasicBlock implements ModelRegistry {

	protected static String name;
	private final boolean ignoreSimilarity;

	public BasicBlockTransparent(Material material, String name, float hardness, String tool, int level,
			boolean ignoreSimilarityIn) {
		super(material, name, hardness, tool, level);
		this.name = name;
		this.ignoreSimilarity = ignoreSimilarityIn;
	}

	public BasicBlockTransparent(Material material, String name, float hardness, boolean ignoreSimilarityIn) {
		super(material, name, hardness);
		this.name = name;
		this.ignoreSimilarity = ignoreSimilarityIn;
	}

	@Override
	public BasicBlockTransparent setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public BasicBlockTransparent setResistance(float resistance) {
		this.blockResistance = resistance * 3.0F;
		return this;
	}

	@Override
	public BasicBlockTransparent setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public int quantityDropped(Random random) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	protected boolean canSilkHarvest() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();

		if (this == MBlocks.TARTHEUS_GLASS) {
			if (blockState != iblockstate) {
				return true;
			}

			if (block == this) {
				return false;
			}
		}

		return !this.ignoreSimilarity && block == this ? false
				: super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}
}