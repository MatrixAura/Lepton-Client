package cn.matrixaura.lepton.module.movement;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.player.EventUpdate;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.util.player.PlayerUtils;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Speed", description = "Make you move faster", category = Category.Movement, key = Keyboard.KEY_G)
public class Speed extends Module {

    @Override
    public void onEnable() {
        mc.getTimer().setTimerSpeed(2F);
    }

    @Listener
    public void onUpdate(EventUpdate ignored) {
        if (mc.getPlayer().onGround() && PlayerUtils.isMoving()) {
            mc.getPlayer().jump();
        }
    }

    @Override
    public void onDisable() {
        mc.getTimer().setTimerSpeed(1F);
    }
}
