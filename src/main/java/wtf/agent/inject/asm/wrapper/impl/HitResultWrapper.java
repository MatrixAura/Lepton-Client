package wtf.agent.inject.asm.wrapper.impl;

import wtf.agent.inject.mapping.Mappings;

import java.lang.reflect.Field;

public class HitResultWrapper {

    private Type type;

    public HitResultWrapper(Object objectMouseOverObj) {

        try {
            // FD: auh/a net/minecraft/util/MovingObjectPosition/field_72313_a
            Field field = objectMouseOverObj.getClass().getField(Mappings.seargeToNotchField("field_72313_a")); // typeOfHit
            Object value = field.get(objectMouseOverObj);
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
