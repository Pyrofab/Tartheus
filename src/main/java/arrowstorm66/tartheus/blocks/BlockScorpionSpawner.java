package arrowstorm66.tartheus.blocks;

import arrowstorm66.tartheus.blocks.tile.TileEntityScorpionSpawner;
import arrowstorm66.tartheus.util.ModelRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockScorpionSpawner extends BlockContainer implements ModelRegistry  {

	protected static String name;

	public BlockScorpionSpawner(String name) {
		super(Material.ROCK);
		BlockScorpionSpawner.name = name;
		disableStats();
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(5.0f);
	}

	@Override
	public BlockScorpionSpawner setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public BlockScorpionSpawner setSoundType(SoundType tile) {
		this.blockSoundType = tile;
		return this;
	}
    
    public int quantityDropped(Random random)
    {
        return 0;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.AIR;
    }
    
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityScorpionSpawner();
    }
    
    public MapColor getMapColor(IBlockState state)
    {
        return MapColor.BLACK;
    }
    
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }
    
    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        return 15 + RANDOM.nextInt(15) + RANDOM.nextInt(15);
    }


    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return ItemStack.EMPTY;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
}