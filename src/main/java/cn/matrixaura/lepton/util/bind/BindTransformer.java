package cn.matrixaura.lepton.util.bind;

import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import org.lwjgl.input.Keyboard;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class BindTransformer {

    public static final int KEY_NONE = 0;
    public static final int KEY_RETURN = 13;
    public static final int KEY_SPACE = 32;
    public static final int KEY_APOSTROPHE = 39;
    public static final int KEY_MULTIPLY = 42;
    public static final int KEY_ADD = 43;
    public static final int KEY_COMMA = 44;
    public static final int KEY_MINUS = 45;
    public static final int KEY_PERIOD = 46;
    public static final int KEY_SLASH = 47;
    public static final int KEY_0 = 48;
    public static final int KEY_1 = 49;
    public static final int KEY_2 = 50;
    public static final int KEY_3 = 51;
    public static final int KEY_4 = 52;
    public static final int KEY_5 = 53;
    public static final int KEY_6 = 54;
    public static final int KEY_7 = 55;
    public static final int KEY_8 = 56;
    public static final int KEY_9 = 57;
    public static final int KEY_SEMICOLON = 59;
    public static final int KEY_EQUALS = 61;
    public static final int KEY_LMENU = 91;
    public static final int KEY_BACKSLASH = 92;
    public static final int KEY_RMENU = 93;
    public static final int KEY_GRAVE = 96;
    public static final int KEY_A = 97;
    public static final int KEY_B = 98;
    public static final int KEY_C = 99;
    public static final int KEY_D = 100;
    public static final int KEY_E = 101;
    public static final int KEY_F = 102;
    public static final int KEY_G = 103;
    public static final int KEY_H = 104;
    public static final int KEY_I = 105;
    public static final int KEY_J = 106;
    public static final int KEY_K = 107;
    public static final int KEY_L = 108;
    public static final int KEY_M = 109;
    public static final int KEY_N = 110;
    public static final int KEY_O = 111;
    public static final int KEY_P = 112;
    public static final int KEY_Q = 113;
    public static final int KEY_R = 114;
    public static final int KEY_S = 115;
    public static final int KEY_T = 116;
    public static final int KEY_U = 117;
    public static final int KEY_V = 118;
    public static final int KEY_W = 119;
    public static final int KEY_X = 120;
    public static final int KEY_Y = 121;
    public static final int KEY_Z = 122;

    public static final String[] keys = new String[256];
    public static final Map<String, Integer> keyMap = new HashMap<>();

    public static int standToLwjglKey(int key) {
        return (Integer) ReflectionUtils.getFieldValue(Keyboard.class, getStandKeyName(key));
    }

    public static int lwjglToStandKey(int key) {
        try {
            int standKey = keyMap.get("KEY_" + Keyboard.getKeyName(key));
            return standKey;
        } catch (NullPointerException ignored) {
            return KEY_NONE;
        }
    }

    public static String getStandKeyName(int key) {
        return keys[key];
    }

    public static String getLwjglKeyName(int key) {
        return "KEY_" + Keyboard.getKeyName(key);
    }

    static {
        Field[] fields = BindTransformer.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(Integer.TYPE)) {
                try {
                    String name = field.getName();
                    int value = field.getInt(null);
                    keys[value] = name;
                    keyMap.put(name, value);
                } catch (IllegalAccessException ignored) {
                }
            }
        }
    }

}
