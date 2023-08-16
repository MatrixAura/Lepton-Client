package cn.matrixaura.lepton.util.render;

import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.gui.ScaledResolutionWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.render.FramebufferWrapper;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import cn.matrixaura.lepton.util.render.shader.ShaderUtils;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.EXTPackedDepthStencil;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtils {

    private static final ShaderUtils roundedShader = new ShaderUtils(ShaderUtils.Shaders.RoundedRect);
    private static final ShaderUtils roundedOutlineShader = new ShaderUtils(ShaderUtils.Shaders.RoundedOutline);
    private static final ShaderUtils roundedTexturedShader = new ShaderUtils(ShaderUtils.Shaders.RoundedTexture);

    public static void drawRoundedRect(float x, float y, float width, float height, float radius, Color color) {
        drawRoundedRect(x, y, width, height, radius, false, color);
    }

    public static void drawRoundedRect(float x, float y, float width, float height, float radius, boolean blur, Color color) {
        glColor4f(1, 1, 1, 1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_ALPHA_TEST);
        glAlphaFunc(GL_GREATER, 0);
        roundedShader.init();

        setupRoundedShaderUniforms(x, y, width, height, radius, roundedShader);
        roundedShader.setUniformi("blur", blur ? 1 : 0);
        roundedShader.setUniformf("color", color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);

        ShaderUtils.drawQuads(x - 1, y - 1, width + 2, height + 2);
        roundedShader.unload();
        glDisable(GL_BLEND);
    }

    public static void drawRoundedOutline(float x, float y, float width, float height, float radius, float outlineThickness, Color color, Color outlineColor) {
        glColor4f(1, 1, 1, 1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_ALPHA_TEST);
        glAlphaFunc(GL_GREATER, 0);
        roundedOutlineShader.init();

        ScaledResolutionWrapper sr = new ScaledResolutionWrapper(MinecraftWrapper.get());
        setupRoundedShaderUniforms(x, y, width, height, radius, roundedOutlineShader);
        roundedOutlineShader.setUniformf("outlineThickness", outlineThickness * sr.getScaleFactor());
        roundedOutlineShader.setUniformf("color", color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        roundedOutlineShader.setUniformf("outlineColor", outlineColor.getRed() / 255f, outlineColor.getGreen() / 255f, outlineColor.getBlue() / 255f, outlineColor.getAlpha() / 255f);


        ShaderUtils.drawQuads(x - (2 + outlineThickness), y - (2 + outlineThickness), width + (4 + outlineThickness * 2), height + (4 + outlineThickness * 2));
        roundedOutlineShader.unload();
        glDisable(GL_BLEND);
    }

    public static void drawRoundedTexture(float x, float y, float width, float height, float radius, float alpha) {
        glColor4f(1, 1, 1, 1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_ALPHA_TEST);
        glAlphaFunc(GL_GREATER, 0);
        roundedTexturedShader.init();
        roundedTexturedShader.setUniformi("textureIn", 0);
        setupRoundedShaderUniforms(x, y, width, height, radius, roundedTexturedShader);
        roundedTexturedShader.setUniformf("alpha", alpha);
        ShaderUtils.drawQuads(x - 1, y - 1, width + 2, height + 2);
        roundedTexturedShader.unload();
        glDisable(GL_BLEND);
    }

    private static void setupRoundedShaderUniforms(float x, float y, float width, float height, float radius, ShaderUtils roundedTexturedShader) {
        ScaledResolutionWrapper sr = new ScaledResolutionWrapper(MinecraftWrapper.get());
        roundedTexturedShader.setUniformf("location", x * sr.getScaleFactor(),
                (MinecraftWrapper.get().getDisplayHeight() - (height * sr.getScaleFactor())) - (y * sr.getScaleFactor()));
        roundedTexturedShader.setUniformf("rectSize", width * sr.getScaleFactor(), height * sr.getScaleFactor());
        roundedTexturedShader.setUniformf("radius", radius * sr.getScaleFactor());
    }

    public static void color(int rgb) {
        float a = (float) (rgb >> 24 & 0xFF) / 255.0f;
        float r = (float) (rgb >> 16 & 0xFF) / 255.0f;
        float g = (float) (rgb >> 8 & 0xFF) / 255.0f;
        float b = (float) (rgb & 0xFF) / 255.0f;
        GL11.glColor4f(r, g, b, a);
    }

    public static void drawRect(float x, float y, float x1, float y1, int color) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        color(color);
        GL11.glBegin(7);
        GL11.glVertex2f(x, y1);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x1, y);
        GL11.glVertex2f(x, y);
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public static Double getScaledWidth() {
        try {
            Object mc = MinecraftWrapper.get().getMinecraftObj();
            Object sr = ReflectionUtils.newInstance(Class.forName(Mappings.getObfClass("net/minecraft/client/gui/ScaledResolution")), new Class[]{mc.getClass()}, mc);
            return (Double) ReflectionUtils.invokeMethod(sr, Mappings.getObfMethod("func_78327_c"));
        } catch (Exception ignored) {
            return null;
        }
    }

    public static Double getScaledHeight() {
        try {
            Object mc = MinecraftWrapper.get().getMinecraftObj();
            Object sr = ReflectionUtils.newInstance(Class.forName(Mappings.getObfClass("net/minecraft/client/gui/ScaledResolution")), new Class[]{mc.getClass()}, mc);
            return (Double) ReflectionUtils.invokeMethod(sr, Mappings.getObfMethod("func_78324_d"));
        } catch (Exception ignored) {
            return null;
        }
    }

    public static FramebufferWrapper createFramebuffer(FramebufferWrapper framebuffer) {
        if (framebuffer == null || framebuffer.getFramebuffer() == null || framebuffer.getFramebufferWidth() != MinecraftWrapper.get().getDisplayWidth() || framebuffer.getFramebufferHeight() != MinecraftWrapper.get().getDisplayHeight()) {
            if (framebuffer != null && framebuffer.getFramebuffer() != null) {
                framebuffer.deleteFramebuffer();
            }
            return new FramebufferWrapper(FramebufferWrapper.newFramebuffer(MinecraftWrapper.get().getDisplayWidth(), MinecraftWrapper.get().getDisplayHeight(), true));
        }
        return framebuffer;
    }


    public static void checkAndSetupFBO(FramebufferWrapper fb) {
        if (fb != null) {
            if (fb.getDepthBuffer() > -1) {
                setupFBO(fb);
                fb.setDepthBuffer(-1);
            }
        }
    }

    public static void setupFBO(FramebufferWrapper fb) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(fb.getDepthBuffer());
        int stencilDepthBufferID = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT(EXTFramebufferObject.GL_RENDERBUFFER_EXT, stencilDepthBufferID);
        EXTFramebufferObject.glRenderbufferStorageEXT(EXTFramebufferObject.GL_RENDERBUFFER_EXT, EXTPackedDepthStencil.GL_DEPTH_STENCIL_EXT, MinecraftWrapper.get().getDisplayWidth(), MinecraftWrapper.get().getDisplayHeight());
        EXTFramebufferObject.glFramebufferRenderbufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, EXTFramebufferObject.GL_STENCIL_ATTACHMENT_EXT, EXTFramebufferObject.GL_RENDERBUFFER_EXT, stencilDepthBufferID);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, EXTFramebufferObject.GL_DEPTH_ATTACHMENT_EXT, EXTFramebufferObject.GL_RENDERBUFFER_EXT, stencilDepthBufferID);
    }

    public static void startStencil() {
        MinecraftWrapper.get().getFramebuffer().bindFramebuffer(false);
        checkAndSetupFBO(MinecraftWrapper.get().getFramebuffer());
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glStencilFunc(GL11.GL_ALWAYS, 1, 1);
        GL11.glStencilOp(GL11.GL_REPLACE, GL11.GL_REPLACE, GL11.GL_REPLACE);
        GL11.glColorMask(false, false, false, false);
    }

    public static void readStencil(int ref) {
        GL11.glColorMask(true, true, true, true);
        GL11.glStencilFunc(GL11.GL_EQUAL, ref, 1);
        GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_KEEP);
    }

    public static void stopStencil() {
        GL11.glDisable(GL11.GL_STENCIL_TEST);
    }
}
