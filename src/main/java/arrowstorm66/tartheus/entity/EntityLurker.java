package arrowstorm66.tartheus.entity;

import arrowstorm66.tartheus.MSounds;
import arrowstorm66.tartheus.base.entity.EntityHostile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EntityLurker extends EntityHostile {
	private static final DataParameter<Boolean> IS_SEEN = EntityDataManager
			.createKey(EntityLurker.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> TIME_VIEWED = EntityDataManager
			.createKey(EntityLurker.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> HOSTILE = EntityDataManager
			.createKey(EntityLurker.class, DataSerializers.BOOLEAN);

	public EntityLurker(final World world) {
		super(world, EntityLurker.class, 1.0F, 25, false);
		this.tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.5, 64.0f));
		this.tasks.addTask(2, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackMelee(this, 2.0, false));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0));
		this.tasks.addTask(6,
				new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this,
				EntityPlayer.class, true));
		this.stepHeight = 1.0F;
		setSize(1.0F, 3.3F);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5D);
	}

	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(EntityLurker.IS_SEEN, false);
		this.getDataManager().register(EntityLurker.TIME_VIEWED, 0);
		this.getDataManager().register(EntityLurker.HOSTILE, false);
	}

	protected SoundEvent getAmbientSound() {
		return this.isHostile() ? null : MSounds.LURKER_BREATH;
	}

	protected SoundEvent getHurtSound(final DamageSource damageSourceIn) {
		return null;
	}

	protected SoundEvent getDeathSound() {
		return null;
	}

	public void move(final MoverType type, final double x, final double y, final double z) {
		if (!this.isSeen()) {
			super.move(type, x, y, z);
		}
	}

	protected void jump() {
		if (!this.isSeen()) {
			super.jump();
		}
	}

	public boolean isSeen() {
		return this.getDataManager().get(EntityLurker.IS_SEEN);
	}

	public void setSeen(final boolean beingViewed) {
		this.getDataManager().set(EntityLurker.IS_SEEN, beingViewed);
	}

	public int getSeenTime() {
		return this.getDataManager().get(EntityLurker.TIME_VIEWED);
	}

	public void setSeenTime(final int time) {
		this.getDataManager().set(EntityLurker.TIME_VIEWED, time);
	}

	public boolean isHostile() {
		return this.getDataManager().get(EntityLurker.HOSTILE);
	}

	public void setHostile(final boolean beingViewed) {
		this.getDataManager().set(EntityLurker.HOSTILE, beingViewed);
	}

	public void writeEntityToNBT(final NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("isSeen", this.isSeen());
		compound.setInteger("timeSeen", this.getSeenTime());
		compound.setBoolean("isHostile", this.isHostile());
	}

	public void readEntityFromNBT(final NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		if (compound.hasKey("isSeen")) {
			this.setSeen(compound.getBoolean("isSeen"));
		}
		if (compound.hasKey("timeSeen")) {
			this.setSeenTime(compound.getInteger("timeSeen"));
		}
		if (compound.hasKey("isHostile")) {
			this.setHostile(compound.getBoolean("isHostile"));
		}
	}

	private boolean getIsInView() {
		for (final EntityPlayer player : FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList()
				.getPlayers()) {
			if (isInSight(player, this) && !player.isSpectator()) {
				this.setSeenTime(this.getSeenTime() + 1);
				if (this.getSeenTime() == 1 && !isDarkForPlayer(this, player)
						&& !player.isPotionActive(MobEffects.BLINDNESS)) {
				}
				return true;
			}
		}
		this.setSeenTime(0);
		return false;
	}

	public static boolean isDarkForPlayer(final EntityLurker entity, final EntityLivingBase living) {
		return !living.isPotionActive(MobEffects.NIGHT_VISION) && entity.world.getLight(entity.getPosition()) == 0;
	}

	public static boolean isInSight(final EntityLivingBase viewer, final Entity entityBeingWatched) {
		double dx;
		double dz;
		for (dx = entityBeingWatched.posX - viewer.posX, dz = entityBeingWatched.posZ - viewer.posZ; dx * dx
				+ dz * dz < 1.0E-4; dx = (Math.random() - Math.random())
						* 0.01, dz = (Math.random() - Math.random()) * 0.01) {
		}
		while (viewer.rotationYaw > 360.0f) {
			viewer.rotationYaw -= 360.0f;
		}
		while (viewer.rotationYaw < -360.0f) {
			viewer.rotationYaw += 360.0f;
		}
		float yaw;
		for (yaw = (float) (Math.atan2(dz, dx) * 180.0 / 3.141592653589793)
				- viewer.rotationYaw, yaw -= 90.0f; yaw < -180.0f; yaw += 360.0f) {
		}
		while (yaw >= 180.0f) {
			yaw -= 360.0f;
		}
		return yaw < 60.0f && yaw > -60.0f && viewer.canEntityBeSeen(entityBeingWatched);
	}

	public boolean isInSightUpClose(final EntityLivingBase entityWatched) {
		Vec3d vec3d = entityWatched.getLook(1.0F).normalize();
		Vec3d vec3d1 = new Vec3d(this.posX - entityWatched.posX, this.getEntityBoundingBox().minY
				+ (double) this.getEyeHeight() - (entityWatched.posY + (double) entityWatched.getEyeHeight()),
				this.posZ - entityWatched.posZ);
		double d0 = vec3d1.lengthVector();
		vec3d1 = vec3d1.normalize();
		double d1 = vec3d.dotProduct(vec3d1);
		return d1 > 1.0D - 0.025D / d0 && entityWatched.canEntityBeSeen(this);
	}

	public void onUpdate() {
		super.onUpdate();
		if (!this.world.isRemote) {
			if (this.getAttackTarget() != null) {
				final float distance = (float) this.getDistance(this.getAttackTarget().posX,
						this.getAttackTarget().getEntityBoundingBox().minY, this.getAttackTarget().posZ);
				EntityPlayer player = (EntityPlayer) this.getAttackTarget();
				if (player != null && distance < 3.0f && this.isSeen() && this.isInSightUpClose(this.getAttackTarget())
						&& !player.capabilities.isCreativeMode) {
					this.playSound(MSounds.LURKER_ROAR, 5.0F, 1.0F);
					this.setHostile(true);
				}
			}
			if (!this.isHostile()) {
				this.setSeen(this.getIsInView());
			} else {
				this.setSeen(false);
			}
		}
	}

	protected boolean isMovementBlocked() {
		if (this.getAttackTarget() != null) {
			final float distance = (float) this.getDistance(this.getAttackTarget().posX,
					this.getAttackTarget().getEntityBoundingBox().minY, this.getAttackTarget().posZ);
			EntityPlayer player = (EntityPlayer) this.getAttackTarget();
			return distance < 3.0f && !this.isHostile() && !player.capabilities.isCreativeMode;
		} else {
			return this.isSeen();
		}
	}

	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source != null && source.getTrueSource() instanceof EntityPlayer) {
			if (!this.isHostile()) {
				this.playSound(MSounds.LURKER_ROAR, 5.0F, 1.0F);
				this.setHostile(true);
			}
		}
		return !this.isEntityInvulnerable(source) && super.attackEntityFrom(source, amount);
	}

	public boolean getCanSpawnHere() {
		return this.rand.nextInt(10) == 0 && this.world.getWorldTime() % 24000L == 13000L && this.posY <= 33 && this
				.getBlockPathWeight(new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ)) >= 0.0F;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	public float getEyeHeight() {
		return 3.0F;
	}

	public int getTalkInterval() {
		return 160;
	}

	public int getHorizontalFaceSpeed() {
		return 500;
	}

	public int getVerticalFaceSpeed() {
		return 500;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}
}