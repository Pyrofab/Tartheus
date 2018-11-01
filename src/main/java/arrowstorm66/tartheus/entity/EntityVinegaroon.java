package arrowstorm66.tartheus.entity;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Predicates;

import arrowstorm66.tartheus.MSounds;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.base.entity.EntityHostile;
import arrowstorm66.tartheus.config.ConfigEntity;
import arrowstorm66.tartheus.dangerlevel.CapabilityDangerLevel;
import arrowstorm66.tartheus.dangerlevel.IDangerLevel;
import arrowstorm66.tartheus.entity.ai.EntityAICustomAttackMelee;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityVinegaroon extends EntityHostile {

	private static final DataParameter<Integer> ACIDTIMER = EntityDataManager.createKey((Class) EntityVinegaroon.class,
			DataSerializers.VARINT);
	public static final ResourceLocation LOOT = new ResourceLocation(Tartheus.MODID, "entity/vinegaroon");
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityVinegaroon.class,
			DataSerializers.BYTE);

	public EntityVinegaroon(World worldIn) {
		super(worldIn, EntityVinegaroon.class, 0.22F, 5, true);
		setSize(2.0F, 1.0F);
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		return LOOT;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5D);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataManager.register((DataParameter) EntityVinegaroon.ACIDTIMER, (Object) new Integer(0));
		this.dataManager.register(CLIMBING, Byte.valueOf((byte) 0));
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(3, new EntityAIRestrictSun(this));
		this.tasks.addTask(4, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.8D));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPig.class, 8.0F));
		this.tasks.addTask(1, new EntityAICustomAttackMelee(this, 1.2F, 1.0D, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPig.class, true));
	}

	protected PathNavigate createNavigator(World worldIn) {
		return new PathNavigateClimber(this, worldIn);
	}

	public boolean isOnLadder() {
		return this.isBesideClimbableBlock();
	}

	public void setInWeb() {
	}

	public boolean isPotionApplicable(PotionEffect potioneffectIn) {
		return potioneffectIn.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(potioneffectIn);
	}

	public boolean isBesideClimbableBlock() {
		return (((Byte) this.dataManager.get(CLIMBING)).byteValue() & 1) != 0;
	}

	public void setBesideClimbableBlock(boolean climbing) {
		byte b0 = ((Byte) this.dataManager.get(CLIMBING)).byteValue();

		if (climbing) {
			b0 = (byte) (b0 | 1);
		} else {
			b0 = (byte) (b0 & -2);
		}

		this.dataManager.set(CLIMBING, Byte.valueOf(b0));
	}

	protected SoundEvent getAmbientSound() {
		return MSounds.SCORPION_IDLE;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return MSounds.SCORPION_HURT;
	}

	protected SoundEvent getDeathSound() {
		return MSounds.SCORPION_DEATH;
	}

	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 0.75F);
	}

	protected float getSoundPitch() {
		return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.75F;
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	public int getMaxSpawnedInChunk() {
		return 1;
	}

	public float getEyeHeight() {
		return 0.75F;
	}

	protected int decreaseAirSupply(int air) {
		return air - 10;
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	public void onUpdate() {
		super.onUpdate();
		if (!this.world.isRemote && !this.IsJumpingUp()) {
			this.setBesideClimbableBlock(this.collidedHorizontally);
		}
		if (this.getAttackTarget() != null) {
			final float distance = (float) this.getDistance(this.getAttackTarget().posX,
					this.getAttackTarget().getEntityBoundingBox().minY, this.getAttackTarget().posZ);
			if (this.getTailTimer() < 100 && distance > 3.0f) {
				this.setTailTimer(this.getTailTimer() + 2);
			} else {
				this.setTailTimer(0);
			}
			if (this.getTailTimer() >= 100 && distance > 3.0f) {
				this.shootVinegar((Entity) this.getAttackTarget(), distance);
			}
		}
	}

	public boolean IsJumpingUp() {
		return this.isJumping;
	}

	public void onLivingUpdate() {
		if (!this.getPassengers().isEmpty()) {
			if (this.getPassengers().get(0).isSneaking()) {
				this.getPassengers().get(0).setSneaking(false);
			}
		}
		super.onLivingUpdate();
		if (!this.getPassengers().isEmpty()) {
			this.getLookHelper().setLookPositionWithEntity((Entity) this.getPassengers().get(0), 100.0f, 100.0f);
			final Vec3d riderPos = this.getRiderPosition();
			this.pushOutOfBlocks(riderPos.x, riderPos.y, riderPos.z);
		}
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		boolean flag = !this.getPassengers().isEmpty() ? false
				: entityIn.attackEntityFrom(DamageSource.causeMobDamage(this),
						(float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
		if (this.getPassengers().isEmpty() && !entityIn.isRiding()) {
			entityIn.startRiding((Entity) this);
		}
		return flag;
	}

	public void setTailTimer(final int size) {
		this.dataManager.set((DataParameter) EntityVinegaroon.ACIDTIMER, (Object) size);
	}

	public int getTailTimer() {
		return (int) this.dataManager.get((DataParameter) EntityVinegaroon.ACIDTIMER);
	}

	protected void shootVinegar(final Entity entity, final float distance) {
		if (distance < 16.0f && entity instanceof EntityLivingBase) {
			final double targetX = entity.posX - this.posX;
			final double targetY = entity.getEntityBoundingBox().minY + entity.height - (this.posY + this.height);
			final double targetZ = entity.posZ - this.posZ;
			this.getEntityWorld().playSound((EntityPlayer) null, this.getPosition(), SoundEvents.BLOCK_SLIME_PLACE,
					SoundCategory.HOSTILE, 1.0f, 1.0f);
			this.setTailTimer(0);
			final EntityVinegar projectile = new EntityVinegar(this.getEntityWorld(), (EntityLiving) this);
			projectile.posY = this.posY + this.height + 0.42;
			projectile.shoot(targetX, targetY, targetZ, 1.2f, 0.0f);
			this.getEntityWorld().spawnEntity((Entity) projectile);
		}
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	public void updatePassenger(final Entity passenger) {
		if (!this.getPassengers().isEmpty()) {
			final Vec3d riderPos = this.getRiderPosition();
			this.getPassengers().get(0).setPosition(riderPos.x, riderPos.y, riderPos.z);
			passenger.attackEntityFrom(DamageSource.causeMobDamage(this).setDamageBypassesArmor(), (float) 1.0f);
		}
	}

	public double getMountedYOffset() {
		return 0.5;
	}

	private Vec3d getRiderPosition() {
		if (!this.getPassengers().isEmpty()) {
			final float distance = 0.8f;
			final double var1 = Math.cos((this.rotationYaw + 90.0f) * 3.141592653589793 / 180.0) * distance;
			final double var2 = Math.sin((this.rotationYaw + 90.0f) * 3.141592653589793 / 180.0) * distance;
			return new Vec3d(this.posX + var1,
					this.posY + this.getMountedYOffset() + this.getPassengers().get(0).getYOffset(), this.posZ + var2);
		}
		return new Vec3d(this.posX, this.posY, this.posZ);
	}

	public boolean canRiderInteract() {
		return true;
	}
}