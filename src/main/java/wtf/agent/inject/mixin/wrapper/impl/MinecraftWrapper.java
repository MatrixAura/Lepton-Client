package wtf.agent.inject.mixin.wrapper.impl;

import wtf.agent.inject.mixin.api.Mixins;
import wtf.agent.inject.mixin.mapping.Mappings;
import wtf.agent.inject.mixin.wrapper.Wrapper;

import java.lang.reflect.Field;

public class MinecraftWrapper extends Wrapper {
    private static final String CLASS = "net/minecraft/client/Minecraft";

    private static MinecraftWrapper instance;

    private final Object minecraftObj;

    private FontRendererWrapper fontRendererWrapper;

    private MinecraftWrapper(Object obj) {
        super(CLASS);
        this.minecraftObj = obj;
    }

    public FontRendererWrapper getFontRenderer() {
        if (fontRendererWrapper == null) {
            try {
                String notch = Mappings.seargeToNotchField("field_71466_p");
                Field field = getClazz().getField(notch);
                fontRendererWrapper = new FontRendererWrapper(field.get(minecraftObj));
            } catch (Exception ignored) {

            }
        }

        return fontRendererWrapper;
    }

    public static MinecraftWrapper get() {
        if (instance == null) {
            try {
                String notchClass = Mappings.getUnobfClass(CLASS);
                String notch = Mappings.seargeToNotchMethod("func_71410_x");

                Mixins.logger.info("{};{}", notchClass, notch);

                Class<?> clazz = Class.forName(notchClass);
                Object obj = clazz.getDeclaredMethod(notch).invoke(null);

                Mixins.logger.info(obj);

                instance = new MinecraftWrapper(obj);
            } catch (Exception ignored) {

            }
        }

        return instance;
    }
}
