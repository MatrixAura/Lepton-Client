package cn.matrixaura.lepton.module.impl.other.anticrash.checks;

import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.module.impl.other.anticrash.CrashCheck;
import cn.matrixaura.lepton.util.packet.PacketUtils;

public class EntityCheck extends CrashCheck {
    public EntityCheck() {
        super("Entity Check");
    }

    @Override
    public boolean handle(Object packet) {
        if (PacketUtils.isPacketInstanceof(packet, "S0FPacketSpawnMob")) {
            return MinecraftWrapper.get().getWorld().getLoadedEntities().size() > 1000;
        }
        return false;
    }
}
