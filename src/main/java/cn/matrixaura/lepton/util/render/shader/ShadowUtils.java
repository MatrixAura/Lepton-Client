package cn.matrixaura.lepton.util.render.shader;

import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ShadowUtils {

    public static ShaderUtils coloredShadow = new ShaderUtils(ShaderUtils.Shaders.Shadow);

    public static void render(float x, float y, float width, float height, float sigma, Color color) {
        GL11.glColor4f(1, 1, 1, 1);
        boolean blendEnabled = GL11.glIsEnabled(GL11.GL_BLEND);
        if (!blendEnabled) GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        coloredShadow.init();
        coloredShadow.setUniformf("u_Color", color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        coloredShadow.setUniformf("u_InnerRect", x, y, x + width, y + height);
        coloredShadow.setUniformf("u_Spread", sigma);
        ShaderUtils.drawQuads();
        coloredShadow.unload();
        if (!blendEnabled) GL11.glDisable(GL11.GL_BLEND);
    }

}
