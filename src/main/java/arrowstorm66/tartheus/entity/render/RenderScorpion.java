package arrowstorm66.tartheus.entity.render;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import arrowstorm66.tartheus.entity.EntityScorpion;
import arrowstorm66.tartheus.entity.EntityVinegaroon;
import arrowstorm66.tartheus.entity.model.ModelScorpion;
import arrowstorm66.tartheus.entity.render.layer.LayerBlood;
import arrowstorm66.tartheus.entity.render.layer.LayerEyes;

public class RenderScorpion extends RenderLiving<EntityScorpion> {

	private ResourceLocation TEXTURE = new ResourceLocation("tartheus:textures/entity/scorpion.png");
	private ResourceLocation EYES_TEXTURE = new ResourceLocation("tartheus:textures/entity/scorpion_eyes.png");

	public static final Factory FACTORY = new Factory();

	public RenderScorpion(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelScorpion(), 1.05F);
	}

	@Override
	@Nonnull
	public ResourceLocation getEntityTexture(@Nonnull EntityScorpion entity) {
		return TEXTURE;
	}

	// Scorpion is 6.3 cm
	@Override
	public void doRender(EntityScorpion entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	protected void preRenderCallback(EntityScorpion entitylivingbaseIn, float partialTickTime) {
		if (entitylivingbaseIn.isBesideClimbableBlock() && !entitylivingbaseIn.IsJumpingUp()) {
			GL11.glRotatef(90.0f, -1.0f, 0.0f, 0.0f);
			GlStateManager.translate(0, 0.7, 0);
		}
		GlStateManager.translate(0, -0.05, 0);
	}
	
    protected float getDeathMaxRotation(EntityScorpion entityLivingBaseIn)
    {
        return 180.0F;
    }

	public static class Factory implements IRenderFactory<EntityScorpion> {

		@Override
		public Render<? super EntityScorpion> createRenderFor(RenderManager manager) {
			return new RenderScorpion(manager);
		}

	}

}