package cn.matrixaura.lepton.module.impl.other.anticrash.checks;

import cn.matrixaura.lepton.module.impl.other.anticrash.CrashCheck;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import cn.matrixaura.lepton.util.packet.PacketUtils;

public class ParticleCheck extends CrashCheck {

    private int particles;

    public ParticleCheck() {
        super("Particle Check");
    }

    @Override
    public boolean handle(Object packet) {
        if (PacketUtils.isPacketInstanceof(packet, "net/minecraft/network/play/server/S2APacketParticles")) {
            int particleCount = (Integer) ReflectionUtils.invokeMethod(packet, Mappings.getObfMethod("func_149222_k"));
            int particleSpeed = (Integer) ReflectionUtils.invokeMethod(packet, Mappings.getObfMethod("func_149227_j"));

            particles += particleCount;
            particles -= 6;
            particles = Math.min(particles, 150);

            return particles > 100 || particleCount < 1 || Math.abs(particleCount) > 20 || particleSpeed < 0 || particleSpeed > 1000;
        }

        return false;
    }

}
