package cn.matrixaura.lepton.module.impl.other.anticrash;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.packet.EventPacket;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.module.impl.other.anticrash.checks.*;

import java.util.ArrayList;
import java.util.List;

@ModuleInfo(name = "Anti Crash", description = "Prevent server crashes your game", category = Category.Other, enabled = true, canToggle = false)
public class AntiCrashModule extends Module {

    private final List<CrashCheck> checks = new ArrayList<>();

    public AntiCrashModule() {
        checks.add(new DemoCheck());
        checks.add(new ExplosionCheck());
        checks.add(new LoggerRCECheck());
        checks.add(new ParticleCheck());
        checks.add(new ResourcePackCheck());
        checks.add(new TeleportCheck());
        checks.add(new EntityCheck());
        checks.add(new RotationCheck());
        Lepton.logger.info("Loaded {} Anti-Crash checks", checks.size());
    }

    @Listener
    public void onPacketReceive(EventPacket event) {
        checks.forEach(crashCheck -> {
            if (crashCheck.handle(event.getPacket())) {
                event.cancel();
                Lepton.logger.info("[Anti-Crash] {} was detected. Message: {}", crashCheck.getName(), crashCheck.getMessage());
            }
        });
    }

}
