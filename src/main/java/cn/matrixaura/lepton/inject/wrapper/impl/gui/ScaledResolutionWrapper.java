package cn.matrixaura.lepton.inject.wrapper.impl.gui;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ObjectUtils;

public class ScaledResolutionWrapper extends Wrapper {
    private Object scaledResolutionObj;

    public ScaledResolutionWrapper(MinecraftWrapper mc) {
        super("net/minecraft/client/gui/ScaledResolution");
        scaledResolutionObj = ObjectUtils.newInstance(getClazz(), new Class[]{MinecraftWrapper.get().getClazz()}, mc.getMinecraftObj());
    }

    public int getScaleFactor() {
        return (Integer) ObjectUtils.invokeMethod(scaledResolutionObj, Mappings.getObfMethod("func_78325_e"));
    }

    public int getScaledWidth() {
        return (Integer) ObjectUtils.invokeMethod(scaledResolutionObj, Mappings.getObfMethod("func_78326_a"));
    }

    public int getScaledHeight() {
        return (Integer) ObjectUtils.invokeMethod(scaledResolutionObj, Mappings.getObfMethod("func_78328_b"));
    }

    public double getScaledWidth_double() {
        return (Integer) ObjectUtils.invokeMethod(scaledResolutionObj, Mappings.getObfMethod("func_78327_c"));
    }

    public double getScaledHeight_double() {
        return (Integer) ObjectUtils.invokeMethod(scaledResolutionObj, Mappings.getObfMethod("func_78324_d"));
    }

}
