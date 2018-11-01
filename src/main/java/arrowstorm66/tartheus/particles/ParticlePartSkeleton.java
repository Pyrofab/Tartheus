package arrowstorm66.tartheus.particles;

import net.minecraft.client.particle.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.world.*;
import java.awt.*;
import java.util.Random;

@SideOnly(Side.CLIENT)
public class ParticlePartSkeleton extends Particle
{
    
    public ParticlePartSkeleton(final World world, final double x, final double y, final double z, final double power, final int r, final int g, final int b) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        final Color c = new Color(r, g, b);
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.particleRed = c.getRed() / 255.0f;
        this.particleGreen = c.getGreen() / 255.0f;
        this.particleBlue = c.getBlue() / 255.0f;
        this.setParticleTextureIndex(112);
        this.setSize(0.01f, 0.01f);
        this.particleGravity = 0.06f;
        this.particleMaxAge = (int)(128.0 / (Math.random() * 0.8 + 0.2));
        this.motionX = randomFloat() / 15.0f * power;
        this.motionY = world.rand.nextFloat() / 3f;
        this.motionZ = randomFloat() / 15.0f * power;
    }
    
    public void onUpdate() {
        if (this != null) {
            super.onUpdate();
        }
        ++this.particleAge;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.particleAge >= this.particleMaxAge) {
            this.setExpired();
        }
        if (this.onGround) {
            this.particleAge = this.particleMaxAge;
        }
        this.motionY -= this.particleGravity;
        this.move(this.motionX, this.motionY, this.motionZ);
    }
    
    public static float randomFloat() {
        final Random r = new Random();
        final int positive = r.nextInt(2);
        if (positive == 0) {
            return r.nextFloat();
        }
        return -r.nextFloat();
    }
}