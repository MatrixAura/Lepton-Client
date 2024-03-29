package cn.matrixaura.lepton.inject.wrapper.impl;

import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ObjectUtils;

public class HitResult {

    private Type type;

    public HitResult(Object objectMouseOverObj) {
        try {
            Object value = ObjectUtils.getFieldValue(objectMouseOverObj, Mappings.getObfField("field_72313_a"));
            type = Type.valueOf(((Enum<?>) value).name());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Type getType() {
        return type == null ? Type.MISS : type;
    }

    public enum Type {
        MISS, BLOCK, ENTITY
    }
}
