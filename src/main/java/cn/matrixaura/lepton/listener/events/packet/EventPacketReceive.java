package cn.matrixaura.lepton.listener.events.packet;

import cn.matrixaura.lepton.listener.bus.CancelableEvent;

public class EventPacketReceive extends CancelableEvent {
    private final Object packet;

    public EventPacketReceive(Object packet) {
        this.packet = packet;
    }

    public Object getPacket() {
        return packet;
    }
}
