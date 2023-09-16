package cn.matrixaura.lepton.module.impl.other.anticrash.checks;

import cn.matrixaura.lepton.module.impl.other.anticrash.CrashCheck;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ObjectUtils;
import cn.matrixaura.lepton.util.packet.PacketUtils;

public class RotationCheck extends CrashCheck {
    public RotationCheck() {
        super("Rotation Check");
    }

    @Override
    public boolean handle(Object packet) {
        if (PacketUtils.isPacketInstanceof(packet, "S08PacketPlayerPosLook")) {
            float yaw = (Float) ObjectUtils.invokeMethod(packet, Mappings.getObfMethod("func_148931_f"));
            float pitch = (Float) ObjectUtils.invokeMethod(packet, Mappings.getObfMethod("func_148930_g"));
            return Math.abs(yaw) > 360f || Math.abs(pitch) > 90f;
        }
        return false;
    }
}
