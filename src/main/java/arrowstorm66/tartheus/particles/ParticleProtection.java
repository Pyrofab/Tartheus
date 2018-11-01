package arrowstorm66.tartheus.particles;

import net.minecraft.client.particle.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.*;
import arrowstorm66.tartheus.Tartheus;
import net.minecraft.client.*;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ParticleProtection extends ParticleSimpleAnimated
{
	private final ResourceLocation danger = new ResourceLocation(Tartheus.MODID, "particle/protection");
    
    public ParticleProtection(final World world, final double x, final double y, final double z, final int maxAge) {
        super(world, x, y, z, 0, 0, 0.0f);
        this.particleGravity = 0.0f;
        this.particleMaxAge = maxAge;
        this.particleScale = 4;
        this.particleAlpha = 1f;
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(danger.toString());
		setParticleTexture(sprite);
    }
    
    public int getFXLayer() {
        return 1;
    }
    
	@Override
	public int getBrightnessForRender(float partialTick) {
		final int FULL_BRIGHTNESS_VALUE = 0xf000f0;
		return FULL_BRIGHTNESS_VALUE;
	}
	
	@Override
	public boolean shouldDisableDepth() {
		return false;
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setExpired();
		}

		this.motionY += 0.004D;
		this.move(this.motionX, this.motionY, this.motionZ);

		if (this.posY == this.prevPosY) {
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= 0.9599999785423279D;
		this.motionY *= 0.9599999785423279D;
		this.motionZ *= 0.9599999785423279D;

		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}
}