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

    public void framebufferClear() {
        ReflectionUtils.invokeMethod(framebufferObj, Mappings.getObfMethod("func_147614_f"));
    }

    public void unbindFramebuffer() {
        ReflectionUtils.invokeMethod(framebufferObj, Mappings.getObfMethod("func_147609_e"));
    }

    public void deleteFramebuffer() {
        ReflectionUtils.invokeMethod(framebufferObj, Mappings.getObfMethod("func_147608_a"));
    }

    public int getFramebufferWidth() {
        return (Integer) ReflectionUtils.getFieldValue(framebufferObj, Mappings.getObfField("field_147621_c"));
    }

    public int getFramebufferHeight() {
        return (Integer) ReflectionUtils.getFieldValue(framebufferObj, Mappings.getObfField("field_147618_d"));
    }

    public int getFramebufferTexture() {
        return (Integer) ReflectionUtils.getFieldValue(framebufferObj, Mappings.getObfField("field_147617_g"));
    }

    public int getDepthBuffer() {
        return (Integer) ReflectionUtils.getFieldValue(framebufferObj, Mappings.getObfField("field_147624_h"));
    }

    public Object getFramebuffer() {
        return framebufferObj;
    }

    public void setDepthBuffer(int depthBuffer) {
        ReflectionUtils.setFieldValue(framebufferObj, Mappings.getObfField("field_147624_h"), depthBuffer);
    }

    public static Object newFramebuffer(int width, int height, boolean useDepth) {
        try {
            return ReflectionUtils.newInstance(Class.forName(Mappings.getObfClass("net/minecraft/client/shader/Framebuffer")), new Class[]{int.class, int.class, boolean.class}, width, height, useDepth);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
