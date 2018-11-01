package arrowstorm66.tartheus;

import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraftforge.common.util.*;

public class MArmorMaterials {

	public static ItemArmor.ArmorMaterial WOODEN = EnumHelper.addArmorMaterial("WOODEN", "wooden", 5,
			new int[] { 1, 2, 3, 1 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f);

	public static void initRepairMaterials() {
		MArmorMaterials.WOODEN.setRepairItem(new ItemStack(MBlocks.ALDER_LOG));
	}

}