package arrowstorm66.tartheus.entity.render.layer;

import org.lwjgl.opengl.GL11;

import arrowstorm66.tartheus.entity.EntityScorpion;
import arrowstorm66.tartheus.entity.model.ModelScorpion;
import arrowstorm66.tartheus.entity.render.RenderScorpion;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerBlood implements LayerRenderer<EntityScorpion> {
	private ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(
			"tartheus:textures/entity/undead_blood.png");

	public final RenderScorpion renderer;

	public LayerBlood(RenderScorpion renderer) {
		this.renderer = renderer;
	}

	@Override
	public void doRenderLayer(EntityScorpion entity, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.depthMask(!entity.isInvisible());

		float scrollTimer = entity.ticksExisted + partialTicks;
		this.renderer.bindTexture(OVERLAY_TEXTURE);
		GlStateManager.matrixMode(GL11.GL_TEXTURE);
		GlStateManager.loadIdentity();
		float yScroll = scrollTimer * 0.01F;
		GlStateManager.translate(0F, -yScroll, 0.0F);
		GlStateManager.matrixMode(GL11.GL_MODELVIEW);
		float colour = 0.5F;
		GlStateManager.color(colour, colour, colour, 1.0F);

		ModelScorpion mainModel = (ModelScorpion) this.renderer.getMainModel();
		mainModel.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
		mainModel.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		mainModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		
		GlStateManager.matrixMode(GL11.GL_TEXTURE);
		GlStateManager.loadIdentity();
		GlStateManager.matrixMode(GL11.GL_MODELVIEW);
		this.renderer.bindTexture(this.renderer.getEntityTexture(entity));
		GlStateManager.depthMask(true);
		GlStateManager.disableBlend();
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}