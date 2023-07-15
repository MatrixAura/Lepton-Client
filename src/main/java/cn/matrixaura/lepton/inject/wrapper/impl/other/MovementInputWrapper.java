package cn.matrixaura.lepton.inject.wrapper.impl.other;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;

public class MovementInputWrapper extends Wrapper {

    private final Object movementInputObj;

    public MovementInputWrapper(Object obj) {
        super("net/minecraft/util/MovementInput");
        movementInputObj = obj;
    }

    public float getMoveForward() {
        return (Float) ReflectionUtils.getFieldValue(movementInputObj, Mappings.getObfField("field_78900_b"));
    }

    public float getMoveStrafe() {
        return (Float) ReflectionUtils.getFieldValue(movementInputObj, Mappings.getObfField("field_78902_a"));
    }
}
