package cn.matrixaura.lepton.module.impl.visual;

import cn.matrixaura.lepton.inject.wrapper.impl.render.FramebufferWrapper;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.setting.Setting;

@ModuleInfo(name = "UI Effects", description = "Show effects on the GUI", category = Category.Visual, enabled = true, canToggle = false)
public class UIEffectsModule extends Module {

    public Setting<Boolean> shadow = setting("Shadow", true);

    public static UIEffectsModule INSTANCE;

    private FramebufferWrapper stencilFramebuffer = new FramebufferWrapper(FramebufferWrapper.newFramebuffer(1, 1, false));
    ;

    public UIEffectsModule() {
        INSTANCE = this;
    }

}
