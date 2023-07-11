package cn.matrixaura.lepton.module.impl.combat;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.packet.EventPacketReceive;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.util.packet.PacketUtils;

@ModuleInfo(name = "Velocity", description = "Reduces your velocity", category = Category.Combat)
public class VelocityModule extends Module {

    public Setting<String> mode = setting("Mode", "Vanilla", "Vanilla");

    @Listener
    public void onVelocity(EventPacketReceive event) {
        if (PacketUtils.isPacketInstanceof(event.getPacket(), "S12PacketEntityVelocity")) {
            switch (mode.getValue()) {
                case "Vanilla": {
                    event.cancel();
                    break;
                }
                default: { // Not happen
                    mode.reset();
                }
            }
        }
    }

}
