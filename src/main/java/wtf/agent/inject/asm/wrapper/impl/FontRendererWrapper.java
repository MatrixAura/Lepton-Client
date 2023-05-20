package wtf.agent.inject.asm.wrapper.impl;

import wtf.agent.inject.asm.api.Transformers;
import wtf.agent.inject.asm.wrapper.Wrapper;
import wtf.agent.inject.mapping.Mappings;

import java.lang.reflect.Method;

public class FontRendererWrapper extends Wrapper {
    private final Object fontRendererObj;

    public FontRendererWrapper(Object obj) {
        super("net/minecraft/client/gui/FontRenderer");
        this.fontRendererObj = obj;
    }

    public void drawString(String s, int x, int y, int color) {
        try {
            String notch = Mappings.seargeToNotchMethod("func_175065_a");
            if (notch == null || notch.isEmpty()) return;
            Method m = getClazz().getMethod(notch, String.class, int.class, int.class, int.class);
            m.invoke(fontRendererObj, s, x, y, color);
        } catch (Exception ignored) {
        }
    }
}
