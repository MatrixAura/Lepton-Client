package cn.matrixaura.lepton.inject.wrapper.impl;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.entity.EntityPlayerSPWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.network.NetHandlerPlayClientWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.other.TimerWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.render.EntityRendererWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.render.FontRendererWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.render.FramebufferWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.render.RenderManagerWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.setting.GameSettingsWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.world.WorldClientWrapper;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;

public class MinecraftWrapper extends Wrapper {
    private static final String CLASS = "net/minecraft/client/Minecraft";

    private static MinecraftWrapper instance;

    private final Object minecraftObj;

    private Object currentScreenObj;

    private final EntityPlayerSPWrapper thePlayer = new EntityPlayerSPWrapper();
    private final WorldClientWrapper theWorld = new WorldClientWrapper();

    private EntityRendererWrapper entityRenderer;
    private RenderManagerWrapper renderManager;
    private GameSettingsWrapper gameSettings;
    private NetHandlerPlayClientWrapper netHandlerPlayClientWrapper;
    private FramebufferWrapper framebufferWrapper;
    private TimerWrapper timer;

    private FontRendererWrapper fontRendererWrapper;

    private MinecraftWrapper(Object obj) {
        super(CLASS);
        this.minecraftObj = obj;
    }

    public GameSettingsWrapper getGameSettings() {
        if (gameSettings == null) {
            try {
                gameSettings = new GameSettingsWrapper(ReflectionUtils.getFieldValue(minecraftObj, Mappings.getObfField("field_71474_y")));
            } catch (Exception ignored) {

            }
        }

        return gameSettings;
    }

    public TimerWrapper getTimer() {
        if (timer == null) {
            try {
                timer = new TimerWrapper(ReflectionUtils.getFieldValue(minecraftObj, Mappings.getObfField("field_71428_T")));
            } catch (Exception ignored) {

            }
        }
        return timer;
    }

    public RenderManagerWrapper getRenderManager() {
        if (renderManager == null) {
            try {
                renderManager = new RenderManagerWrapper(ReflectionUtils.getFieldValue(minecraftObj, Mappings.getObfField("field_175616_W")));
            } catch (Exception ignored) {

            }
        }

        return renderManager;
    }

    public EntityRendererWrapper getEntityRenderer() {
        if (entityRenderer == null) {
            try {
                entityRenderer = new EntityRendererWrapper(ReflectionUtils.getFieldValue(minecraftObj, Mappings.getObfField("field_71460_t")));
            } catch (Exception ignored) {

            }
        }

        return entityRenderer;
    }

    public HitResult getHitResult() {

        try {
            Object value = ReflectionUtils.getFieldValue(minecraftObj, Mappings.getObfField("field_71476_x"));

            if (value == null) return null;

            return new HitResult(value);
        } catch (Exception ignored) {

        }

        return null;
    }

    public EntityPlayerSPWrapper getPlayer() {

        try {
            Object value = ReflectionUtils.getFieldValue(minecraftObj, Mappings.getObfField("field_71439_g"));
            thePlayer.setPlayerObj(value);
        } catch (Exception ignored) {

        }

        return thePlayer;
    }

    public WorldClientWrapper getWorld() {
        try {
            Object value = ReflectionUtils.getFieldValue(minecraftObj, Mappings.getObfField("field_71441_e"));
            theWorld.setWorldObj(value);
        } catch (Exception ignored) {

        }

        return theWorld;
    }

    public Object getCurrentScreen() {
        return currentScreenObj;
    }

    public void setCurrentScreen(Object currentScreenObj) {
        this.currentScreenObj = currentScreenObj;
    }

    public void displayGuiScreen(Object guiScreen) {
        try {
            ReflectionUtils.invokeMethod(minecraftObj, Mappings.getObfMethod("func_147108_a"), new Class[]{guiScreen.getClass()}, guiScreen);
        } catch (Exception ignored) {

        }
    }

    public int getDisplayWidth() {
        return (Integer) ReflectionUtils.getFieldValue(minecraftObj, Mappings.getObfField("field_71443_c"));
    }

    public int getDisplayHeight() {
        return (Integer) ReflectionUtils.getFieldValue(minecraftObj, Mappings.getObfField("field_71440_d"));
    }

    public FontRendererWrapper getFontRenderer() {
        if (fontRendererWrapper == null) {
            try {
                fontRendererWrapper = new FontRendererWrapper(ReflectionUtils.getFieldValue(minecraftObj, Mappings.getObfField("field_71466_p")));
            } catch (Exception ignored) {

            }
        }

        return fontRendererWrapper;
    }

    public NetHandlerPlayClientWrapper getNetHandler() {
        if (netHandlerPlayClientWrapper == null) {
            netHandlerPlayClientWrapper = new NetHandlerPlayClientWrapper(getPlayer().getSendQueue());
        }
        return netHandlerPlayClientWrapper;
    }

    public FramebufferWrapper getFramebuffer() {
        if (framebufferWrapper == null) {
            framebufferWrapper = new FramebufferWrapper(ReflectionUtils.invokeMethod(minecraftObj, Mappings.getObfMethod("func_147110_a")));
        }
        return framebufferWrapper;
    }

    public void setLeftClickCounter(int delay) {
        try {
            ReflectionUtils.setFieldValue(minecraftObj, Mappings.getObfField("field_71429_W"), delay);
        } catch (Exception ignored) {

        }
    }

    public void clickMouse() {
        try {
            ReflectionUtils.invokeMethod(minecraftObj, Mappings.getObfMethod("func_147116_af"));
        } catch (Exception ignored) {

        }
    }

    public void rightClickMouse() {
        try {
            ReflectionUtils.invokeMethod(minecraftObj, Mappings.getObfMethod("func_147121_ag"));
        } catch (Exception ignored) {

        }
    }

    public Object getMinecraftObj() {
        return minecraftObj;
    }

    public static MinecraftWrapper get() {
        if (instance == null) {
            try {
                instance = new MinecraftWrapper(ReflectionUtils.invokeMethod(Class.forName(Mappings.getObfClass(CLASS)), Mappings.getObfMethod("func_71410_x")));
            } catch (Exception ignored) {

            }
        }

        return instance;
    }
}
