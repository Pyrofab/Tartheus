package arrowstorm66.tartheus;

import net.minecraft.creativetab.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.relauncher.*;

public abstract class MCreativeTabs extends CreativeTabs
{
    public static final MCreativeTabs T_ITEMS;
    public static final MCreativeTabs T_CONSUMABLES;
	public static final MCreativeTabs T_BLOCKS;
	public static final MCreativeTabs T_DECORATION;
    public static final MCreativeTabs T_WEAPONS;
    public static final MCreativeTabs T_TOOLS;
    
    public MCreativeTabs(final String label) {
        super(label);
        this.setBackgroundImageName("tartheus.png");
    }
    
    static {
        T_ITEMS = new MCreativeTabs("tabTitems") {
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() {
                return new ItemStack(MItems.CHITIN);
            }
        };
        T_CONSUMABLES = new MCreativeTabs("tabTconsumables") {
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() {
                return new ItemStack(MItems.MONACTUS_JUICE);
            }
        };
        T_BLOCKS = new MCreativeTabs("tabTblocks") {
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() {
                return new ItemStack(MBlocks.TARTHEUS_SAND);
            }
        };
        T_DECORATION = new MCreativeTabs("tabTdecoration") {
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() {
                return new ItemStack(MBlocks.TARTHEUS_TALLGRASS);
            }
        };
        T_WEAPONS = new MCreativeTabs("tabTweapons") {
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() {
                return new ItemStack(MItems.STELLARIUM_SWORD);
            }
        };
        T_TOOLS = new MCreativeTabs("tabTtools") {
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() {
                return new ItemStack(MItems.UMBRALLIUM_PICKAXE);
            }
        };
    }
}
