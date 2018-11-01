package arrowstorm66.tartheus.world;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Iterator;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import arrowstorm66.tartheus.Tartheus;

public class TartheusSkyRenderer extends IRenderHandler {

	private int starGLCallList;
	private int glSkyList;
	private int glSkyList2;
	private net.minecraft.client.renderer.vertex.VertexBuffer starVBO;
	private net.minecraft.client.renderer.vertex.VertexBuffer skyVBO;
	private net.minecraft.client.renderer.vertex.VertexBuffer sky2VBO;

	public TartheusSkyRenderer() {
		RenderGlobal renderGlobal = Minecraft.getMinecraft().renderGlobal;
		this.glSkyList2 = (this.glSkyList = (this.starGLCallList = ObfuscationReflectionHelper
				.getPrivateValue(RenderGlobal.class, renderGlobal, "starGLCallList", "field_72772_v")) + 1) + 1;
		this.generateStars();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		final int pass = EntityRenderer.anaglyphField;
		final RenderGlobal rg = mc.renderGlobal;
		GlStateManager.disableTexture2D();
		Vec3d vec3d = world.getSkyColor(mc.getRenderViewEntity(), partialTicks);
		float f = (float) vec3d.x;
		float f1 = (float) vec3d.y;
		float f2 = (float) vec3d.z;

		if (pass != 2) {
			float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
			float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
			float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
			f = f3;
			f1 = f4;
			f2 = f5;
		}

		GlStateManager.color(f, f1, f2);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		GlStateManager.depthMask(false);
		GlStateManager.enableFog();
		GlStateManager.color(f, f1, f2);
		GlStateManager.disableFog();
		GlStateManager.disableAlpha();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		RenderHelper.disableStandardItemLighting();
		float[] afloat = world.provider.calcSunriseSunsetColors(world.getCelestialAngle(partialTicks), partialTicks);

		if (afloat != null) {
			GlStateManager.disableTexture2D();
			GlStateManager.shadeModel(7425);
			GlStateManager.pushMatrix();
			GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F,
					0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
			float f6 = afloat[0];
			float f7 = afloat[1];
			float f8 = afloat[2];

			if (pass != 2) {
				float f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
				float f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
				float f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
				f6 = f9;
				f7 = f10;
				f8 = f11;
			}

			vertexbuffer.begin(6, DefaultVertexFormats.POSITION_COLOR);
			vertexbuffer.pos(0.0D, 100.0D, 0.0D).color(f6, f7, f8, afloat[3]).endVertex();
			int j = 16;

			for (int l = 0; l <= 16; ++l) {
				float f21 = (float) l * ((float) Math.PI * 2F) / 16.0F;
				float f12 = MathHelper.sin(f21);
				float f13 = MathHelper.cos(f21);
				vertexbuffer.pos((double) (f12 * 120.0F), (double) (f13 * 120.0F), (double) (-f13 * 40.0F * afloat[3]))
						.color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
			}

			tessellator.draw();
			GlStateManager.popMatrix();
			GlStateManager.shadeModel(7424);
		}

		GlStateManager.enableTexture2D();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE,
				GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.pushMatrix();
		float f16 = 1.0F - world.getRainStrength(partialTicks);
		GlStateManager.color(1.0F, 1.0F, 1.0F, f16);
		GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
		float f17 = 30.0F;
		mc.renderEngine.bindTexture(new ResourceLocation(Tartheus.MODID + ":textures/enviroment/apathorion_sun.png"));
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos((double) (-f17), 100.0D, (double) (-f17)).tex(0.0D, 0.0D).endVertex();
		vertexbuffer.pos((double) f17, 100.0D, (double) (-f17)).tex(1.0D, 0.0D).endVertex();
		vertexbuffer.pos((double) f17, 100.0D, (double) f17).tex(1.0D, 1.0D).endVertex();
		vertexbuffer.pos((double) (-f17), 100.0D, (double) f17).tex(0.0D, 1.0D).endVertex();
		tessellator.draw();
		f17 = 20.0F;
		mc.renderEngine.bindTexture(new ResourceLocation(Tartheus.MODID + ":textures/enviroment/apathorion_moon.png"));
		int i = world.getMoonPhase();
		int k = i % 4;
		int i1 = i / 4 % 2;
		float f22 = (float) (k + 0) / 4.0F;
		float f23 = (float) (i1 + 0) / 2.0F;
		float f24 = (float) (k + 1) / 4.0F;
		float f14 = (float) (i1 + 1) / 2.0F;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos((double) (-f17), -100.0D, (double) f17).tex((double) f24, (double) f14).endVertex();
		vertexbuffer.pos((double) f17, -100.0D, (double) f17).tex((double) f22, (double) f14).endVertex();
		vertexbuffer.pos((double) f17, -100.0D, (double) (-f17)).tex((double) f22, (double) f23).endVertex();
		vertexbuffer.pos((double) (-f17), -100.0D, (double) (-f17)).tex((double) f24, (double) f23).endVertex();
		tessellator.draw();
		GlStateManager.disableTexture2D();
		float f15 = world.getStarBrightness(partialTicks) * f16;

		if (f15 > 0.0F) {
			GlStateManager.color(f15, f15, f15, f15);
			GlStateManager.callList(this.starGLCallList);
		}

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableFog();
		GlStateManager.popMatrix();
		GlStateManager.disableTexture2D();
		GlStateManager.color(0.0F, 0.0F, 0.0F);
		double d0 = mc.player.getPositionEyes(partialTicks).y - world.getHorizon();

		if (d0 < 0.0D) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, 12.0F, 0.0F);
			GlStateManager.callList(this.glSkyList2);
			GlStateManager.popMatrix();
			float f18 = 1.0F;
			float f19 = -((float) (d0 + 65.0D));
			float f20 = -1.0F;
			vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
			vertexbuffer.pos(-1.0D, (double) f19, 1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(1.0D, (double) f19, 1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(1.0D, (double) f19, -1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(-1.0D, (double) f19, -1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(1.0D, (double) f19, 1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(1.0D, (double) f19, -1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(-1.0D, (double) f19, -1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(-1.0D, (double) f19, 1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
			vertexbuffer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
			tessellator.draw();
		}

		if (world.provider.isSkyColored()) {
			GlStateManager.color(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
		} else {
			GlStateManager.color(f, f1, f2);
		}

		GlStateManager.pushMatrix();
		GlStateManager.translate(0.0F, -((float) (d0 - 16.0D)), 0.0F);
		GlStateManager.callList(this.glSkyList2);
		GlStateManager.popMatrix();
		GlStateManager.enableTexture2D();
		GlStateManager.depthMask(true);
	}

	private void renderStars(final BufferBuilder worldRendererIn) {
		final Random random = new Random(10842L);
		worldRendererIn.begin(7, DefaultVertexFormats.POSITION);
		for (int i = 0; i < 2500; ++i) {
			double d0 = random.nextFloat() * 2.0f - 1.0f;
			double d2 = random.nextFloat() * 2.0f - 1.0f;
			double d3 = random.nextFloat() * 2.0f - 1.0f;
			final double d4 = 0.15f + random.nextFloat() * 0.1f;
			double d5 = d0 * d0 + d2 * d2 + d3 * d3;
			if (d5 < 1.0 && d5 > 0.01) {
				d5 = 1.0 / Math.sqrt(d5);
				d0 *= d5;
				d2 *= d5;
				d3 *= d5;
				final double d6 = d0 * 125.0;
				final double d7 = d2 * 125.0;
				final double d8 = d3 * 125.0;
				final double d9 = Math.atan2(d0, d3);
				final double d10 = Math.sin(d9);
				final double d11 = Math.cos(d9);
				final double d12 = Math.atan2(Math.sqrt(d0 * d0 + d3 * d3), d2);
				final double d13 = Math.sin(d12);
				final double d14 = Math.cos(d12);
				final double d15 = random.nextDouble() * 3.141592653589793 * 2.0;
				final double d16 = Math.sin(d15);
				final double d17 = Math.cos(d15);
				for (int j = 0; j < 4; ++j) {
					final double d18 = 0.0;
					final double d19 = ((j & 0x2) - 1) * d4;
					final double d20 = ((j + 1 & 0x2) - 1) * d4;
					final double d21 = 0.0;
					final double d22 = d19 * d17 - d20 * d16;
					final double d23 = d20 * d17 + d19 * d16;
					final double d24 = d22 * d13 + 0.0 * d14;
					final double d25 = 0.0 * d13 - d22 * d14;
					final double d26 = d25 * d10 - d23 * d11;
					final double d27 = d23 * d10 + d25 * d11;
					worldRendererIn.pos(d6 + d26, d7 + d24, d8 + d27).endVertex();
				}
			}
		}
	}

	private void generateStars() {
		final VertexFormat vertexBufferFormat = new VertexFormat();
		vertexBufferFormat.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT,
				VertexFormatElement.EnumUsage.POSITION, 3));
		final Tessellator tessellator = Tessellator.getInstance();
		final BufferBuilder vertexbuffer = tessellator.getBuffer();
		if (this.starGLCallList >= 0) {
			GLAllocation.deleteDisplayLists(this.starGLCallList);
			this.starGLCallList = -1;
		}
		this.starGLCallList = GLAllocation.generateDisplayLists(1);
		GlStateManager.pushMatrix();
		GlStateManager.glNewList(this.starGLCallList, 4864);
		this.renderStars(vertexbuffer);
		tessellator.draw();
		GlStateManager.glEndList();
		GlStateManager.popMatrix();
	}
}