package cn.matrixaura.lepton.module;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.packet.EventPacketReceive;
import cn.matrixaura.lepton.listener.events.packet.EventPacketSend;
import cn.matrixaura.lepton.util.packet.BlinkUtils;
import cn.matrixaura.lepton.util.packet.PacketUtils;

public class Core {

    @Listener
    public void onPacketSend(EventPacketSend event) {
        if (BlinkUtils.isBlinking() && (BlinkUtils.getType() == BlinkUtils.Type.OUTBOUND || BlinkUtils.getType() == BlinkUtils.Type.BOTH)) {
            BlinkUtils.blockOutboundPacket(event);
            event.cancel();
        }
    }

    @Listener
    public void onPacketReceive(EventPacketReceive event) {
        if (BlinkUtils.isBlinking() && (BlinkUtils.getType() == BlinkUtils.Type.INBOUND || BlinkUtils.getType() == BlinkUtils.Type.BOTH)) {
            BlinkUtils.blockInboundPacket(event);
            event.cancel();
        }
    }

}
