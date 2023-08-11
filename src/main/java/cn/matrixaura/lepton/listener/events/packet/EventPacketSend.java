package cn.matrixaura.lepton.listener.events.packet;

import cn.matrixaura.lepton.listener.bus.CancellableEvent;

public class EventPacketSend extends CancellableEvent {
    private final Object packet;

    public EventPacketSend(Object packet) {
        this.packet = packet;
    }

    public Object getPacket() {
        return packet;
    }
}
