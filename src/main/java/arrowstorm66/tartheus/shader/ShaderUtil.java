package arrowstorm66.tartheus.shader;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;

/**
 * A class offering several utility methods to create, use and configure shaders
 *
 * <p>
 * <p>
 * This class was created by Pyrofab. It's distributed as
 * part of the Ladylib library. Get the Source Code on github:
 * https://github.com/Ladysnake/Ladylib
 *
 * Ladylib is Open Source and distributed under the
 * LGPL 3.0 license: https://github.com/Ladysnake/LadyLib/blob/master/LICENSE.md
 *
 */
@SideOnly(Side.CLIENT)
public final class ShaderUtil {
    private ShaderUtil() { }

    /**Used for opengl interactions such as matrix retrieval*/
    private static FloatBuffer buffer = BufferUtils.createFloatBuffer(16);

    /* Generic shaders variables */

    private static int prevProgram = 0;
    private static int currentProgram = 0;

    /**A map of programs to maps of uniform names to location*/
    private static final Int2ObjectMap<Object2IntMap<String>> uniformsCache = new Int2ObjectOpenHashMap<>();

    /* Screen shader variables */

    private static boolean shouldUseShaders() {
        return OpenGlHelper.shadersSupported;
    }


    /**
     * Sets the currently used program
     *
     * @param program the reference to the desired shader (0 to remove any current shader)
     */
    public static void useShader(int program) {
        if (!shouldUseShaders()) {
            return;
        }

        prevProgram = GlStateManager.glGetInteger(GL20.GL_CURRENT_PROGRAM);
        OpenGlHelper.glUseProgram(program);

        currentProgram = program;
    }

    /**
     * Sets the value of an int uniform from the current shader program
     *
     * @param uniformName the name of the uniform field in the shader source file
     * @param value       an int value for this uniform
     */
    public static void setUniform(String uniformName, int value) {
        if (!shouldUseShaders() || currentProgram == 0) {
            return;
        }

        int uniform = getUniform(uniformName);
        if (uniform != -1) {
            GL20.glUniform1i(uniform, value);
        }
    }

    /**
     * Sets the value of an uniform from the current shader program
     * If exactly 1 value is supplied, will set the value of a float uniform field
     * If between 2 and 4 values are supplied, will set the value of a vec uniform of corresponding length
     *
     * @param uniformName the name of the uniform field in the shader source file
     * @param values      between 1 and 4 float values
     */
    public static void setUniform(String uniformName, float... values) {
        if (!shouldUseShaders()) {
            return;
        }

        int uniform = getUniform(uniformName);
        if (uniform != -1) {
            switch (values.length) {
                case 1:
                    GL20.glUniform1f(uniform, values[0]);
                    break;
                case 2:
                    GL20.glUniform2f(uniform, values[0], values[1]);
                    break;
                case 3:
                    GL20.glUniform3f(uniform, values[0], values[1], values[2]);
                    break;
                case 4:
                    GL20.glUniform4f(uniform, values[0], values[1], values[2], values[3]);
                    break;
                default:
                    throw new IllegalArgumentException("Shader float uniforms only support between 1 and 4 values");
            }
        }
    }

    /**
     * Sets the value of a mat4 uniform in the current shader
     *
     * @param uniformName the name of the uniform field in the shader source file
     * @param mat4        a raw array of float values
     */
    public static void setUniform(String uniformName, FloatBuffer mat4) {
        if (!shouldUseShaders()) {
            return;
        }

        int uniform = getUniform(uniformName);
        if (uniform != -1) {
            GL20.glUniformMatrix4(uniform, false, mat4);
        }
    }

    private static int getUniform(String uniformName) {
        // Gets the uniform cache for the current program
        Object2IntMap<String> shaderUniformsCache = uniformsCache.get(currentProgram);
        // Computee if absent
        if (shaderUniformsCache == null) {
            shaderUniformsCache = new Object2IntOpenHashMap<>();
            uniformsCache.put(currentProgram, shaderUniformsCache);
        }
        // Gets the uniform location from the cache
        int uniform;
        if (shaderUniformsCache.containsKey(uniformName)) {
            uniform = shaderUniformsCache.get(uniformName);
        } else {
            // Compute if absent
            uniform = GL20.glGetUniformLocation(currentProgram, uniformName);
            shaderUniformsCache.put(uniformName, uniform);
        }
        return uniform;
    }

    /**
     *
     * @return the gl name of the main framebuffer's depth texture
     */
    public static int getDepthTexture() {
        return FramebufferReplacement.getMainDepthTexture();
    }

    /**
     * A 16-sized float buffer that can be used to send data to shaders.
     * Do not use this buffer for long term data storage, it can be cleared at any time.
     */
    public static FloatBuffer getTempBuffer() {
        buffer.clear();
        return buffer;
    }

    public static FloatBuffer getProjectionMatrix() {
        FloatBuffer projection = getTempBuffer();
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, projection);
        projection.rewind();
        return projection;
    }

    /**
     * Call this once before doing any transform to get the <em>view</em> matrix, then load identity and call this again after
     * doing any rendering transform to get the <em>model</em> matrix for the rendered object
     */
    public static FloatBuffer getModelViewMatrix() {
        FloatBuffer modelView = getTempBuffer();
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, modelView);
        modelView.rewind();
        return modelView;
    }

    /**
     * Reverts to the previous shader used
     */
    public static void revert() {
        useShader(prevProgram);
    }

}