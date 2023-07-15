package cn.matrixaura.lepton.util.render;

import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import org.lwjgl.opengl.GL11;

import java.awt.*;

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

}
