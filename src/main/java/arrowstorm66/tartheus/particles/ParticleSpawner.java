package arrowstorm66.tartheus.particles;

import net.minecraft.client.*;
import net.minecraft.client.particle.*;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;

public class ParticleSpawner {
	@SideOnly(Side.CLIENT)
	public static void spawnBloodGush(final World world, final double x, final double y, final double z,
			final double power, final int amount, final int r, final int g, final int b) {
		for (int i = 0; i < amount; ++i) {
			final ParticleBloodGushNormal blood = new ParticleBloodGushNormal(world, x, y, z, power, r, g, b);
			Minecraft.getMinecraft().effectRenderer.addEffect((Particle) blood);
		}
	}

	@SideOnly(Side.CLIENT)
	public static void spawnSpiderBloodGush(final World world, final double x, final double y, final double z,
			final double power, final int amount, final int r, final int g, final int b) {
		for (int i = 0; i < amount; ++i) {
			final ParticleBloodGushSpider blood = new ParticleBloodGushSpider(world, x, y, z, power, r, g, b);
			Minecraft.getMinecraft().effectRenderer.addEffect((Particle) blood);
		}
	}

	@SideOnly(Side.CLIENT)
	public static void spawnEnderBloodGush(final World world, final double x, final double y, final double z,
			final double power, final int amount, final int r, final int g, final int b) {
		for (int i = 0; i < amount; ++i) {
			final ParticleBloodGushEnder blood = new ParticleBloodGushEnder(world, x, y, z, power, r, g, b);
			Minecraft.getMinecraft().effectRenderer.addEffect((Particle) blood);
		}
	}

	@SideOnly(Side.CLIENT)
	public static void spawnGunpowderGush(final World world, final double x, final double y, final double z,
			final double power, final int amount, final int r, final int g, final int b) {
		for (int i = 0; i < amount; ++i) {
			final ParticleBloodGushGunpowder blood = new ParticleBloodGushGunpowder(world, x, y, z, power, r, g, b);
			Minecraft.getMinecraft().effectRenderer.addEffect((Particle) blood);
		}
	}

	@SideOnly(Side.CLIENT)
	public static void spawnSnowGush(final World world, final double x, final double y, final double z,
			final double power, final int amount, final int r, final int g, final int b) {
		for (int i = 0; i < amount; ++i) {
			final ParticleSnowGush blood = new ParticleSnowGush(world, x, y, z, power, r, g, b);
			Minecraft.getMinecraft().effectRenderer.addEffect((Particle) blood);
		}
	}

	@SideOnly(Side.CLIENT)
	public static void spawnPartSkeleton(final World world, final double x, final double y, final double z,
			final double power, final int amount, final int r, final int g, final int b) {
		for (int i = 0; i < amount; ++i) {
			final ParticlePartSkeleton blood = new ParticlePartSkeleton(world, x, y, z, power, r, g, b);
			Minecraft.getMinecraft().effectRenderer.addEffect((Particle) blood);
		}
	}

	@SideOnly(Side.CLIENT)
	public static void spawnParticlesSpark(final World world, final double x, final double y, final double z,
			final int red, final int green, final int blue, final float scale, final int maxAge) {
		final ParticleSpark particle = new ParticleSpark(world, x, y, z, red, green, blue, scale, maxAge);
		Minecraft.getMinecraft().effectRenderer.addEffect((net.minecraft.client.particle.Particle) particle);
	}

	@SideOnly(Side.CLIENT)
	public static void spawnBulletHoleParticle(final World world, final RayTraceResult origin) {
		final Particle entity = new ParticleBulletHole(world, origin);
		Minecraft.getMinecraft().effectRenderer.addEffect((net.minecraft.client.particle.Particle) entity);
	}

	@SideOnly(Side.CLIENT)
	public static void spawnFireballImpactParticle(final World world, final RayTraceResult origin, float red,
			float green, float blue) {
		final Particle entity = new ParticleFireballImpact(world, origin, red, green, blue);
		Minecraft.getMinecraft().effectRenderer.addEffect((net.minecraft.client.particle.Particle) entity);
	}

	@SideOnly(Side.CLIENT)
	public static void spawnHugeFlame(final World world, final double x, final double y, final double z, final double vx,
			final double vy, final double vz) {
		final ParticleHugeFlame entity = new ParticleHugeFlame(world, x, y, z, vx, vy, vz);
		Minecraft.getMinecraft().effectRenderer.addEffect((Particle) entity);
	}
}