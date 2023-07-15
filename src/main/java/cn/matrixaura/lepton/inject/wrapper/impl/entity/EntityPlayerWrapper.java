package cn.matrixaura.lepton.inject.wrapper.impl.entity;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;

public class EntityPlayerWrapper extends Wrapper {
    private final Object entityPlayerObj;

    public EntityPlayerWrapper(Object entityPlayerObj) {
        super("net/minecraft/entity/player/EntityPlayer");
        this.entityPlayerObj = entityPlayerObj;
    }

    public double getX() {
        // FD: pk/s net/minecraft/entity/Entity/field_70165_t

        String notch = Mappings.getObfField("field_70165_t");
        Object value = ReflectionUtils.getFieldValue(entityPlayerObj, notch);
        return value == null ? 0.0 : (Double) value;
    }

    public double getY() {
        // FD: pk/s net/minecraft/entity/Entity/field_70165_t

        String notch = Mappings.getObfField("field_70163_u");
        Object value = ReflectionUtils.getFieldValue(entityPlayerObj, notch);
        return value == null ? 0.0 : (Double) value;
    }

    public double getZ() {
        // FD: pk/s net/minecraft/entity/Entity/field_70165_t

        String notch = Mappings.getObfField("field_70161_v");
        Object value = ReflectionUtils.getFieldValue(entityPlayerObj, notch);
        return value == null ? 0.0 : (Double) value;
    }
}
