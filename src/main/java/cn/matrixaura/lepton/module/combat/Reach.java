package cn.matrixaura.lepton.module.combat;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.input.EventAttackReach;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import org.lwjgl.input.Keyboard;

@ModuleInfo(name = "Reach", description = "Reaches further than normal", category = Category.Combat, key = Keyboard.KEY_X)
public class Reach extends Module {

    @Listener
    public void onAttackReach(EventAttackReach event) {
        event.setReach(6);
    }
}
