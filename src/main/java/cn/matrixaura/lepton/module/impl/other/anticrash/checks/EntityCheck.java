package cn.matrixaura.lepton.module.impl.other.anticrash.checks;

import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.module.impl.other.anticrash.CrashCheck;
import cn.matrixaura.lepton.util.inject.Mappings;

public class EntityCheck extends CrashCheck {
    public EntityCheck() {
        super("Entity Check", "Blocked server's entity spammer");
    }

    @Override
    public boolean handle(Object packet) {
        if (Mappings.getUnobfClass(packet.getClass().getName().replace(".", "/")).equals("net/minecraft/network/play/server/S0FPacketSpawnMob")) {
            return MinecraftWrapper.get().getWorld().getLoadedEntities().size() > 500;
        }
        return false;
    }
}
