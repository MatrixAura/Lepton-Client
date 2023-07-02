package cn.matrixaura.lepton.listener.events.packet;

public class EventPacketReceive {
    private final Object packet;
    private boolean cancelled = false;

    public EventPacketReceive(Object packet) {
        this.packet = packet;
    }

    public Object getPacket() {
        return packet;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
