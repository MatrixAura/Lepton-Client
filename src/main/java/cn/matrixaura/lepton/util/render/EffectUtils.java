package cn.matrixaura.lepton.util.render;

import cn.matrixaura.lepton.inject.wrapper.impl.render.FramebufferWrapper;
import cn.matrixaura.lepton.util.render.shader.impl.Blur;
import cn.matrixaura.lepton.util.render.shader.impl.Shadow;

public class EffectUtils {

    private static FramebufferWrapper buffer = new FramebufferWrapper(FramebufferWrapper.newFramebuffer(1, 1, true));

    public static void drawBlur(Runnable runnable, float radius) {
        Blur.renderBlur(runnable, radius);
    }

    public static void drawShadow(Runnable runnable) {
        buffer = RenderUtils.createFramebuffer(buffer);

        buffer.framebufferClear();
        buffer.bindFramebuffer(true);

        runnable.run();

        buffer.unbindFramebuffer();
        int framebufferTexture = buffer.getFramebufferTexture();
        Shadow.render(framebufferTexture, 6, 2);
    }

}
