package cn.matrixaura.lepton.module.impl.other;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.packet.EventPacket;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.util.inject.Mappings;

@ModuleInfo(name = "Packet Debugger", description = "Displays packets on the console", category = Category.Other)
public class PacketDebuggerModule extends Module {

    @Listener
    public void onPacket(EventPacket event) {
        Lepton.logger.info("[Packet-Debugger] <Packet> {}", Mappings.getUnobfClass(event.getPacket().getClass()));
    }

}
