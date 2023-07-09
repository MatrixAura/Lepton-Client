package cn.matrixaura.lepton.module.impl.movement;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.player.EventUpdate;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.util.player.PlayerUtils;

@ModuleInfo(name = "Speed", description = "Make you move faster", category = Category.Movement)
public class Speed extends Module {

    public Setting<String> mode = setting("Mode", "Legit", "Legit", "Timer");

    @Listener
    public void onUpdate(EventUpdate ignored) {
        switch (mode.getValue()) {
            case "Legit": {
                if (mc.getPlayer().onGround() && PlayerUtils.isMoving()) mc.getPlayer().jump();
                break;
            }
            case "Timer": {
                mc.getTimer().setTimerSpeed(2F);
                if (mc.getPlayer().onGround() && PlayerUtils.isMoving()) mc.getPlayer().jump();
                break;
            }
        }
    }

    @Override
    public void onDisable() {
        if (mode.getValue().equals("Timer"))
            mc.getTimer().setTimerSpeed(1F);
    }
}
