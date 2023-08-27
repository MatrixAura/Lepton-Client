package cn.matrixaura.lepton.module.impl.combat;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.packet.EventPacket;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.util.packet.PacketUtils;

@ModuleInfo(name = "Velocity", description = "Reduces your velocity", category = Category.Combat)
public class VelocityModule extends Module {

    public Setting<String> mode = setting("Mode", "Cancel", "Cancel", "Old GrimAC");
    private int oldGrimCancelFlag;

    @Override
    public void onEnable() {
        oldGrimCancelFlag = 0;
    }

    @Listener
    public void onPacket(EventPacket event) {
        if (PacketUtils.isPacketInstanceof(event.getPacket(), "S12PacketEntityVelocity")) {
            switch (mode.getValue()) {
                case "Cancel": {
                    event.cancel();
                    break;
                }
                case "Old GrimAC": {
                    oldGrimCancelFlag = 6;
                    event.cancel();
                    if (mc.getPlayer().onGround() && !mc.getPlayer().isInFluid()) {
                        mc.getGameSettings().getKey("key.jump").setPressed(false);
                        mc.getPlayer().jump();
                    }
                }
            }
        } else if (PacketUtils.isPacketInstanceof(event.getPacket(), "C0FPacketConfirmTransaction") && oldGrimCancelFlag != 0) {
            oldGrimCancelFlag--;
            event.cancel();
        }
    }

}
