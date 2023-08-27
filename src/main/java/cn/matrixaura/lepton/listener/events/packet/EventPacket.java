package cn.matrixaura.lepton.listener.events.packet;

import cn.matrixaura.lepton.listener.bus.CancellableEvent;

public class EventPacket extends CancellableEvent {
    private final Object packet;
    private final Type type;

    public EventPacket(Object packet, boolean isInbound) {
        this.packet = packet;
        this.type = isInbound ? Type.INBOUND : Type.OUTBOUND;
    }

    public Object getPacket() {
        return packet;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        INBOUND,
        OUTBOUND
    }
}
