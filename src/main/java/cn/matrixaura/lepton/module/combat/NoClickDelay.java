package cn.matrixaura.lepton.module.combat;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.player.EventUpdate;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;

@ModuleInfo(name = "NoClickDelay", description = "Stops left click delay", category = Category.Combat, enabled = true)
public class NoClickDelay extends Module {

    @Listener
    public void onUpdate(EventUpdate event) {
        mc.setLeftClickCounter(0);
    }
}
