package cn.matrixaura.lepton.util.render.shader.impl;

import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import cn.matrixaura.lepton.util.math.MathUtils;
import cn.matrixaura.lepton.util.render.shader.ShaderUtils;
import cn.matrixaura.lepton.util.render.shader.ShaderUtils.Shaders;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;

public class Shadow {

    private static final ShaderUtils shadowShader = new ShaderUtils(Shaders.Bloom);
    private static Object framebuffer;

    static {

        try {
            framebuffer = ReflectionUtils.newInstance(Class.forName(Mappings.getObfClass("net/minecraft/client/shader/Framebuffer")), new Class[]{int.class, int.class, boolean.class}, 1, 1, false);
        } catch (ClassNotFoundException ignored) {
        }

    }

    public static void render(int sourceTexture, int radius, int offset) {
        if ((Integer)ReflectionUtils.getFieldValue(framebuffer, Mappings.getObfField("field_147621_c")) != MinecraftWrapper.get().getDisplayWidth() || (Integer)ReflectionUtils.getFieldValue(framebuffer, Mappings.getObfField("field_147618_d")) != MinecraftWrapper.get().getDisplayHeight()) {
            initFramebuffers();
        }

        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0);
        GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        FloatBuffer buffer = BufferUtils.createFloatBuffer(256);

        int framebufferTexture;
        for(framebufferTexture = 0; framebufferTexture <= radius; ++framebufferTexture) {
            buffer.put(MathUtils.gaussianValue((float)framebufferTexture, (float)radius));
        }

        ((Buffer) buffer).flip();
        ReflectionUtils.invokeMethod(framebuffer, Mappings.getObfMethod("func_147614_f"));
        ReflectionUtils.invokeMethod(framebuffer, Mappings.getObfMethod("func_147610_a"), new Class[]{boolean.class}, true);
        shadowShader.init();
        setupUniforms(radius, offset, 0, buffer);
        GL11.glBindTexture(3553, sourceTexture);
        ShaderUtils.drawQuads();
        shadowShader.unload();
        ReflectionUtils.invokeMethod(framebuffer, Mappings.getObfMethod("func_147609_e"));
        MinecraftWrapper.get().getFramebuffer().bindFramebuffer(true);
        shadowShader.init();
        setupUniforms(radius, 0, offset, buffer);
        framebufferTexture = (Integer)ReflectionUtils.getFieldValue(framebuffer, Mappings.getObfField("field_147617_g"));
        GL13.glActiveTexture(34000);
        GL11.glBindTexture(3553, sourceTexture);
        GL13.glActiveTexture(33984);
        GL11.glBindTexture(3553, framebufferTexture);
        ShaderUtils.drawQuads();
        shadowShader.unload();
        GL11.glAlphaFunc(516, 0.1F);
        GL11.glBindTexture(3553, 0);
    }

    private static void initFramebuffers() {
        ReflectionUtils.invokeMethod(framebuffer, Mappings.getObfMethod("func_147608_a"));
        framebuffer = ReflectionUtils.newInstance(framebuffer.getClass(), new Class[]{int.class, int.class, boolean.class}, MinecraftWrapper.get().getDisplayWidth(), MinecraftWrapper.get().getDisplayHeight(), true);
    }

    public static void setupUniforms(int radius, int directionX, int directionY, FloatBuffer buffer) {
        shadowShader.setUniformi("u_diffuse_sampler", 0);
        shadowShader.setUniformi("u_other_sampler", 16);
        shadowShader.setUniformf("u_radius", radius);
        shadowShader.setUniformf("u_texel_size", 1.0F / MinecraftWrapper.get().getDisplayWidth(), 1.0F / MinecraftWrapper.get().getDisplayHeight());
        shadowShader.setUniformf("u_direction", directionX, directionY);
        shadowShader.setUniformb("u_kernel", buffer);
    }

}
