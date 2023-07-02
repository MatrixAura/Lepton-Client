package cn.matrixaura.lepton.util.packet;

import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.listener.events.packet.EventPacketSend;

import java.util.concurrent.CopyOnWriteArrayList;

public class BlinkUtils {

    private static boolean blinking = false;
    private static Type type = Type.ALL;
    private static final CopyOnWriteArrayList<Object> blockedPackets = new CopyOnWriteArrayList<>();

    public static void startBlink() {
        blinking = true;
    }

    public static void stopBlink() {
        blinking = false;
        blockedPackets.forEach(MinecraftWrapper.get().getNetHandler()::addToSendQueue);
    }

    public static boolean isBlinking() {
        return blinking;
    }

    public static Type getType() {
        return type;
    }

    public static void setType(Type type1) {
        type = type1;
    }

    public static void blockOutboundPacket(EventPacketSend event) {
        blockedPackets.add(event.getPacket());
        event.setCancelled(true);
    }

    public enum Type {
        MOVING,
        ALL
    }

}
