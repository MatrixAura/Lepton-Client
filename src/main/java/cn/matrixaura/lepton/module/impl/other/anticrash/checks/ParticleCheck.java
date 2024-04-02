package cn.matrixaura.lepton.module.impl.other.anticrash.checks;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.module.impl.other.anticrash.CrashCheck;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ObjectUtils;
import cn.matrixaura.lepton.util.packet.PacketUtils;

public class ParticleCheck extends CrashCheck {

    private int particles;

    public ParticleCheck() {
        super("Particle Check");
    }

    @Override
    public boolean handle(Object packet) {
        if (PacketUtils.isPacketInstanceof(packet, "S2APacketParticles")) {
            int particleCount = (Integer) ObjectUtils.invokeMethod(packet, Mappings.getObfMethod("func_149222_k"));
            float particleSpeed = (Float) ObjectUtils.invokeMethod(packet, Mappings.getObfMethod("func_149227_j"));

            particles += particleCount;
            particles -= 6;
            particles = Math.min(particles, 150);

            boolean a = particles > 100, b = particleCount < 1, c = Math.abs(particleCount) > 100, d = particleSpeed < 0f, e=particleSpeed > 1000f;
            Lepton.logger.info("{} + {} + {} + {} + {}", a,b,c,d,e);

            return a || b || c || d || e;
        }

        return false;
    }

}
