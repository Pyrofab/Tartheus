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
import arrowstorm66.tartheus.entity.model.ModelVinegaroon;
import arrowstorm66.tartheus.entity.render.layer.LayerBlood;
import arrowstorm66.tartheus.entity.render.layer.LayerEyes;

public class RenderVinegaroon extends RenderLiving<EntityVinegaroon> {

	private ResourceLocation TEXTURE = new ResourceLocation("tartheus:textures/entity/vinegaroon.png");
	private ResourceLocation EYES_TEXTURE = new ResourceLocation("tartheus:textures/entity/vinegaroon_eyes.png");

	public static final Factory FACTORY = new Factory();

	public RenderVinegaroon(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelVinegaroon(), 1.2F);
	}

	@Override
	@Nonnull
	public ResourceLocation getEntityTexture(@Nonnull EntityVinegaroon entity) {
		return TEXTURE;
	}

	//Vinegaroon is 3 cm
	@Override
	public void doRender(EntityVinegaroon entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	protected void preRenderCallback(EntityVinegaroon entitylivingbaseIn, float partialTickTime) {
		if (entitylivingbaseIn.isBesideClimbableBlock() && !entitylivingbaseIn.IsJumpingUp()) {
			GL11.glRotatef(90.0f, -1.0f, 0.0f, 0.0f);
			GlStateManager.translate(0, 0.95, 0);
		}
		GlStateManager.scale(1.1, 1.1, 1.1);
		GlStateManager.translate(0, -0.05, 0);
	}
	
    protected float getDeathMaxRotation(EntityVinegaroon entityLivingBaseIn)
    {
        return 180.0F;
    }

	public static class Factory implements IRenderFactory<EntityVinegaroon> {

		@Override
		public Render<? super EntityVinegaroon> createRenderFor(RenderManager manager) {
			return new RenderVinegaroon(manager);
		}

	}

}