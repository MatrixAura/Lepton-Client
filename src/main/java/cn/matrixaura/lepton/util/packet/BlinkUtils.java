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
            switch (type) {
                case INBOUND: {
                    blockedPackets.forEach(MinecraftWrapper.get().getNetHandler().getNetworkManager()::processPacket);
                    break;
                }
                case OUTBOUND: {
                    blockedPackets.forEach(MinecraftWrapper.get().getNetHandler()::addToSendQueue);
                    break;
                }
                case BOTH: {
                    blockedPackets.forEach(packet -> {
                        if (PacketUtils.isPacketInbound(packet)) {
                            MinecraftWrapper.get().getNetHandler().getNetworkManager().processPacket(packet);
                        } else MinecraftWrapper.get().getNetHandler().addToSendQueue(packet);
                    });
                    break;
                }
            }
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

    public static void blockOutboundPacket(Object packet) {
        blockedPackets.add(packet);
    }

    public static void blockInboundPacket(Object packet) {
        blockedPackets.add(packet);
    }

    public enum Type {
        INBOUND,
        OUTBOUND,
        BOTH
    }

}
