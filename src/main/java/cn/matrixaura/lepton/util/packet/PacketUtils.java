package cn.matrixaura.lepton.util.packet;

import cn.matrixaura.lepton.util.inject.Mappings;

public class PacketUtils {

    public static boolean isPacketInstanceof(Object packet, String packetName) {
        String[] spilt = Mappings.getUnobfClass(packet.getClass().getName().replace(".", "/")).split("/");
        String packetDeobfName = spilt[spilt.length - 1];
        return packetDeobfName.equals(packetName);
    }

    public static boolean isPacketInbound(Object packet) {
        String[] spilt = Mappings.getUnobfClass(packet.getClass().getName().replace(".", "/")).split("/");
        String packetDeobfName = spilt[spilt.length - 1];
        return packetDeobfName.startsWith("S");
    }

    public static boolean isPacketOutbound(Object packet) {
        return !isPacketInbound(packet);
    }

    public static boolean isMovingPacket(Object packet) {
        return packet.getClass().getName().replace(".", "/").startsWith(Mappings.getObfClass("net/minecraft/network/play/client/C03PacketPlayer"));
    }

}
