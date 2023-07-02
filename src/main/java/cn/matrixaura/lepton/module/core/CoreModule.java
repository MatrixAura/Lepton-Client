package cn.matrixaura.lepton.module.core;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.packet.EventPacketSend;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.util.packet.BlinkUtils;
import cn.matrixaura.lepton.util.packet.PacketUtils;

@ModuleInfo(name = "Core", category = Category.HIDDEN, enabled = true, canToggle = false)
public class CoreModule extends Module {

    @Listener
    public void onPacketSend(EventPacketSend event) {
        if (BlinkUtils.isBlinking()) {
            if (BlinkUtils.getType() == BlinkUtils.Type.MOVING) {
                if (PacketUtils.isMovingPacket(event.getPacket())) BlinkUtils.blockOutboundPacket(event);
            } else BlinkUtils.blockOutboundPacket(event);
        }
    }

}
