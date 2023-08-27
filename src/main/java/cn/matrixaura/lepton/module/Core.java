package cn.matrixaura.lepton.module;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.packet.EventPacket;
import cn.matrixaura.lepton.util.packet.BlinkUtils;

public class Core {

    @Listener
    public void onPacket(EventPacket event) {
        if (BlinkUtils.isBlinking()) {
            if (event.getType() == EventPacket.Type.INBOUND && (BlinkUtils.getType() == BlinkUtils.Type.BOTH || BlinkUtils.getType() == BlinkUtils.Type.INBOUND)) {
                BlinkUtils.blockInboundPacket(event);
                event.cancel();
            } else if (event.getType() == EventPacket.Type.OUTBOUND && (BlinkUtils.getType() == BlinkUtils.Type.BOTH || BlinkUtils.getType() == BlinkUtils.Type.OUTBOUND)) {
                BlinkUtils.blockOutboundPacket(event);
                event.cancel();
            }
        }
    }

}
