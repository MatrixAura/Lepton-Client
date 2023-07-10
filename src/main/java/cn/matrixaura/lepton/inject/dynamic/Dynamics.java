package cn.matrixaura.lepton.inject.dynamic;

import cn.matrixaura.lepton.inject.dynamic.impl.gui.GuiScreenDynamic;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Dynamics {

    private static final List<Dynamic> dynamicClasses = new ArrayList<>();

    public static void init() throws Exception {
        dynamicClasses.forEach(clazz -> {
            try {
                byte[] bytes = clazz.dump();
                ReflectionUtils.invokeMethod(
                        ClassLoader.class,
                        ClassLoader.getSystemClassLoader(),
                        "defineClass",
                        new Class[]{
                                String.class,
                                byte[].class,
                                int.class,
                                int.class
                        },
                        clazz.getName(),
                        bytes,
                        0,
                        bytes.length
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    static {
        dynamicClasses.add(new GuiScreenDynamic());
    }
}
