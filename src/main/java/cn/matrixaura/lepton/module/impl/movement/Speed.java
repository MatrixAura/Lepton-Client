package cn.matrixaura.lepton.module.impl.movement;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.player.EventUpdate;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.util.player.PlayerUtils;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Speed", description = "Make you move faster", category = Category.Movement, key = Keyboard.KEY_G)
public class Speed extends Module {

    public Setting<Mode> mode = setting("Mode", Mode.Legit);

    @Listener
    public void onUpdate(EventUpdate ignored) {
        switch (mode.getValue()) {
            case Legit: {
                if (mc.getPlayer().onGround() && PlayerUtils.isMoving()) mc.getPlayer().jump();
                break;
            }
            case Timer: {
                mc.getTimer().setTimerSpeed(2F);
                if (mc.getPlayer().onGround() && PlayerUtils.isMoving()) mc.getPlayer().jump();
                break;
            }
        }
    }

    @Override
    public void onDisable() {
        if (mode.getValue() == Mode.Timer) mc.getTimer().setTimerSpeed(1F);
    }

    public enum Mode {
        Legit,
        Timer
    }
}
