package cn.matrixaura.lepton.inject.wrapper.impl;

import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;

public class HitResult {

    private Type type;

    public HitResult(Object objectMouseOverObj) {
        try {
            Object value = ReflectionUtils.getFieldValue(objectMouseOverObj.getClass(), objectMouseOverObj, Mappings.getObfField("field_72313_a"));
            type = Type.valueOf(((Enum<?>) value).name());
        } catch (Exception ignored) {
        }
    }

    public Type getType() {
        return type == null ? Type.MISS : type;
    }

    public enum Type {
        MISS, BLOCK, ENTITY
    }
}
