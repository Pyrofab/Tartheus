package arrowstorm66.tartheus.particles;

import net.minecraft.client.particle.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;

public class ParticleBulletHole extends Particle {
	RayTraceResult block;

	public ParticleBulletHole(final World worldIn, final RayTraceResult result) {
		super(worldIn, result.hitVec.x + result.sideHit.getFrontOffsetX() * 0.012,
				result.hitVec.y + result.sideHit.getFrontOffsetY() * 0.012,
				result.hitVec.z + result.sideHit.getFrontOffsetZ() * 0.012);
		this.block = result;
		this.particleRed = 0.05f;
		this.particleGreen = 0.05f;
		this.particleBlue = 0.05f;
		this.particleAlpha = 0.5f;
		this.particleMaxAge = 280;
		this.particleScale *= 0.9f;
		this.setSize(0.1f, 0.1f);
		this.setParticleTextureIndex(0);
	}

	public void onUpdate() {
		this.motionX = 0.0;
		this.motionY = 0.0;
		this.motionZ = 0.0;
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.world.getBlockState(this.block.getBlockPos()).getBlock().isAir(
				this.world.getBlockState(this.block.getBlockPos()), (IBlockAccess) this.world,
				this.block.getBlockPos())) {
			this.setExpired();
		}
		if (this.particleMaxAge-- <= 0) {
			this.setExpired();
		}
	}

	public void renderParticle(final BufferBuilder buffer, final Entity entityIn, final float partialTicks,
			final float rotationX, final float rotationZ, final float rotationYZ, final float rotationXY,
			final float rotationXZ) {
		final EnumFacing face = this.block.sideHit;
		if (face == EnumFacing.UP) {
			super.renderParticle(buffer, entityIn, partialTicks, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f);
		} else if (face == EnumFacing.DOWN) {
			super.renderParticle(buffer, entityIn, partialTicks, 1.0f, 0.0f, 0.0f, 0.0f, -1.0f);
		} else if (face == EnumFacing.NORTH) {
			super.renderParticle(buffer, entityIn, partialTicks, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
		} else if (face == EnumFacing.SOUTH) {
			super.renderParticle(buffer, entityIn, partialTicks, -1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
		} else if (face == EnumFacing.EAST) {
			super.renderParticle(buffer, entityIn, partialTicks, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f);
		} else if (face == EnumFacing.WEST) {
			super.renderParticle(buffer, entityIn, partialTicks, 0.0f, 1.0f, -1.0f, 0.0f, 0.0f);
		}
	}
}
