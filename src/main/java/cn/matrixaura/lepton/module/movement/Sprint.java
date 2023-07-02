package cn.matrixaura.lepton.module.movement;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.player.EventUpdate;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Sprint", description = "Automatically sprints for you", category = Category.MOVEMENT, key = Keyboard.KEY_O, enabled = true)
public class Sprint extends Module {

    @Listener
    public void onUpdate(EventUpdate event) {
        if (mc.getPlayer().isNull()) return;
        mc.getGameSettings().getKey("key.sprint").setPressed(true);
    }
}
