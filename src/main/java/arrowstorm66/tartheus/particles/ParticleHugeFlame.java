package arrowstorm66.tartheus.particles;

import net.minecraft.client.particle.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.world.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class ParticleHugeFlame extends Particle {
	private float flameScale;

	public ParticleHugeFlame(final World world, final double x, final double y, final double z, final double vx,
			final double vy, final double vz) {
		super(world, x, y, z, vx, vy, vz);
		this.motionX = this.motionX * 0.009999999776482582 + vx;
		this.motionY = this.motionY * 0.009999999776482582 + vy;
		this.motionZ = this.motionZ * 0.009999999776482582 + vz;
		this.particleScale *= 3.0;
		this.flameScale = this.particleScale;
		final float particleRed = 1.0f;
		this.particleBlue = particleRed;
		this.particleGreen = particleRed;
		this.particleRed = particleRed;
		this.particleMaxAge = (int) (8.0 / (Math.random() * 0.8 + 0.2)) + 4;
		this.setParticleTextureIndex(48);
	}

	public void renderParticle(final BufferBuilder buffer, final Entity entity, final float partialTicks,
			final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY,
			final float rotationXZ) {
		final float var8 = (this.particleAge + partialTicks) / this.particleMaxAge;
		this.particleScale = this.flameScale * (1.0f - var8 * var8 * 0.5f);
		super.renderParticle(buffer, entity, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
	}

	public int getBrightnessForRender(final float partialTicks) {
		float var2 = (this.particleAge + partialTicks) / this.particleMaxAge;
		if (var2 < 0.0f) {
			var2 = 0.0f;
		}
		if (var2 > 1.0f) {
			var2 = 1.0f;
		}
		final int var3 = super.getBrightnessForRender(partialTicks);
		int var4 = var3 & 0xFF;
		final int var5 = var3 >> 16 & 0xFF;
		var4 += (int) (var2 * 15.0f * 16.0f);
		if (var4 > 240) {
			var4 = 240;
		}
		return var4 | var5 << 16;
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.particleAge++ >= this.particleMaxAge) {
			this.setExpired();
		}
		this.motionY += 0.004;
		this.move(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9599999785423279;
		this.motionY *= 0.9599999785423279;
		this.motionZ *= 0.9599999785423279;
		if (this.isExpired) {
			this.motionX *= 0.699999988079071;
			this.motionZ *= 0.699999988079071;
		}
	}
}