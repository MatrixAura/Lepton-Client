package cn.matrixaura.lepton.util.packet;

import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;

import java.util.ArrayList;
import java.util.List;

public class BlinkUtils {

    private static boolean blinking = false;
    private static Type type = Type.OUTBOUND;
    private static final List<Object> blockedPackets = new ArrayList<>();

    public static void startBlink() {
        blinking = true;
    }

    public static void stopBlink() {
        blinking = false;
        if (!blockedPackets.isEmpty()) {
            blockedPackets.forEach(packet -> {
                if (PacketUtils.isPacketInbound(packet)) {
                    MinecraftWrapper.get().getNetHandler().getNetworkManager().processPacket(packet);
                } else MinecraftWrapper.get().getNetHandler().addToSendQueue(packet);
            });
        }
        blockedPackets.clear();
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

    public static void blockPacket(Object packet) {
        blockedPackets.add(packet);
    }

    public enum Type {
        INBOUND,
        OUTBOUND,
        BOTH
    }

}
