package cn.matrixaura.lepton.inject.wrapper.impl.render;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.util.inject.Mappings;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FontRendererWrapper extends Wrapper {
    private final Object fontRendererObj;

    private int heightCache;

    public FontRendererWrapper(Object obj) {
        super("net/minecraft/client/gui/FontRenderer");
        this.fontRendererObj = obj;
    }

    public void drawString(String s, int x, int y, int color) {
        try {
            String notch = Mappings.getObfMethod("func_175065_a"); // drawString
            if (notch == null || notch.isEmpty()) return;
            Method m = getClazz().getMethod(notch, String.class, int.class, int.class, int.class);
            m.invoke(fontRendererObj, s, x, y, color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawStringShadow(String s, float x, float y, int color) {
        try {
            String notch = Mappings.getObfMethod("func_175063_a"); // drawStringWithShadow
            if (notch == null || notch.isEmpty()) return;
            Method m = getClazz().getMethod(notch, String.class, float.class, float.class, int.class);
            m.invoke(fontRendererObj, s, x, y, color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getStringWidth(String s) {
        try {
            String notch = Mappings.getObfMethod("func_78256_a"); // getStringWidth
            if (notch == null || notch.isEmpty()) return 0;
            Method m = getClazz().getMethod(notch, String.class);
            Object value = m.invoke(fontRendererObj, s);
            return value == null ? 0 : (Integer) value;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getHeight() {
        if (heightCache == 0) {
            try {
                String notch = Mappings.getObfField("field_78288_b"); // FONT_HEIGHT
                if (notch == null || notch.isEmpty()) return 0;
                Field f = getClazz().getField(notch);
                Object value = f.get(fontRendererObj);
                heightCache = value == null ? 0 : (Integer) value;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return heightCache;
    }
}
