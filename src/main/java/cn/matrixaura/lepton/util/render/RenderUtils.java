package cn.matrixaura.lepton.util.render;

import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.render.FramebufferWrapper;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.EXTPackedDepthStencil;
import org.lwjgl.opengl.GL11;

public class RenderUtils {

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
