package arrowstorm66.tartheus.blocks.tile;

import arrowstorm66.tartheus.MBlocks;
import arrowstorm66.tartheus.blocks.BlockFlameJet;
import arrowstorm66.tartheus.blocks.BlockFlameJet.FireJetVariant;
import arrowstorm66.tartheus.particles.ParticleSpawner;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class TileEntityFlameJet extends TileEntity implements ITickable {
	private int counter;
	private FireJetVariant nextVariant;

	public TileEntityFlameJet(final FireJetVariant variant) {
		this.counter = 0;
		this.nextVariant = variant;
	}

	public TileEntityFlameJet() {
		this.counter = 0;
	}

	public void update() {
		if (++this.counter > 80) {
			this.counter = 0;
			if (!this.world.isRemote && this.world.getBlockState(this.pos).getBlock() == MBlocks.FLAME_JET) {
				this.world.setBlockState(this.pos, MBlocks.FLAME_JET.getDefaultState()
						.withProperty(BlockFlameJet.VARIANT, this.nextVariant));
			}
		} else if (this.counter % 2 == 0) {
			this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.pos.getX() + 0.5, this.pos.getY() + 1.0,
					this.pos.getZ() + 0.5, 0.0, 0.0, 0.0);
			ParticleSpawner.spawnHugeFlame(this.world, this.pos.getX() + 0.5, this.pos.getY() + 1.0,
					this.pos.getZ() + 0.5, 0.0, 0.5, 0.0);
			ParticleSpawner.spawnHugeFlame(this.world, this.pos.getX() + 0.3, this.pos.getY() + 1.0,
					this.pos.getZ() + 0.5, 0.0, 0.5, 0.0);
			ParticleSpawner.spawnHugeFlame(this.world, this.pos.getX() + 0.5, this.pos.getY() + 1.0,
					this.pos.getZ() + 0.3, 0.0, 0.5, 0.0);
			ParticleSpawner.spawnHugeFlame(this.world, this.pos.getX() + 0.7, this.pos.getY() + 1.0,
					this.pos.getZ() + 0.5, 0.0, 0.5, 0.0);
			ParticleSpawner.spawnHugeFlame(this.world, this.pos.getX() + 0.5, this.pos.getY() + 1.0,
					this.pos.getZ() + 0.7, 0.0, 0.5, 0.0);
		}
		if (this.counter % 4 == 0) {
			this.world.playSound(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5,
					SoundEvents.ENTITY_BLAZE_BURN, SoundCategory.BLOCKS, 2.0f + this.world.rand.nextFloat(),
					this.world.rand.nextFloat() * 0.7f + 0.3f, false);
		}
		if (!this.world.isRemote && this.counter % 5 == 0) {
			final List<Entity> entitiesInRange = (List<Entity>) this.world.getEntitiesWithinAABB((Class) Entity.class,
					new AxisAlignedBB(this.pos.add(-2, 0, -2), this.pos.add(2, 4, 2)));
			for (final Entity entity : entitiesInRange) {
				if (!entity.isImmuneToFire()) {
					entity.attackEntityFrom(DamageSource.IN_FIRE, 2.0f);
					entity.setFire(15);
				}
			}
		}
	}

	public void readFromNBT(final NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.nextVariant = FireJetVariant.values()[compound.getInteger("NextMeta")];
	}

	public NBTTagCompound writeToNBT(final NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("NextMeta", this.nextVariant.ordinal());
		return compound;
	}
}
