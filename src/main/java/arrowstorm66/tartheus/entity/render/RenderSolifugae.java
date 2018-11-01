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
import arrowstorm66.tartheus.entity.EntitySolifugae;
import arrowstorm66.tartheus.entity.EntityVinegaroon;
import arrowstorm66.tartheus.entity.model.ModelScorpion;
import arrowstorm66.tartheus.entity.model.ModelSolifugae;
import arrowstorm66.tartheus.entity.model.ModelVinegaroon;
import arrowstorm66.tartheus.entity.render.layer.LayerBlood;
import arrowstorm66.tartheus.entity.render.layer.LayerEyes;

public class RenderSolifugae extends RenderLiving<EntitySolifugae> {

	private ResourceLocation TEXTURE = new ResourceLocation("tartheus:textures/entity/solifugae.png");

	public static final Factory FACTORY = new Factory();

	public RenderSolifugae(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelSolifugae(), 1.0F);
	}

	@Override
	@Nonnull
	public ResourceLocation getEntityTexture(@Nonnull EntitySolifugae entity) {
		return TEXTURE;
	}

	//Solifugae is 5.2 cm
	@Override
	public void doRender(EntitySolifugae entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	protected void preRenderCallback(EntitySolifugae entitylivingbaseIn, float partialTickTime) {
		if (entitylivingbaseIn.isBesideClimbableBlock() && !entitylivingbaseIn.IsJumpingUp()) {
			GL11.glRotatef(90.0f, -1.0f, 0.0f, 0.0f);
			GlStateManager.translate(0, 0.65, 0);
		}
		GlStateManager.scale(0.8, 0.8, 0.8);
		GlStateManager.translate(0, -0.15, 0);
	}
	
    protected float getDeathMaxRotation(EntitySolifugae entityLivingBaseIn)
    {
        return 180.0F;
    }

	public static class Factory implements IRenderFactory<EntitySolifugae> {

		@Override
		public Render<? super EntitySolifugae> createRenderFor(RenderManager manager) {
			return new RenderSolifugae(manager);
		}

	}

}