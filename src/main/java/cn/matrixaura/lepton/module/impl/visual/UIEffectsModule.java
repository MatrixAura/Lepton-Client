package cn.matrixaura.lepton.module.impl.visual;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.listener.events.render.EventShader;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import cn.matrixaura.lepton.util.render.shader.impl.Shadow;

@ModuleInfo(name = "UI Effects", description = "Show effects on the GUI", category = Category.Visual, enabled = true, canToggle = false)
public class UIEffectsModule extends Module {

    public Setting<Boolean> shadow = setting("Shadow", true);

    public static UIEffectsModule INSTANCE;

    private Object stencilFramebuffer;

    public UIEffectsModule() {
        INSTANCE = this;
        try {
            stencilFramebuffer = ReflectionUtils.newInstance(
                    Class.forName(Mappings.getObfClass("net/minecraft/client/shader/Framebuffer")),
                    new Class[]{
                            int.class,
                            int.class,
                            boolean.class
                    },
                    1,
                    1,
                    false
            );
        } catch (ClassNotFoundException ignored) {
        }
    }


    public void process() {
        if (shadow.getValue()) {
            if (stencilFramebuffer != null) ReflectionUtils.invokeMethod(stencilFramebuffer, Mappings.getObfMethod("func_147608_a"));
            try {
                stencilFramebuffer = ReflectionUtils.newInstance(
                        Class.forName(Mappings.getObfClass("net/minecraft/client/shader/Framebuffer")),
                        new Class[]{
                                int.class,
                                int.class,
                                boolean.class
                        },
                        mc.getDisplayWidth(),
                        mc.getDisplayHeight(),
                        false
                );
            } catch (ClassNotFoundException ignored) {
            }
            ReflectionUtils.invokeMethod(stencilFramebuffer, Mappings.getObfMethod("func_147614_f"));
            ReflectionUtils.invokeMethod(stencilFramebuffer, Mappings.getObfMethod("func_147610_a"), new Class[]{boolean.class}, false);

            Lepton.getBus().dispatch(new EventShader());

            ReflectionUtils.invokeMethod(stencilFramebuffer, Mappings.getObfMethod("func_147609_e"));
            int framebufferTexture = (Integer) ReflectionUtils.getFieldValue(stencilFramebuffer, Mappings.getObfField("field_147617_g"));
            Shadow.render(framebufferTexture, 6, 2);
        }
    }

}
