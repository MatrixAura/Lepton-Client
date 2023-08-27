package cn.matrixaura.lepton.inject.wrapper.impl.entity;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.other.MovementInputWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.world.BlockPosWrapper;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import cn.matrixaura.lepton.util.math.Vec3D;
import net.minecraft.client.Minecraft;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class EntityPlayerSPWrapper extends Wrapper {
    private Object playerObj;
    private Object sendQueueObj;

    private MovementInputWrapper movementInputObj;

    public EntityPlayerSPWrapper() {
        super("net/minecraft/client/entity/EntityPlayerSP");
    }

    public boolean isNull() {
        return playerObj == null;
    }

    public double getX() {
        // FD: pk/s net/minecraft/entity/Entity/field_70165_t

        String notch = Mappings.getObfField("field_70165_t");
        Object value = ReflectionUtils.getFieldValue(playerObj, notch);
        return value == null ? 0.0 : (Double) value;
    }

    public double getY() {
        // FD: pk/s net/minecraft/entity/Entity/field_70165_t

        String notch = Mappings.getObfField("field_70163_u");
        Object value = ReflectionUtils.getFieldValue(playerObj, notch);
        return value == null ? 0.0 : (Double) value;
    }

    public double getZ() {
        // FD: pk/s net/minecraft/entity/Entity/field_70165_t

        String notch = Mappings.getObfField("field_70161_v");
        Object value = ReflectionUtils.getFieldValue(playerObj, notch);
        return value == null ? 0.0 : (Double) value;
    }

    public int getHurtTime() {
        return (Integer) ReflectionUtils.getFieldValue(playerObj, Mappings.getObfField("field_70737_aN"));
    }

    public double getMotionX() {
        return (Double) ReflectionUtils.getFieldValue(playerObj, Mappings.getObfField("field_70159_w"));
    }
    public double getMotionY() {
        return (Double) ReflectionUtils.getFieldValue(playerObj, Mappings.getObfField("field_70181_x"));
    }
    public double getMotionZ() {
        return (Double) ReflectionUtils.getFieldValue(playerObj, Mappings.getObfField("field_70179_y"));
    }

    public void setMotionX(double motionX) {
        ReflectionUtils.setFieldValue(playerObj, Mappings.getObfField("field_70159_w"), motionX);
    }
    public void setMotionY(double motionY) {
        ReflectionUtils.setFieldValue(playerObj, Mappings.getObfField("field_70181_x"), motionY);
    }
    public void setMotionZ(double motionZ) {
        ReflectionUtils.setFieldValue(playerObj, Mappings.getObfField("field_70179_y"), motionZ);
    }

    public boolean onGround() {
        // FD: pk/C net/minecraft/entity/Entity/field_70122_E
        return (Boolean) ReflectionUtils.getFieldValue(playerObj, Mappings.getObfField("field_70122_E"));
    }

    public float getFallDistance() {
        return (Float) ReflectionUtils.getFieldValue(playerObj, Mappings.getObfField("field_70143_R"));
    }

    public void setFallDistance(float fallDistance) {
        ReflectionUtils.setFieldValue(playerObj, Mappings.getObfField("field_70143_R"), fallDistance);
    }

    public float getSpeedInAir() {
        return (Float) ReflectionUtils.getFieldValue(playerObj, Mappings.getObfField("field_71102_ce"));
    }

    public void setSpeedInAir(float speedInAir) {
        ReflectionUtils.setFieldValue(playerObj, Mappings.getObfField("field_71102_ce"), speedInAir);
    }

    public boolean isInWater() {
        return (Boolean) ReflectionUtils.invokeMethod(playerObj, Mappings.getObfMethod("func_70090_H"));
    }

    public boolean isInLava() {
        return (Boolean) ReflectionUtils.invokeMethod(playerObj, Mappings.getObfMethod("func_180799_ab"));
    }

    public boolean isMoving() {
        return this.getMovementInputObj().getMoveForward() != 0 || this.getMovementInputObj().getMoveStrafe() != 0;
    }

    public void jump() {
        ReflectionUtils.invokeMethod(playerObj, Mappings.getObfMethod("func_70664_aZ"));
    }

    public BlockPosWrapper getPos() {
        return new BlockPosWrapper(Math.floor(getX()), Math.floor(getY()), Math.floor(getZ()));
    }

    public Object getPosObj() {
        return BlockPosWrapper.create(getX(), getY(), getZ());
    }

    public double getDistance(EntityPlayerWrapper wrapper) {
        float f = (float) (getX() - wrapper.getX());
        float f1 = (float) (getY() - wrapper.getY());
        float f2 = (float) (getZ() - wrapper.getZ());
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
        Object value = ReflectionUtils.getFieldValue(playerObj, Mappings.getObfField("field_70177_z"));
        return value == null ? 0.0f : (Float) value;
    }

    public float getPitch() {
        // FD: pk/z net/minecraft/entity/Entity/field_70125_A
        Object value = ReflectionUtils.getFieldValue(playerObj, Mappings.getObfField("field_70125_A"));
        return value == null ? 0.0f : (Float) value;
    }

    public Vec3D getVectorForRotation() {
        float pitch = getPitch();
        float yaw = getYaw();

        float f = (float) Math.cos(-yaw * 0.017453292F - (float) Math.PI);
        float f1 = (float) Math.sin(-yaw * 0.017453292F - (float) Math.PI);
        float f2 = (float) -Math.cos(-pitch * 0.017453292F);
        float f3 = (float) Math.sin(-pitch * 0.017453292F);
        return new Vec3D(f1 * f2, f3, f * f2);
    }

    public void setSprinting(boolean value) {
        if (playerObj == null) return;

        // MD: bew/d (Z)V net/minecraft/client/entity/EntityPlayerSP/func_70031_b (Z)V

        String notch = Mappings.getObfMethod("func_70031_b"); // setSprinting
        try {
            Method m = getClazz().getMethod(notch, boolean.class);
            m.invoke(playerObj, value);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public boolean isSprinting() {
        if (playerObj == null) return false;

        // MD: pk/aw ()Z net/minecraft/entity/Entity/func_70051_ag ()Z

        String notch = Mappings.getObfMethod("func_70051_ag"); // isSprinting
        try {
            Method m = getClazz().getDeclaredMethod(notch);
            Object value = m.invoke(playerObj);
            return value != null && (Boolean) value;
        } catch (Exception e) {
            e.printStackTrace();

        }

        return false;
    }

    public boolean isSneaking() {
        if (playerObj == null) return false;

        // MD: pk/av ()Z net/minecraft/entity/Entity/func_70093_af ()Z

        String notch = Mappings.getObfMethod("func_70093_af"); // isSneaking
        try {
            Method m = getClazz().getDeclaredMethod(notch);
            Object value = m.invoke(playerObj);
            return value != null && (Boolean) value;
        } catch (Exception e) {
            e.printStackTrace();

        }

        return false;
    }

    public Object getSendQueue() {
        if (sendQueueObj == null) {
            try {
                String notch = Mappings.getObfField("field_71174_a"); // sendQueue
                Field field = getClazz().getField(notch);
                sendQueueObj = field.get(playerObj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sendQueueObj;
    }

    public MovementInputWrapper getMovementInputObj() {
        if (movementInputObj == null) {
            try {
                movementInputObj = new MovementInputWrapper(ReflectionUtils.getFieldValue(playerObj, Mappings.getObfField("field_71158_b")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return movementInputObj;
    }

    public void addChatMessage(Object chatComponentText) {
        ReflectionUtils.invokeMethod(playerObj, Mappings.getObfMethod("func_145747_a"), new Class[]{chatComponentText.getClass()}, chatComponentText);
    }

    public void sendChatMessage(String message) {
        ReflectionUtils.invokeMethod(playerObj, Mappings.getObfMethod("func_71165_d"), new Class[]{String.class}, message);
    }

    public void setPlayerObj(Object playerObj) {
        this.playerObj = playerObj;
    }

    public Object getPlayerObj() {
        return playerObj;
    }

    public boolean isInFluid() {
        return isInWater() || isInLava();
    }
}
