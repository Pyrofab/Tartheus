package arrowstorm66.tartheus.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIDodgeAttack extends EntityAIBase
{
    private final EntityLivingBase entity;
    private final float maxAttackDistance;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public EntityAIDodgeAttack(EntityLivingBase entity, float maxDistance)
    {
        this.entity = entity;
        this.maxAttackDistance = maxDistance * maxDistance;
    }
    
    public boolean shouldContinueExecuting()
    {
    	if ((entity.getHealth() <= (entity.getMaxHealth() / 2))){
        return (this.shouldExecute());
    	}
		return false;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	 final EntityLivingBase target = ((EntityLiving) this.entity).getAttackTarget();
         if (target == null) {
             return false;
         }
        return target.isEntityAlive();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        super.startExecuting();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        super.resetTask();
        this.seeTime = 0;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        EntityLivingBase entitylivingbase = ((EntityLiving) this.entity).getAttackTarget();

        if (entitylivingbase != null)
        {
            double d0 = this.entity.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
            boolean flag = ((EntityLiving) this.entity).getEntitySenses().canSee(entitylivingbase);
            boolean flag1 = this.seeTime > 0;

            if (flag != flag1)
            {
                this.seeTime = 0;
            }

            if (flag)
            {
                ++this.seeTime;
            }
            else
            {
                --this.seeTime;
            }

            if (d0 <= (double)this.maxAttackDistance && this.seeTime >= 20)
            {
                ((EntityLiving) this.entity).getNavigator().clearPath();
                ++this.strafingTime;
            }
            else
            {
                this.strafingTime = -1;
            }

            if (this.strafingTime >= 20)
            {
                if ((double)this.entity.getRNG().nextFloat() < 0.3D)
                {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                if ((double)this.entity.getRNG().nextFloat() < 0.3D)
                {
                    this.strafingBackwards = !this.strafingBackwards;
                }

                this.strafingTime = 0;
            }

            if (this.strafingTime > -1)
            {
                if (d0 > (double)(this.maxAttackDistance * 0.75F))
                {
                    this.strafingBackwards = false;
                }
                else if (d0 < (double)(this.maxAttackDistance * 0.25F))
                {
                    this.strafingBackwards = true;
                }
                
                ((EntityLiving) this.entity).getMoveHelper().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                ((EntityLiving) this.entity).faceEntity(entitylivingbase, 30.0F, 30.0F);
            }
            else
            {
                ((EntityLiving) this.entity).getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
            }     
        }
    }
}