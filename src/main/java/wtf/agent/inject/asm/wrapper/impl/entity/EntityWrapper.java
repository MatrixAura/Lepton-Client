package wtf.agent.inject.asm.wrapper.impl.entity;

import wtf.agent.inject.asm.api.ReflectionUtils;
import wtf.agent.inject.asm.wrapper.Wrapper;
import wtf.agent.inject.asm.wrapper.impl.world.BlockPosWrapper;
import wtf.agent.inject.mapping.Mappings;

public class EntityWrapper extends Wrapper {
    private final Object entityObj;

    public EntityWrapper(Object entityObj) {
        super("net/minecraft/entity/Entity");
        this.entityObj = entityObj;
    }

    public double getX() {
        // FD: pk/s net/minecraft/entity/Entity/field_70165_t

        String notch = Mappings.seargeToNotchField("field_70165_t");
        Object value = ReflectionUtils.getFieldValue(getClazz(), entityObj, notch);
        return value == null ? 0.0 : (Double) value;
    }

    public double getY() {
        // FD: pk/s net/minecraft/entity/Entity/field_70165_t

        String notch = Mappings.seargeToNotchField("field_70163_u");
        Object value = ReflectionUtils.getFieldValue(getClazz(), entityObj, notch);
        return value == null ? 0.0 : (Double) value;
    }

    public double getZ() {
        // FD: pk/s net/minecraft/entity/Entity/field_70165_t

        String notch = Mappings.seargeToNotchField("field_70161_v");
        Object value = ReflectionUtils.getFieldValue(getClazz(), entityObj, notch);
        return value == null ? 0.0 : (Double) value;
    }

    public BlockPosWrapper getPos() {
        return new BlockPosWrapper(Math.floor(getX()), Math.floor(getY()), Math.floor(getZ()));
    }

    public Object getPosObj() {
        return BlockPosWrapper.create(getX(), getY(), getZ());
    }
}
