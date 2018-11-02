package arrowstorm66.tartheus.entity;

import arrowstorm66.tartheus.MSounds;
import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.base.entity.EntityHostile;
import arrowstorm66.tartheus.entity.ai.EntityAICustomAttackMelee;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityScorpion extends EntityHostile {

	private int attackTimer;
	public static final ResourceLocation LOOT = new ResourceLocation(Tartheus.MODID, "entity/scorpion");
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.createKey(EntitySpider.class,
			DataSerializers.BYTE);

	public EntityScorpion(World worldIn) {
		super(worldIn, EntityScorpion.class, 0.22F, 5, true);
		setSize(1.4F, 0.9F);
	}

	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		return LOOT;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6D);
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
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPig.class, true));
	}

	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(CLIMBING, (byte) 0);
	}

	protected PathNavigate createNavigator(World worldIn) {
		return new PathNavigateClimber(this, worldIn);
	}

	public void onUpdate() {
		super.onUpdate();

		if (!this.world.isRemote && !this.IsJumpingUp()) {
			this.setBesideClimbableBlock(this.collidedHorizontally);
		}
	}

	public boolean IsJumpingUp() {
		return this.isJumping;
	}

	public boolean isOnLadder() {
		return this.isBesideClimbableBlock();
	}

	public void setInWeb() {
	}

	public boolean isPotionApplicable(PotionEffect potioneffectIn) {
		return potioneffectIn.getPotion() != MobEffects.POISON && super.isPotionApplicable(potioneffectIn);
	}

	public boolean isBesideClimbableBlock() {
		return (this.dataManager.get(CLIMBING) & 1) != 0;
	}

	public void setBesideClimbableBlock(boolean climbing) {
		byte b0 = this.dataManager.get(CLIMBING);

		if (climbing) {
			b0 = (byte) (b0 | 1);
		} else {
			b0 = (byte) (b0 & -2);
		}

		this.dataManager.set(CLIMBING, b0);
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

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	public float getEyeHeight() {
		return 0.6F;
	}

	protected int decreaseAirSupply(int air) {
		return air - 10;
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.attackTimer > 0) {
			--this.attackTimer;
		}
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		super.attackEntityAsMob(entityIn);
		this.attackTimer = 10;
		this.world.setEntityState(this, (byte) 4);
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this),
				(float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
		if (entityIn instanceof EntityLivingBase) {
			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 140, 0));
		}
		this.playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 1.0F, 1.3F);
		return flag;
	}

	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
			this.playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 1.0F, 1.3F);
		} else {
			super.handleStatusUpdate(id);
		}
	}

	@SideOnly(Side.CLIENT)
	public int getAttackTimer() {
		return this.attackTimer;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}
}