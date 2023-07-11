package cn.matrixaura.lepton.module.impl.other.anticrash.checks;

import cn.matrixaura.lepton.module.impl.other.anticrash.CrashCheck;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import cn.matrixaura.lepton.util.packet.PacketUtils;

public class DemoCheck extends CrashCheck {

    public DemoCheck() {
        super("Demo Check", "Server attempted to prevent gameplay with a demo screen");
    }

    @Override
    public boolean handle(Object packet) {
        if (PacketUtils.isPacketInstanceof(packet, "net/minecraft/network/play/server/S2BPacketChangeGameState")) {
            return (Integer) ReflectionUtils.invokeMethod(packet.getClass(), packet, Mappings.seargeToNotchMethod("func_149138_c")) == 5 && (Integer) ReflectionUtils.invokeMethod(packet.getClass(), packet, Mappings.seargeToNotchMethod("func_149137_d")) == 0;
        }

        return false;
    }

}
