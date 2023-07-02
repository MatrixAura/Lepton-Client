package cn.matrixaura.lepton.inject.wrapper.impl.setting;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.util.inject.Mappings;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class KeyBindingWrapper extends Wrapper {
    private final Object keyBindingObj;

    private String keyDescription;

    public KeyBindingWrapper(Object keyBindingObj) {
        super("net/minecraft/client/settings/KeyBinding");
        this.keyBindingObj = keyBindingObj;
    }

    public String getKeyName() {
        if (keyDescription == null) {
            // MD: avb/g ()Ljava/lang/String; net/minecraft/client/settings/KeyBinding/func_151464_g ()Ljava/lang/String;

            try {
                Method method = getClazz().getMethod(Mappings.seargeToNotchMethod("func_151464_g"));
                Object value = method.invoke(keyBindingObj);
                return value == null ? null : (String) value;
            } catch (Exception ignored) {

            }
        }

        return keyDescription;
    }

    public void setPressed(boolean pressed) {
        // FD: avb/h net/minecraft/client/settings/KeyBinding/field_74513_e

        try {
            Field field = getClazz().getDeclaredField(Mappings.seargeToNotchField("field_74513_e"));
            field.setAccessible(true);
            field.set(keyBindingObj, pressed);
            field.setAccessible(false);
        } catch (Exception ignored) {

        }
    }

    public boolean isPressed() {
        // FD: avb/h net/minecraft/client/settings/KeyBinding/field_74513_e

        try {
            Field field = getClazz().getDeclaredField(Mappings.seargeToNotchField("field_74513_e"));
            field.setAccessible(true);
            Object value = field.get(keyBindingObj);
            field.setAccessible(false);

            return value != null && (Boolean) value;
        } catch (Exception ignored) {

        }

        return false;
    }
}
