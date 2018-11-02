package arrowstorm66.tartheus;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum MToolMaterial {

	WOODEN(0, 64, 2.0F, 0.25F, 0), CHITIN(1, 161, 4.0F, 0.75F, 0), ATLASITE(2, 3202, 3.5F, 0.5F, 0), STELLARIUM(3, 1043,
			10.0F, 3.0F, 0), UMBRALLIUM(3, 1043, 6.0F, 4.0F, 0);

	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float damageVsEntity;
	private final int enchantability;
	private ItemStack repairMaterial = ItemStack.EMPTY;

	MToolMaterial(int harvestLevel, int maxUses, float efficiency, float damageVsEntity, int enchantability) {
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.damageVsEntity = damageVsEntity;
		this.enchantability = enchantability;
	}

	public int getMaxUses() {
		return this.maxUses;
	}

	public float getEfficiency() {
		return this.efficiency;
	}

	public float getAttackDamage() {
		return this.damageVsEntity;
	}

	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	@Deprecated
	public Item getRepairItem() {
		return this == WOODEN ? Item.getItemFromBlock(MBlocks.BARRENWOOD_LOG)
				: (this == CHITIN ? MItems.CHITIN
						: (this == ATLASITE ? MItems.ATLASITE_INGOT
								: (this == STELLARIUM ? MItems.STELLARIUM_INGOT
										: (this == ATLASITE ? MItems.UMBRALLIUM_INGOT : null))));
	}

	public MToolMaterial setRepairItem(ItemStack stack) {
		if (!this.repairMaterial.isEmpty())
			throw new RuntimeException("Repair material has already been set");
		if (this == WOODEN || this == CHITIN || this == ATLASITE || this == STELLARIUM || this == UMBRALLIUM)
			throw new RuntimeException("Can not change vanilla tool repair materials");
		this.repairMaterial = stack;
		return this;
	}

	public ItemStack getRepairItemStack() {
		if (!repairMaterial.isEmpty())
			return repairMaterial;
		Item ret = this.getRepairItem();
		if (ret != null)
			repairMaterial = new ItemStack(ret, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE);
		return repairMaterial;
	}
}