package arrowstorm66.tartheus.entity.render;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import java.util.Random;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import arrowstorm66.tartheus.entity.EntityLurker;
import arrowstorm66.tartheus.entity.EntityScorpion;
import arrowstorm66.tartheus.entity.EntityVinegaroon;
import arrowstorm66.tartheus.entity.model.ModelLurker;
import arrowstorm66.tartheus.entity.render.layer.LayerBlood;
import arrowstorm66.tartheus.entity.render.layer.LayerEyes;

public class RenderLurker extends RenderLiving<EntityLurker> {

	private ResourceLocation TEXTURE = new ResourceLocation("tartheus:textures/entity/lurker.png");
	private ResourceLocation EYES_TEXTURE = new ResourceLocation("tartheus:textures/entity/lurker_eyes.png");

	public static final Factory FACTORY = new Factory();
	private final Random rnd = new Random();

	public RenderLurker(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelLurker(), 0.0F);
		this.addLayer(new LayerEyes(this, EYES_TEXTURE));
	}

	@Override
	@Nonnull
	public ResourceLocation getEntityTexture(@Nonnull EntityLurker entity) {
		return TEXTURE;
	}

	public void doRender(EntityLurker entity, double x, double y, double z, float entityYaw, float partialTicks) {
		// Movement
		if (entity.isHostile()) {
			double d0 = 0.035D;
			x += this.rnd.nextGaussian() * d0;
			z += this.rnd.nextGaussian() * d0;
		}
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	public static class Factory implements IRenderFactory<EntityLurker> {

		@Override
		public Render<? super EntityLurker> createRenderFor(RenderManager manager) {
			return new RenderLurker(manager);
		}

	}

}