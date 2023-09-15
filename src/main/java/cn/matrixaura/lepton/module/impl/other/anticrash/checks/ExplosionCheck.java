package cn.matrixaura.lepton.module.impl.other.anticrash.checks;

import cn.matrixaura.lepton.module.impl.other.anticrash.CrashCheck;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import cn.matrixaura.lepton.util.packet.PacketUtils;

public class ExplosionCheck extends CrashCheck {

    public ExplosionCheck() {
        super("Explosion Check");
    }

    @Override
    public boolean handle(Object packet) {
        if (PacketUtils.isPacketInstanceof(packet, "S27PacketExplosion")) {
            return (Byte) ReflectionUtils.invokeMethod(packet, Mappings.getObfMethod("func_149149_c")) >= Byte.MAX_VALUE
                    || (Byte) ReflectionUtils.invokeMethod(packet, Mappings.getObfMethod("func_149144_d")) >= Byte.MAX_VALUE
                    || (Byte) ReflectionUtils.invokeMethod(packet, Mappings.getObfMethod("func_149147_e")) >= Byte.MAX_VALUE;
        }

        return false;
    }

}
