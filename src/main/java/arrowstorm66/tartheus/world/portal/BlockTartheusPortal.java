package arrowstorm66.tartheus.world.portal;

import java.util.Random;

import javax.annotation.Nullable;
import javax.swing.Icon;

import com.google.common.cache.LoadingCache;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.MDimensions;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTartheusPortal extends BlockBreakable implements ModelRegistry {

	protected String name;
	private int dimgoingto;
	private int portalcooldown;
	private int light;

	public BlockTartheusPortal(Material material, String name, int dimgoingto, int portalcooldown,
			int light) {
		super(Material.PORTAL, false);
		this.name = name;
		this.dimgoingto = dimgoingto;
		this.portalcooldown = portalcooldown;
		this.light = light;
		setTickRandomly(true);
		setHardness(0.1F);
		setLightLevel(light);
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}

	@Override
	public BlockTartheusPortal setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public void onEntityCollidedWithBlock(World par1World, BlockPos pos, IBlockState state, Entity par5Entity) {
		if ((!par5Entity.isRiding()) && (!par5Entity.isBeingRidden()) && ((par5Entity instanceof EntityPlayerMP))) {
			EntityPlayerMP thePlayer = (EntityPlayerMP) par5Entity;
			if (thePlayer.timeUntilPortal > 0) {
				thePlayer.timeUntilPortal = thePlayer.getPortalCooldown();
			} else if (thePlayer.dimension != dimgoingto) {
				thePlayer.timeUntilPortal = portalcooldown;
				thePlayer.mcServer.getPlayerList().transferPlayerToDimension(thePlayer, dimgoingto, new TartheusTeleporter(
						thePlayer.mcServer.getWorld(dimgoingto)));
			} else {
				thePlayer.timeUntilPortal = portalcooldown;
				thePlayer.mcServer.getPlayerList().transferPlayerToDimension(thePlayer, 0,
						new TartheusTeleporter(thePlayer.mcServer.getWorld(0)));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public ItemStack getItem(World par1World, BlockPos pos, IBlockState state) {
		return ItemStack.EMPTY;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}