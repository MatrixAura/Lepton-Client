package cn.matrixaura.lepton.util.render.shader.impl;

import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.render.FramebufferWrapper;
import cn.matrixaura.lepton.util.math.MathUtils;
import cn.matrixaura.lepton.util.render.RenderUtils;
import cn.matrixaura.lepton.util.render.shader.ShaderUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import java.nio.Buffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glUniform1;

public class Blur {

    public static ShaderUtils blurShader = new ShaderUtils(ShaderUtils.Shaders.Blur);

    public static FramebufferWrapper framebufferIn = new FramebufferWrapper(FramebufferWrapper.newFramebuffer(1, 1, false));
    public static FramebufferWrapper framebufferOut = new FramebufferWrapper(FramebufferWrapper.newFramebuffer(1, 1, false));

    public static void setupUniforms(int texture, float dir1, float dir2, float radius) {
        blurShader.setUniformi("textureIn", texture);
        blurShader.setUniformf("texelSize", 1.0F / (float) MinecraftWrapper.get().getDisplayWidth(), 1.0F / (float) MinecraftWrapper.get().getDisplayHeight());
        blurShader.setUniformf("direction", dir1, dir2);
        blurShader.setUniformf("radius", radius);

        FloatBuffer weightBuffer = BufferUtils.createFloatBuffer(256);
        for (int i = 0; i <= radius; i++) {
            weightBuffer.put(MathUtils.gaussianValue(i, radius / 2));
        }

        // ----- WARNING WARNING WARNING WARNING WARNING ------ //
        ((Buffer) weightBuffer).flip(); // Don't change it
        // ----- WARNING WARNING WARNING WARNING WARNING ------ //
        glUniform1(blurShader.getUniform("weights"), weightBuffer);
    }

    public static void renderBlur(Runnable func, float radius) {
        RenderUtils.startStencil();
        func.run();
        RenderUtils.readStencil(1);

        GL11.glEnable(GL_BLEND);
        GL11.glColor4f(1, 1, 1, 1);
        GL14.glBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);

        framebufferOut = RenderUtils.createFramebuffer(framebufferOut);

        framebufferOut.framebufferClear();
        framebufferOut.bindFramebuffer(true);
        blurShader.init();
        setupUniforms(0, 1, 0, radius);

        GL11.glBindTexture(GL_TEXTURE_2D, MinecraftWrapper.get().getFramebuffer().getFramebufferTexture());

        ShaderUtils.drawQuads();
        framebufferOut.unbindFramebuffer();
        blurShader.unload();

        MinecraftWrapper.get().getFramebuffer().bindFramebuffer(true);
        blurShader.init();
        setupUniforms(0, 0, 1, radius);

        GL11.glBindTexture(GL_TEXTURE_2D, framebufferOut.getFramebufferTexture());
        ShaderUtils.drawQuads();
        blurShader.unload();

        GL11.glColor4f(1, 1, 1, 1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

        RenderUtils.stopStencil();
    }
}