package arrowstorm66.tartheus.particles;

import net.minecraft.client.particle.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import arrowstorm66.tartheus.Tartheus;
import net.minecraft.client.*;
import net.minecraft.client.renderer.texture.*;

@SideOnly(Side.CLIENT)
public class ParticleSpark extends ParticleSimpleAnimated
{
    public static final ResourceLocation[] TEXTURES;
    private float originalScale;
    
    public ParticleSpark(final World world, final double x, final double y, final double z, final int red, final int green, final int blue, final float scale, final int maxAge) {
        super(world, x, y, z, 0, 0, 0.0f);
        this.particleGravity = 0.0f;
        this.particleMaxAge = maxAge;
        this.particleScale = scale;
        this.originalScale = scale;
        this.particleAlpha = 0.5f;
        this.setRBGColorF(red, green, blue);
        final ResourceLocation texture = new ResourceLocation(Tartheus.MODID, "particle/spark_" + world.rand.nextInt(4));
        final TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture.toString());
        this.setParticleTexture(sprite);
    }
    
    public void onUpdate() {
        super.onUpdate();
        this.particleAlpha = Math.max((this.particleMaxAge - this.particleAge) / this.particleMaxAge - 0.1f, 0.0f);
        this.particleScale -= this.originalScale / 30.0f;
    }
    
    public int getFXLayer() {
        return 1;
    }
    
    public void setParticleTextureIndex(final int particleTextureIndex) {
    }
    
    static {
        TEXTURES = new ResourceLocation[] { new ResourceLocation(Tartheus.MODID, "particle/spark_0"), new ResourceLocation(Tartheus.MODID, "particle/spark_1"), new ResourceLocation(Tartheus.MODID, "particle/spark_2"), new ResourceLocation(Tartheus.MODID, "particle/spark_3") };
    }
}