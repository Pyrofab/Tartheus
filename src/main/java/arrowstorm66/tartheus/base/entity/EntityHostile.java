package arrowstorm66.tartheus.base.entity;

import arrowstorm66.tartheus.config.ConfigEntity;
import arrowstorm66.tartheus.dangerlevel.CapabilityDangerLevel;
import arrowstorm66.tartheus.dangerlevel.IDangerLevel;
import arrowstorm66.tartheus.entity.ai.EntityAIDodgeAttack;
import arrowstorm66.tartheus.particles.ParticleDanger;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class EntityHostile extends EntityTartheus implements IMob {

	protected Random rand = new Random();
	public Class thisSpecies;
	public double knockback;
	public int exp;
	public boolean dangerlvlEnabled;

	public EntityHostile(World worldIn, Class thisSpecies, double knockback, int exp, boolean dangerlvlEnabled) {
		super(worldIn);
		this.thisSpecies = thisSpecies;
		this.knockback = knockback;
		this.experienceValue = exp;
		this.dangerlvlEnabled = dangerlvlEnabled;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	}

	protected IDangerLevel danger = this.getCapability(CapabilityDangerLevel.DANGER_LEVEL, null);

	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		livingdata = super.onInitialSpawn(difficulty, livingdata);
		List list = world.getEntitiesWithinAABB(EntityPlayer.class,
				this.getEntityBoundingBox().expand(320.0D, 320.0D, 320.0D));

		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			if (o instanceof EntityPlayer && danger != null && ConfigEntity.areDangerLevelsenabled
					&& this.dangerlvlEnabled) {
				danger.setDangerLevel(rand.nextInt(7));
				if ((danger.getDangerLevel() >= 6) && (rand.nextInt(350) == 1)) {
					danger.setDangerLevel(7);
					this.setCustomNameTag(
							TextFormatting.DARK_RED + "Omniscient " + this.getDisplayName().getFormattedText());
					this.setAlwaysRenderNameTag(true);
					((Entity) o).sendMessage(new TextComponentString(
							TextFormatting.DARK_RED + "A " + this.getDisplayName().getFormattedText()
									+ TextFormatting.DARK_RED + " was encountered in a nearby "
									+ this.world.getBiome(this.getPosition()).getBiomeName() + " Biome!"));
				} else if ((danger.getDangerLevel() >= 5) && (rand.nextInt(300) == 1)) {
					danger.setDangerLevel(6);
					this.setCustomNameTag(
							TextFormatting.LIGHT_PURPLE + "Tyrannical " + this.getDisplayName().getFormattedText());
					this.setAlwaysRenderNameTag(true);
					((Entity) o).sendMessage(new TextComponentString(
							TextFormatting.LIGHT_PURPLE + "A " + this.getDisplayName().getFormattedText()
									+ TextFormatting.LIGHT_PURPLE + " was encountered in a nearby "
									+ this.world.getBiome(this.getPosition()).getBiomeName() + " Biome!"));
				} else if ((danger.getDangerLevel() >= 4) && (rand.nextInt(250) == 1)) {
					danger.setDangerLevel(5);
					this.setCustomNameTag(TextFormatting.GOLD + "Adept " + this.getDisplayName().getFormattedText());
					this.setAlwaysRenderNameTag(true);
					((Entity) o).sendMessage(new TextComponentString(
							TextFormatting.GOLD + "A " + this.getDisplayName().getFormattedText() + TextFormatting.GOLD
									+ " was encountered in a nearby "
									+ this.world.getBiome(this.getPosition()).getBiomeName() + " Biome!"));
				} else if ((danger.getDangerLevel() >= 3) && (rand.nextInt(200) == 1)) {
					danger.setDangerLevel(4);
					this.setCustomNameTag(
							TextFormatting.GREEN + "Skillful " + this.getDisplayName().getFormattedText());
					this.setAlwaysRenderNameTag(true);
				} else if ((danger.getDangerLevel() >= 2) && (rand.nextInt(150) == 1)) {
					danger.setDangerLevel(3);
					this.setCustomNameTag(
							TextFormatting.YELLOW + "Experienced " + this.getDisplayName().getFormattedText());
					this.setAlwaysRenderNameTag(true);
				} else if ((danger.getDangerLevel() >= 1) && (rand.nextInt(100) == 1)) {
					danger.setDangerLevel(2);
					this.setCustomNameTag(TextFormatting.BLUE + "Learning " + this.getDisplayName().getFormattedText());
					this.setAlwaysRenderNameTag(true);
				} else if ((danger.getDangerLevel() >= 0) && (rand.nextInt(50) == 1)) {
					danger.setDangerLevel(1);
					this.setCustomNameTag(TextFormatting.WHITE + "Naive " + this.getDisplayName().getFormattedText());
					this.setAlwaysRenderNameTag(true);
				} else {
					danger.setDangerLevel(0);
				}
			}
		}
		return livingdata;
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	public void onLivingUpdate() {
		this.updateArmSwingProgress();
		float f = this.getBrightness();

		if (f > 0.5F) {
			this.idleTime += 2;
		}

		if (danger != null) {
			if (danger.getDangerLevel() == 7) {
				for (int i = 0; i < 2; ++i) {
					double d1 = posX + rand.nextDouble() - 0.5;
					double d2 = (posY + 0.25) - rand.nextDouble() * 0.10000000149011612D;
					double d3 = posZ + rand.nextDouble() - 0.5;
					ParticleDanger newEffect = new ParticleDanger(world, d1, d2, d3, 0, 0, 0, 1, 0.5f, 0, 0);
					Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
				}
				if (this.getHealth() <= (this.getMaxHealth() / 2)) {
					this.tasks.addTask(1, new EntityAIDodgeAttack(this, 15.0F));
					this.heal(0.1F);
				}
				if (rand.nextInt(500) == 1) {
					List list = world.getEntitiesWithinAABB(thisSpecies,
							this.getEntityBoundingBox().expand(320.0D, 320.0D, 320.0D));
					for (int i = 0; i < list.size(); i++) {
						Object o = list.get(i);
						if (o instanceof EntityHostile) {
							if ((((EntityLivingBase) o).getHealth() <= (((EntityLivingBase) o).getMaxHealth() / 2))
									&& this.isEntityAlive()) {
								((EntityHostile) o).tasks.addTask(1,
										new EntityAIDodgeAttack((EntityLivingBase) o, 15.0F));
								((EntityLivingBase) o).heal(0.1F);
							}
						}
					}
				}
			}

			if (danger.getDangerLevel() == 6) {
				for (int i = 0; i < 2; ++i) {
					double d1 = posX + rand.nextDouble() - 0.5;
					double d2 = (posY + 0.25) - rand.nextDouble() * 0.10000000149011612D;
					double d3 = posZ + rand.nextDouble() - 0.5;
					ParticleDanger newEffect = new ParticleDanger(world, d1, d2, d3, 0, 0, 0, 1, 1, 0, 0.8f);
					Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
				}
				if (this.getHealth() <= (this.getMaxHealth() / 2)) {
					this.tasks.addTask(1, new EntityAIDodgeAttack(this, 15.0F));
					this.heal(0.075F);
				}
				if (rand.nextInt(500) == 1) {
					List list = world.getEntitiesWithinAABB(thisSpecies,
							this.getEntityBoundingBox().expand(160.0D, 160.0D, 160.0D));
					for (int i = 0; i < list.size(); i++) {
						Object o = list.get(i);
						if (o instanceof EntityHostile) {
							if ((((EntityLivingBase) o).getHealth() <= (((EntityLivingBase) o).getMaxHealth() / 2))
									&& this.isEntityAlive()) {
								((EntityHostile) o).tasks.addTask(1,
										new EntityAIDodgeAttack((EntityLivingBase) o, 15.0F));
								((EntityLivingBase) o).heal(0.075F);
							}
						}
					}
				}
			}

			if (danger.getDangerLevel() == 5) {
				for (int i = 0; i < 2; ++i) {
					double d1 = posX + rand.nextDouble() - 0.5;
					double d2 = (posY + 0.25) - rand.nextDouble() * 0.10000000149011612D;
					double d3 = posZ + rand.nextDouble() - 0.5;
					ParticleDanger newEffect = new ParticleDanger(world, d1, d2, d3, 0, 0, 0, 1, 1, 0.6f, 0);
					Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
				}
				if (this.getHealth() <= (this.getMaxHealth() / 2)) {
					this.tasks.addTask(1, new EntityAIDodgeAttack(this, 15.0F));
					this.heal(0.05F);
				}
				if (rand.nextInt(500) == 1) {
					List list = world.getEntitiesWithinAABB(thisSpecies,
							this.getEntityBoundingBox().expand(80.0D, 80.0D, 80.0D));
					for (int i = 0; i < list.size(); i++) {
						Object o = list.get(i);
						if (o instanceof EntityHostile) {
							if ((((EntityLivingBase) o).getHealth() <= (((EntityLivingBase) o).getMaxHealth() / 2))
									&& this.isEntityAlive()) {
								((EntityHostile) o).tasks.addTask(1,
										new EntityAIDodgeAttack((EntityLivingBase) o, 15.0F));
								((EntityLivingBase) o).heal(0.05F);
							}
						}
					}
				}
			}

			if (danger.getDangerLevel() == 4) {
				for (int i = 0; i < 2; ++i) {
					double d1 = posX + rand.nextDouble() - 0.5;
					double d2 = (posY + 0.25) - rand.nextDouble() * 0.10000000149011612D;
					double d3 = posZ + rand.nextDouble() - 0.5;
					ParticleDanger newEffect = new ParticleDanger(world, d1, d2, d3, 0, 0, 0, 1, 0, 1, 0.1f);
					Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
				}
				if (this.getHealth() <= (this.getMaxHealth() / 2)) {
					this.tasks.addTask(1, new EntityAIDodgeAttack(this, 15.0F));
					this.heal(0.025F);
				}
				if (rand.nextInt(500) == 1) {
					List list = world.getEntitiesWithinAABB(thisSpecies,
							this.getEntityBoundingBox().expand(40.0D, 40.0D, 40.0D));
					for (int i = 0; i < list.size(); i++) {
						Object o = list.get(i);
						if (o instanceof EntityHostile) {
							if ((((EntityLivingBase) o).getHealth() <= (((EntityLivingBase) o).getMaxHealth() / 2))
									&& this.isEntityAlive()) {
								((EntityHostile) o).tasks.addTask(1,
										new EntityAIDodgeAttack((EntityLivingBase) o, 15.0F));
								((EntityLivingBase) o).heal(0.025F);
							}
						}
					}
				}
			}

			if (danger.getDangerLevel() == 3) {
				for (int i = 0; i < 2; ++i) {
					double d1 = posX + rand.nextDouble() - 0.5;
					double d2 = (posY + 0.25) - rand.nextDouble() * 0.10000000149011612D;
					double d3 = posZ + rand.nextDouble() - 0.5;
					ParticleDanger newEffect = new ParticleDanger(world, d1, d2, d3, 0, 0, 0, 1, 1, 0.9f, 0);
					Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
				}
				if (this.getHealth() <= (this.getMaxHealth() / 2)) {
					this.tasks.addTask(1, new EntityAIDodgeAttack(this, 15.0F));
					this.heal(0.01F);
				}
				if (rand.nextInt(500) == 1) {
					List list = world.getEntitiesWithinAABB(thisSpecies,
							this.getEntityBoundingBox().expand(20.0D, 20.0D, 20.0D));
					for (int i = 0; i < list.size(); i++) {
						Object o = list.get(i);
						if (o instanceof EntityHostile) {
							if ((((EntityLivingBase) o).getHealth() <= (((EntityLivingBase) o).getMaxHealth() / 2))
									&& this.isEntityAlive()) {
								((EntityHostile) o).tasks.addTask(1,
										new EntityAIDodgeAttack((EntityLivingBase) o, 15.0F));
								((EntityLivingBase) o).heal(0.01F);
							}
						}
					}
				}
			}

			if (danger.getDangerLevel() == 2) {
				for (int i = 0; i < 2; ++i) {
					double d1 = posX + rand.nextDouble() - 0.5;
					double d2 = (posY + 0.25) - rand.nextDouble() * 0.10000000149011612D;
					double d3 = posZ + rand.nextDouble() - 0.5;
					ParticleDanger newEffect = new ParticleDanger(world, d1, d2, d3, 0, 0, 0, 1, 0, 0.3f, 1);
					Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
				}
			}

			if (danger.getDangerLevel() == 1) {
				for (int i = 0; i < 2; ++i) {
					double d1 = posX + rand.nextDouble() - 0.5;
					double d2 = (posY + 0.25) - rand.nextDouble() * 0.10000000149011612D;
					double d3 = posZ + rand.nextDouble() - 0.5;
					ParticleDanger newEffect = new ParticleDanger(world, d1, d2, d3, 0, 0, 0, 1, 0.9f, 0.9f, 0.9f);
					Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
				}
			}
		}
		super.onLivingUpdate();
	}

	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		List list = world.getEntitiesWithinAABB(EntityPlayer.class,
				this.getEntityBoundingBox().expand(320.0D, 320.0D, 320.0D));
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			if (o instanceof EntityPlayer && danger != null) {
				if (danger.getDangerLevel() == 7) {
					((Entity) o).sendMessage(new TextComponentString(TextFormatting.DARK_RED + "The "
							+ this.getDisplayName().getFormattedText() + TextFormatting.DARK_RED + " was killed by "
							+ cause.getTrueSource().getDisplayName().getFormattedText()));
				}
				if (danger.getDangerLevel() == 6) {
					((Entity) o).sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "The "
							+ this.getDisplayName().getFormattedText() + TextFormatting.LIGHT_PURPLE + " was killed by "
							+ cause.getTrueSource().getDisplayName().getFormattedText()));
				}
				if (danger.getDangerLevel() == 5) {
					((Entity) o).sendMessage(new TextComponentString(TextFormatting.GOLD + "The "
							+ this.getDisplayName().getFormattedText() + TextFormatting.GOLD + " was killed by "
							+ cause.getTrueSource().getDisplayName().getFormattedText()));
				}
				if (danger.getDangerLevel() == 4) {
					((Entity) o).sendMessage(new TextComponentString(TextFormatting.GREEN + "The "
							+ this.getDisplayName().getFormattedText() + TextFormatting.GREEN + " was killed by "
							+ cause.getTrueSource().getDisplayName().getFormattedText()));
				}
				if (danger.getDangerLevel() == 3) {
					((Entity) o).sendMessage(new TextComponentString(TextFormatting.YELLOW + "The "
							+ this.getDisplayName().getFormattedText() + TextFormatting.YELLOW + " was killed by "
							+ cause.getTrueSource().getDisplayName().getFormattedText()));
				}
				if (danger.getDangerLevel() == 2) {
					((Entity) o).sendMessage(new TextComponentString(TextFormatting.BLUE + "The "
							+ this.getDisplayName().getFormattedText() + TextFormatting.BLUE + " was killed by "
							+ cause.getTrueSource().getDisplayName().getFormattedText()));
				}
				if (danger.getDangerLevel() == 1) {
					((Entity) o).sendMessage(new TextComponentString(TextFormatting.WHITE + "The "
							+ this.getDisplayName().getFormattedText() + TextFormatting.WHITE + " was killed by "
							+ cause.getTrueSource().getDisplayName().getFormattedText()));
				}
			}
		}
	}

	public SoundCategory getSoundCategory() {
		return SoundCategory.HOSTILE;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		super.onUpdate();

		if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
			this.setDead();
		}
	}

	protected SoundEvent getSwimSound() {
		return SoundEvents.ENTITY_HOSTILE_SWIM;
	}

	protected SoundEvent getSplashSound() {
		return SoundEvents.ENTITY_HOSTILE_SPLASH;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return !this.isEntityInvulnerable(source) && super.attackEntityFrom(source, amount);
	}

	protected SoundEvent getHurtSound() {
		return SoundEvents.ENTITY_HOSTILE_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_HOSTILE_DEATH;
	}

	protected SoundEvent getFallSound(int heightIn) {
		return heightIn > 4 ? SoundEvents.ENTITY_HOSTILE_BIG_FALL : SoundEvents.ENTITY_HOSTILE_SMALL_FALL;
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		super.attackEntityAsMob(entityIn);
		float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();

		if (danger != null && entityIn instanceof EntityLivingBase) {
			if (danger.getDangerLevel() >= 5) {
				((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.WITHER, 200));
			}
			if (danger.getDangerLevel() >= 4) {
				((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 300, 2));
			}
			if (danger.getDangerLevel() >= 2) {
				((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 300, 2));
			}
			if (danger.getDangerLevel() >= 1) {
				((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.HUNGER, 400, 1));
			}
		}

		if (entityIn instanceof EntityLivingBase) {
			f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(),
					((EntityLivingBase) entityIn).getCreatureAttribute());
		}

		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), f);

		if (flag) {
			if (entityIn instanceof EntityLivingBase) {
				((EntityLivingBase) entityIn).knockBack(this, (float) knockback,
						(double) MathHelper.sin(this.rotationYaw * 0.017453292F),
						(double) (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
				this.motionX *= 0.6D;
				this.motionZ *= 0.6D;
			}
		}
		return flag;
	}

	public float getBlockPathWeight(BlockPos pos) {
		return 0.5F - this.world.getLightBrightness(pos);
	}

	/**
	 * Checks to make sure the light is not too bright where the mob is spawning
	 */
	protected boolean isValidLightLevel() {
		BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

		if (this.world.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32)) {
			return false;
		} else {
			int i = this.world.getLightFromNeighbors(blockpos);

			if (this.world.isThundering()) {
				int j = this.world.getSkylightSubtracted();
				this.world.setSkylightSubtracted(10);
				i = this.world.getLightFromNeighbors(blockpos);
				this.world.setSkylightSubtracted(j);
			}

			return i <= this.rand.nextInt(8);
		}
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this
	 * entity.
	 */
	public boolean getCanSpawnHere() {
		return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel()
				&& super.getCanSpawnHere();
	}

	/**
	 * Entity won't drop items or experience points if this returns false
	 */
	protected boolean canDropLoot() {
		return true;
	}
}