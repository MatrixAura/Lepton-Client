package cn.matrixaura.lepton.listener.events.packet;

import cn.matrixaura.lepton.listener.bus.CancellableEvent;

public class EventPacketReceive extends CancellableEvent {
    private final Object packet;

    public EventPacketReceive(Object packet) {
        this.packet = packet;
    }

    public Object getPacket() {
        return packet;
    }
}
