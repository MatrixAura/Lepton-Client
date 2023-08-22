package cn.matrixaura.lepton.module.impl.movement;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.player.EventUpdate;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.util.player.PlayerUtils;

@ModuleInfo(name = "Speed", description = "Make you move faster", category = Category.Movement)
public class SpeedModule extends Module {

    public Setting<String> mode = setting("Mode", "Legit", "Legit", "Intave");

    @Listener
    public void onUpdate(EventUpdate event) {
        switch (mode.getValue()) {
            case "Legit": {
                if (mc.getPlayer().onGround() && PlayerUtils.isMoving()) {
                    mc.getGameSettings().getKey("key.jump").setPressed(false);
                    mc.getPlayer().jump();
                }
                break;
            }
            case "Intave": {
                if (mc.getPlayer().onGround()) {
                    mc.getGameSettings().getKey("key.jump").setPressed(false);
                    mc.getPlayer().jump();
                }
                if (!mc.getPlayer().onGround() && mc.getPlayer().getFallDistance() <= 0.1f) {
                    mc.getTimer().setTimerSpeed(1.4f);
                }
                if (mc.getPlayer().getFallDistance() > 0.1f && mc.getPlayer().getFallDistance() < 1.3f) {
                    mc.getTimer().setTimerSpeed(0.7f);
                }
                if (mc.getPlayer().getFallDistance() >= 1.3f) {
                    mc.getTimer().setTimerSpeed(1f);
                }
                break;
            }
        }
    }

    @Override
    public void onDisable() {
        if (mode.getValue().equals("Intave")) {
            mc.getTimer().setTimerSpeed(1f);
        }
    }
}
