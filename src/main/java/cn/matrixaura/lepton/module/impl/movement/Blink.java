package cn.matrixaura.lepton.module.impl.movement;

import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.util.packet.BlinkUtils;

@ModuleInfo(name = "Blink", description = "Pause your packet send/receive", category = Category.Movement)
public class Blink extends Module {

    public Setting<String> mode = setting("Mode", "Inbound", "Inbound", "Outbound", "Both");

    @Override
    public void onEnable() {
        BlinkUtils.setType(BlinkUtils.Type.valueOf(mode.getValue().toUpperCase()));
        BlinkUtils.startBlink();
    }

    @Override
    public void onDisable() {
        BlinkUtils.stopBlink();
    }

}
