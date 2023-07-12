package cn.matrixaura.lepton.inject.wrapper.impl.render;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.util.inject.Mappings;

import java.lang.reflect.Method;

public class EntityRendererWrapper extends Wrapper {
    private final Object entityRendererObj;

    public EntityRendererWrapper(Object entityRendererObj) {
        super("net/minecraft/client/renderer/EntityRenderer");
        this.entityRendererObj = entityRendererObj;
    }

    public void orientCamera(float partialTicks) {
        try {
            // MD: bfk/f (F)V net/minecraft/client/renderer/EntityRenderer/func_78467_g (F)V
            Method method = getClazz().getDeclaredMethod(Mappings.getObfMethod("func_78467_g"), float.class);
            method.setAccessible(true);
            method.invoke(entityRendererObj, partialTicks);
        } catch (Exception ignored) {

        }
    }
}
