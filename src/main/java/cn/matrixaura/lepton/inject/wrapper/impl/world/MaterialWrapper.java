package cn.matrixaura.lepton.inject.wrapper.impl.world;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.util.inject.Mappings;

import java.lang.reflect.Field;

public class MaterialWrapper extends Wrapper {
    private final Object materialObj;

    public MaterialWrapper(Object materialObj) {
        super("net/minecraft/block/material/Material");
        this.materialObj = materialObj;
    }

    public boolean isReplaceable() {
        // FD: arm/K net/minecraft/block/material/Material/field_76239_H

        try {
            Field field = getClazz().getDeclaredField(Mappings.getObfField("field_76239_H"));
            field.setAccessible(true);
            Object value = field.get(materialObj);
            return value != null && (Boolean) value;
        } catch (Exception e) {
            e.printStackTrace();

        }

        return false;
    }
}
