package arrowstorm66.tartheus.base.entity;

import arrowstorm66.tartheus.MDamageSources;
import arrowstorm66.tartheus.base.gear.BasicItemShield;
import arrowstorm66.tartheus.particles.ParticleImmunity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityTartheus extends EntityCreature {

	public EntityTartheus(World worldIn) {
		super(worldIn);
	}

	// Weapon Protection
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source != null && source.getTrueSource() instanceof EntityPlayer) {
			EntityPlayer source2 = (EntityPlayer) source.getTrueSource();
			Item heldItem = source2.getHeldItemMainhand().getItem();
			if (!source2.capabilities.isCreativeMode
					&& (heldItem instanceof ItemSword || heldItem instanceof ItemTool)) {

				if (!this.world.isRemote) {
					double d1 = posX;
					double d2 = (posY + this.height + 0.1);
					double d3 = posZ;
					ParticleImmunity newEffect = new ParticleImmunity(world, d1, d2, d3, 35);
					Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
				}

				return false;
			}
		}
		return !this.isEntityInvulnerable(source) && super.attackEntityFrom(source, amount);
	}

	// Armor And Shield Protection
	public boolean attackEntityAsMob(Entity entityIn) {
		if (entityIn instanceof EntityPlayer &&
				(((EntityPlayer) entityIn).inventory.armorItemInSlot(3).getItem() instanceof ItemArmor ||
						((EntityPlayer) entityIn).inventory.armorItemInSlot(2).getItem() instanceof ItemArmor ||
						((EntityPlayer) entityIn).inventory.armorItemInSlot(1).getItem() instanceof ItemArmor ||
						((EntityPlayer) entityIn).inventory.armorItemInSlot(0).getItem() instanceof ItemArmor)) {
			entityIn.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1F, 1.2F);
			entityIn.attackEntityFrom(MDamageSources.DIMENSIONAL_MELT, 8F);
		}
		if (entityIn instanceof EntityPlayer && ((EntityPlayer) entityIn).getHeldItemMainhand().getItem() instanceof ItemShield &&
				!(((EntityPlayer) entityIn).getHeldItemMainhand().getItem() instanceof BasicItemShield)) {
			entityIn.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1F, 1.2F);
			entityIn.attackEntityFrom(MDamageSources.DIMENSIONAL_MELT, 8F);
		}
		return true;
	}
}