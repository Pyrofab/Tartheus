package arrowstorm66.tartheus.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ScreenEffects {

	public static int showTicks;
	public static String image;

	@SubscribeEvent
	public void onRenderGameOverlay(final RenderGameOverlayEvent.Post event) {
		if (ScreenEffects.showTicks > 0) {
			ScreenEffects.showTicks -= 1;
		}
		if (event.isCanceled() || event.getType() != RenderGameOverlayEvent.ElementType.HELMET) {
			return;
		}
		if (ScreenEffects.showTicks > 0) {
			drawEffects(event);
		}
	}

	public static void drawEffects(final RenderGameOverlayEvent.Post event) {
		final double height = event.getResolution().getScaledHeight_double();
		final double width = event.getResolution().getScaledWidth_double();
		float fadein = (0.035F * showTicks);
		float fadeout = 1.7F + (0.005F - showTicks) / 115F;
		final int imageSize = 256;
		double scale = Math.max(height / imageSize, width / imageSize);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glDisable(2929);
		GL11.glDisable(3008);
		GlStateManager.scale(scale, scale, 0.0);
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(ScreenEffects.image));
		GuiUtils.drawTexturedModalRect((int) (width / 2.0 / scale - imageSize / 2),
				(int) (height / 2.0 / scale - imageSize / 2), 0, 0, imageSize, imageSize, -5.0f);
		GlStateManager.disableBlend();
		GL11.glDepthMask(true);
		GL11.glEnable(2929);
		GL11.glEnable(3008);
		GlStateManager.popMatrix();
	}
}