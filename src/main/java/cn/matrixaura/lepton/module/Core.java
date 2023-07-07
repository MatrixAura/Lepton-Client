package cn.matrixaura.lepton.module;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.packet.EventPacketSend;
import cn.matrixaura.lepton.util.packet.BlinkUtils;
import cn.matrixaura.lepton.util.packet.PacketUtils;

public class Core {

    @Listener
    public void onPacketSend(EventPacketSend event) {
        if (BlinkUtils.isBlinking()) {
            if (BlinkUtils.getType() == BlinkUtils.Type.MOVING) {
                if (PacketUtils.isMovingPacket(event.getPacket())) BlinkUtils.blockOutboundPacket(event);
            } else BlinkUtils.blockOutboundPacket(event);
        }
    }

}
