package cn.matrixaura.lepton.module.impl.other.anticrash.checks;

import cn.matrixaura.lepton.module.impl.other.anticrash.CrashCheck;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ObjectUtils;
import cn.matrixaura.lepton.util.packet.PacketUtils;

public class TeleportCheck extends CrashCheck {

    public TeleportCheck() {
        super("Teleport Check");
    }

    @Override
    public boolean handle(Object packet) {
        if (PacketUtils.isPacketInstanceof(packet, "S08PacketPlayerPosLook")) {
            double x = (Double) ObjectUtils.invokeMethod(packet, Mappings.getObfMethod("func_148932_c"));
            double y = (Double) ObjectUtils.invokeMethod(packet, Mappings.getObfMethod("func_148928_d"));
            double z = (Double) ObjectUtils.invokeMethod(packet, Mappings.getObfMethod("func_148933_e"));

            return Math.abs(x) > 1E+9 || Math.abs(y) > 1E+9 || Math.abs(z) > 1E+9;
        }

        return false;
    }

}
