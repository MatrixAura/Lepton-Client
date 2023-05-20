package wtf.agent.inject.asm.wrapper.impl;

import wtf.agent.inject.asm.api.Transformers;
import wtf.agent.inject.mapping.Mappings;
import wtf.agent.inject.asm.wrapper.Wrapper;

import java.lang.reflect.Field;

public class MinecraftWrapper extends Wrapper {
    private static final String CLASS = "net/minecraft/client/Minecraft";

    private static MinecraftWrapper instance;

    private final Object minecraftObj;

    private EntityPlayerSPWrapper thePlayer = new EntityPlayerSPWrapper();
    private FontRendererWrapper fontRendererWrapper;

    private MinecraftWrapper(Object obj) {
        super(CLASS);
        this.minecraftObj = obj;
    }

    public EntityPlayerSPWrapper getPlayer() {

        try {
            // FD: ave/h net/minecraft/client/Minecraft/field_71439_g
            String notch = Mappings.seargeToNotchField("field_71439_g"); // thePlayer
            Field field = getClazz().getField(notch);

            Object value = field.get(minecraftObj);
            thePlayer.setPlayerObj(value);
        } catch (Exception ignored) {

        }

        return thePlayer;
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

    public void setLeftClickCounter(int delay) {
        // FD: ave/ag net/minecraft/client/Minecraft/field_71429_W

        try {
            String notch = Mappings.seargeToNotchField("field_71429_W"); // leftClickCounter
            Field field = getClazz().getDeclaredField(notch);
            field.setAccessible(true);
            field.set(minecraftObj, delay);
        } catch (Exception ignored) {

        }
    }

    public static MinecraftWrapper get() {
        if (instance == null) {
            try {
                String notchClass = Mappings.getUnobfClass(CLASS);
                String notch = Mappings.seargeToNotchMethod("func_71410_x");

                Class<?> clazz = Class.forName(notchClass);
                Object obj = clazz.getDeclaredMethod(notch).invoke(null);

                instance = new MinecraftWrapper(obj);
            } catch (Exception ignored) {

            }
        }

        return instance;
    }
}
