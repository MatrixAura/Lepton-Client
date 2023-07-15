package cn.matrixaura.lepton.inject.wrapper.impl.render;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;

public class FramebufferWrapper extends Wrapper {

    private final Object framebufferObj;

    public FramebufferWrapper(Object obj) {
        super("net/minecraft/client/shader/Framebuffer");
        this.framebufferObj = obj;
    }

    public void bindFramebuffer(boolean viewport) {
        ReflectionUtils.invokeMethod(framebufferObj, Mappings.getObfMethod("func_147610_a"), new Class[]{boolean.class}, viewport);
    }

}
