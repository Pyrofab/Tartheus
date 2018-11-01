package arrowstorm66.tartheus.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityVinegar extends EntityThrowable
{
    public EntityVinegar(World worldIn)
    {
        super(worldIn);
    }

    public EntityVinegar(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityVinegar(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null && result.entityHit instanceof EntityLivingBase)
        {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)0);
            ((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 0));
            ((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 200, -3));
        }

        if (!this.world.isRemote)
        {
            this.setDead();
        }
    }
}