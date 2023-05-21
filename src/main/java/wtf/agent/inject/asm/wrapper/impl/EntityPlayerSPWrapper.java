package wtf.agent.inject.asm.wrapper.impl;

import wtf.agent.inject.asm.api.ReflectionUtils;
import wtf.agent.inject.asm.wrapper.Wrapper;
import wtf.agent.inject.asm.wrapper.impl.world.BlockPosWrapper;
import wtf.agent.inject.mapping.Mappings;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class EntityPlayerSPWrapper extends Wrapper {
    private Object playerObj;

    public EntityPlayerSPWrapper() {
        super("net/minecraft/client/entity/EntityPlayerSP");
    }

    public boolean isNull() {
        return playerObj == null;
    }

    public double getX() {
        // FD: pk/s net/minecraft/entity/Entity/field_70165_t

        String notch = Mappings.seargeToNotchField("field_70165_t");
        Object value = ReflectionUtils.getFieldValue(getClazz(), playerObj, notch);
        return value == null ? 0.0 : (Double) value;
    }

    public double getY() {
        // FD: pk/s net/minecraft/entity/Entity/field_70165_t

        String notch = Mappings.seargeToNotchField("field_70163_u");
        Object value = ReflectionUtils.getFieldValue(getClazz(), playerObj, notch);
        return value == null ? 0.0 : (Double) value;
    }

    public double getZ() {
        // FD: pk/s net/minecraft/entity/Entity/field_70165_t

        String notch = Mappings.seargeToNotchField("field_70161_v");
        Object value = ReflectionUtils.getFieldValue(getClazz(), playerObj, notch);
        return value == null ? 0.0 : (Double) value;
    }

    public BlockPosWrapper getPos() {
        return new BlockPosWrapper(Math.floor(getX()), Math.floor(getY()), Math.floor(getZ()));
    }

    public Object getPosObj() {
        return BlockPosWrapper.create(getX(), getY(), getZ());
    }

    public void setSprinting(boolean value) {
        if (playerObj == null) return;

        // MD: bew/d (Z)V net/minecraft/client/entity/EntityPlayerSP/func_70031_b (Z)V

        String notch = Mappings.seargeToNotchMethod("func_70031_b"); // setSprinting
        try {
            Method m = getClazz().getMethod(notch, boolean.class);
            m.invoke(playerObj, value);
        } catch (Exception ignored) {

        }
    }

    public boolean isSprinting() {
        if (playerObj == null) return false;

        // MD: pk/aw ()Z net/minecraft/entity/Entity/func_70051_ag ()Z

        String notch = Mappings.seargeToNotchMethod("func_70051_ag"); // isSprinting
        try {
            Method m = getClazz().getDeclaredMethod(notch);
            Object value = m.invoke(playerObj);
            return value != null && (Boolean) value;
        } catch (Exception ignored) {

        }

        return false;
    }

    public void setPlayerObj(Object playerObj) {
        this.playerObj = playerObj;
    }

    public Object getPlayerObj() {
        return playerObj;
    }
}
