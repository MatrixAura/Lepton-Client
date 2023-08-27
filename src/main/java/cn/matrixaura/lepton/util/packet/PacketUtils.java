package cn.matrixaura.lepton.util.packet;

import cn.matrixaura.lepton.util.inject.Mappings;

public class PacketUtils {

    public static boolean isPacketInstanceof(Object packet, String packetName) {
        String unobfClass = Mappings.getUnobfClass(packet);
        return unobfClass.contains(packetName);
    }

    public static boolean isPacketInbound(Object packet) {
        String[] spilt = Mappings.getUnobfClass(packet).split("/");
        String packetDeobfName = spilt[spilt.length - 1];
        return packetDeobfName.startsWith("S");
    }

    public static boolean isPacketOutbound(Object packet) {
        return !isPacketInbound(packet);
    }

}
