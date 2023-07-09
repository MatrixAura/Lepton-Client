package cn.matrixaura.lepton.module.impl.movement;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.player.EventUpdate;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;

@ModuleInfo(name = "Sprint", description = "Automatically sprints for you", category = Category.Movement, enabled = true)
public class Sprint extends Module {

    @Listener
    public void onUpdate(EventUpdate event) {
        if (mc.getPlayer().isNull()) return;
        mc.getGameSettings().getKey("key.sprint").setPressed(true);
    }

    @Override
    public void onDisable() {
        mc.getGameSettings().getKey("key.sprint").setPressed(false);
    }
}
