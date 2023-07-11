package cn.matrixaura.lepton.module.impl.other;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.packet.EventPacketReceive;
import cn.matrixaura.lepton.listener.events.packet.EventPacketSend;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.util.inject.Mappings;

@ModuleInfo(name = "Packet Debugger", description = "Displays packets on the console", category = Category.Other)
public class PacketDebuggerModule extends Module {

    public Setting<String> mode = setting("Mode", "Inbound", "Inbound", "Outbound", "Both");

    @Listener
    public void onPacketReceive(EventPacketReceive event) {
        if (mode.getValue().equals("Inbound") || mode.getValue().equals("Both")) {
            Lepton.logger.info("[Packet-Debugger] <Inbound Packet> {}", Mappings.getUnobfClass(event.getPacket().getClass()));
        }
    }

    @Listener
    public void onPacketSend(EventPacketSend event) {
        if (mode.getValue().equals("Outbound") || mode.getValue().equals("Both")) {
            Lepton.logger.info("[Packet-Debugger] <Outbound Packet> {}", Mappings.getUnobfClass(event.getPacket().getClass()));
        }
    }

}
