package arrowstorm66.tartheus.entity.ai;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class EntityAIAvoidEntityCompletely<T extends Entity> extends EntityAIBase {
	private final Predicate<Entity> canBeSeenSelector;
	/** The entity we are attached to */
	protected EntityCreature entity;
	private final double farSpeed;
	private final double nearSpeed;
	protected T closestLivingEntity;
	private final float avoidDistance;
	/** The PathEntity of our entity */
	private Path entityPathEntity;
	/** The PathNavigate of our entity */
	private final PathNavigate entityPathNavigate;
	/** Class of entity this behavior seeks to avoid */
	private final Class<T> classToAvoid;
	private final Predicate<? super T> avoidTargetSelector;

	public EntityAIAvoidEntityCompletely(EntityCreature entityIn, Class<T> classToAvoidIn, float avoidDistanceIn,
			double farSpeedIn, double nearSpeedIn) {
		this(entityIn, classToAvoidIn, Predicates.alwaysTrue(), avoidDistanceIn, farSpeedIn, nearSpeedIn);
	}

	public EntityAIAvoidEntityCompletely(EntityCreature entityIn, Class<T> classToAvoidIn,
			Predicate<? super T> avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
		this.canBeSeenSelector = Entity::isEntityAlive;
		this.entity = entityIn;
		this.classToAvoid = classToAvoidIn;
		this.avoidTargetSelector = avoidTargetSelectorIn;
		this.avoidDistance = avoidDistanceIn;
		this.farSpeed = farSpeedIn;
		this.nearSpeed = nearSpeedIn;
		this.entityPathNavigate = entityIn.getNavigator();
		this.setMutexBits(1);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		List<T> list = this.entity.world.getEntitiesWithinAABB(this.classToAvoid,
				this.entity.getEntityBoundingBox().grow((double) this.avoidDistance, this.avoidDistance / 2, (double) this.avoidDistance),
				Predicates.and(new Predicate[] { EntitySelectors.CAN_AI_TARGET, this.canBeSeenSelector,
						this.avoidTargetSelector }));

		if (list.isEmpty()) {
			return false;
		} else {
			this.closestLivingEntity = list.get(0);
			Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.entity, 16, 7, new Vec3d(
					this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));
			if (vec3d == null) {
				return false;
			} else if (this.closestLivingEntity.getDistanceSq(vec3d.x, vec3d.y, vec3d.z) < this.closestLivingEntity
					.getDistanceSq(this.entity)) {
				return false;
			} else {
				this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
				return this.entityPathEntity != null;
			}
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean shouldContinueExecuting() {
		return !this.entityPathNavigate.noPath();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by
	 * another one
	 */
	public void resetTask() {
		this.closestLivingEntity = null;
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void updateTask() {
		if (this.entity.getDistanceSq(this.closestLivingEntity) < 49.0D) {
			this.entity.getNavigator().setSpeed(this.nearSpeed);
		} else {
			this.entity.getNavigator().setSpeed(this.farSpeed);
		}
	}
}