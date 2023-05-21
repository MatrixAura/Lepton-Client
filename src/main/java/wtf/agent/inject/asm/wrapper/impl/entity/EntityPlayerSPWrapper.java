package wtf.agent.inject.asm.wrapper.impl.entity;

import wtf.agent.client.util.math.Vec3D;
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

    public double getDistance(EntityPlayerWrapper wrapper) {
        float f = (float)(getX() - wrapper.getX());
        float f1 = (float)(getY() - wrapper.getY());
        float f2 = (float)(getZ() - wrapper.getZ());
        return Math.sqrt(f * f + f1 * f1 + f2 * f2);
    }

    public double getEyeHeight() {
        double value = 1.62;
        if (isSneaking()) {
            value -= 0.08;
        }
        return value;
    }

    public float getYaw() {
        // FD: pk/y net/minecraft/entity/Entity/field_70177_z
        Object value = ReflectionUtils.getFieldValue(getClazz(), playerObj, Mappings.seargeToNotchField("field_70177_z"));
        return value == null ? 0.0f : (Float) value;
    }

    public float getPitch() {
        // FD: pk/z net/minecraft/entity/Entity/field_70125_A
        Object value = ReflectionUtils.getFieldValue(getClazz(), playerObj, Mappings.seargeToNotchField("field_70125_A"));
        return value == null ? 0.0f : (Float) value;
    }

    public Vec3D getVectorForRotation() {
        float pitch = getPitch();
        float yaw = getYaw();

        float f = (float) Math.cos(-yaw * 0.017453292F - (float)Math.PI);
        float f1 = (float) Math.sin(-yaw * 0.017453292F - (float)Math.PI);
        float f2 = (float) -Math.cos(-pitch * 0.017453292F);
        float f3 = (float) Math.sin(-pitch * 0.017453292F);
        return new Vec3D(f1 * f2, f3, f * f2);
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

    public boolean isSneaking() {
        if (playerObj == null) return false;

        // MD: pk/av ()Z net/minecraft/entity/Entity/func_70093_af ()Z

        String notch = Mappings.seargeToNotchMethod("func_70093_af"); // isSneaking
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
