package wtf.agent.inject.asm.wrapper.impl.world;

import wtf.agent.inject.asm.wrapper.Wrapper;
import wtf.agent.inject.mapping.Mappings;

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
            Field field = getClazz().getDeclaredField(Mappings.seargeToNotchField("field_76239_H"));
            field.setAccessible(true);
            Object value = field.get(materialObj);
            return value != null && (Boolean) value;
        } catch (Exception ignored) {

        }

        return false;
    }
}
