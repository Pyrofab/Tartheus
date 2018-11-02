package arrowstorm66.tartheus.shader;

import arrowstorm66.tartheus.Tartheus;
import arrowstorm66.tartheus.world.TartheusWorldProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.resource.IResourceType;
import net.minecraftforge.client.resource.ISelectiveResourceReloadListener;
import net.minecraftforge.client.resource.VanillaResourceType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.function.Predicate;

/**
 * @author Pyrofab
 */
public class TartheusShaderManager implements ISelectiveResourceReloadListener {
    public static final ResourceLocation FANCY_NIGHT_SHADER = new ResourceLocation(Tartheus.MODID, "shaders/post/fancy_darkness.json");
    private ShaderGroup shader;
    private int oldDisplayWidth, oldDisplayHeight;

    // fancy shader stuff
    private Matrix4f projectionMatrix = new Matrix4f();
    private Matrix4f viewMatrix = new Matrix4f();
    private Frustum frustum = new Frustum();

    private boolean isPlayerEligible(@Nullable EntityPlayer player) {
        if (player != null) {
            World world = player.world;
            if (world.provider instanceof TartheusWorldProvider) {
                float celestialAngle = world.getCelestialAngle(1.0f);
                return celestialAngle > 0.23f && celestialAngle < 0.76f || player.posY < 88.0 && !player.world.canSeeSky(player.getPosition());
            }
        }
        return false;
    }

    /**
     * Applies the darkness shader after the world has been fully rendered
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (isPlayerEligible(mc.player)) {
            if (this.shader != null) {
                if (mc.displayWidth != oldDisplayWidth || mc.displayHeight != oldDisplayHeight) {
                    for (Shader shader : this.shader.listShaders) {
                        shader.getShaderManager().addSamplerTexture("DepthSampler", ShaderUtil.getDepthTexture());
                        shader.getShaderManager().getShaderUniformOrDefault("ViewPort").set(0, 0, mc.displayWidth, mc.displayHeight);
                    }
                    this.shader.createBindFramebuffers(mc.displayWidth, mc.displayHeight);
                    oldDisplayWidth = mc.displayWidth;
                    oldDisplayHeight = mc.displayHeight;
                }
                float celestialAngle = Minecraft.getMinecraft().world.getCelestialAngle(1.0f);
                float progress = 1;
                if (mc.world.canSeeSky(mc.player.getPosition())) {
                    if (celestialAngle < 0.30f) {
                        progress = 1 + (celestialAngle - 0.30f) / 0.07f;
                    } else if (celestialAngle > 0.69f) {
                        progress = (0.76f - celestialAngle) / 0.07f;
                    }
                } else if (mc.player.posY >= 73f) {
                    progress = 1 + (73f - (float) mc.player.posY) / 15f;
                }
                for (Shader shader : this.shader.listShaders) {
                    shader.getShaderManager().getShaderUniformOrDefault("Progress").set(progress);
                }
                setupFancyUniforms(mc);
                GlStateManager.matrixMode(GL11.GL_TEXTURE);
                GlStateManager.loadIdentity();
                shader.render(event.getPartialTicks());
                Minecraft.getMinecraft().getFramebuffer().bindFramebuffer(true);
                GlStateManager.disableBlend();
                GlStateManager.enableAlpha();
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); // restore blending
                GlStateManager.enableDepth();
            } else if (OpenGlHelper.areShadersSupported()) {
                ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(this);
            }
        }
    }

    private void setupFancyUniforms(Minecraft mc) {
        ShaderManager sm = this.shader.listShaders.get(0).getShaderManager();
        ShaderUtil.useShader(sm.getProgram());
        Entity camera = mc.getRenderViewEntity();
        frustum.setPosition(camera.posX, camera.posY, camera.posZ);
        ShaderUtil.setUniform("InverseTransformMatrix", computeInverseTransformMatrix());
        int lightCount = 0;
        // Add a visibility area around the player
        ShaderUtil.setUniform("Lights[" + lightCount + "].position", 0f, 0f, 0f);
        ShaderUtil.setUniform("Lights[" + lightCount + "].radius", 40f);
        lightCount++;
        ShaderUtil.setUniform("LightCount", lightCount);
        ShaderUtil.revert();
    }

    /**
     * @return the matrix allowing computation of eye space coordinates from window space
     */
    @Nonnull
    private FloatBuffer computeInverseTransformMatrix() {
        projectionMatrix.load(ShaderUtil.getProjectionMatrix());

        viewMatrix.load(ShaderUtil.getModelViewMatrix());

        Matrix4f projectionViewMatrix = Matrix4f.mul(projectionMatrix, viewMatrix, null);
        // reuse the projection matrix instead of creating a new one
        Matrix4f inverseTransformMatrix = Matrix4f.invert(projectionViewMatrix, projectionMatrix);

        FloatBuffer buf = ShaderUtil.getTempBuffer();
        inverseTransformMatrix.store(buf);
        buf.rewind();
        return buf;
    }

    @Override
    public void onResourceManagerReload(@Nonnull IResourceManager resourceManager, @Nonnull Predicate<IResourceType> resourcePredicate) {
        if (resourcePredicate.test(VanillaResourceType.SHADERS)) {
            Minecraft mc = Minecraft.getMinecraft();
            try {
                this.shader = new ShaderGroup(
                        mc.renderEngine,
                        resourceManager,
                        mc.getFramebuffer(),
                        FANCY_NIGHT_SHADER
                );
                this.oldDisplayWidth = -1; // reset framebuffers next tick
            } catch (IOException e) {
                Tartheus.LOGGER.error("Could not load darkness shader", e);
            }
        }
    }
}
