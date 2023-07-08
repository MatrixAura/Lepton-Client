package cn.matrixaura.lepton.module.impl.world;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.packet.EventPacketReceive;
import cn.matrixaura.lepton.listener.events.packet.EventPacketSend;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.util.math.RandomUtils;
import cn.matrixaura.lepton.util.packet.PacketUtils;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

@ModuleInfo(name = "Ping Spoof", description = "aka. FakeLag", category = Category.World)
public class PingSpoof extends Module {

    public Setting<Boolean> inbound = setting("Inbound", false);
    public Setting<Boolean> outbound = setting("Outbound", true);
    public Setting<Number> maxPing = setting("Max Ping (MS)", 3050, 0, 5000, 10);
    public Setting<Number> minPing = setting("Min Ping (MS)", 3000, 0, 5000, 10);

    private final LinkedList<Object> packetBuffer = new LinkedList<>();

    @Listener
    public void onPacketSend(EventPacketSend event) {
        if (outbound.getValue()) {
            packetBuffer.add(event.getPacket());
            event.cancel();
            queuePacket(RandomUtils.longRandom(minPing.getValue().longValue(), maxPing.getValue().longValue()));
        }
    }

    @Listener
    public void onPacketReceive(EventPacketReceive event) {
        if (inbound.getValue()) {
            packetBuffer.add(event.getPacket());
            event.cancel();
            queuePacket(RandomUtils.longRandom(minPing.getValue().longValue(), maxPing.getValue().longValue()));
        }
    }

    private void queuePacket(long delayTime) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (Lepton.INSTANCE.getModuleManager().get(PingSpoof.class).isToggled()) {
                    Object packet = packetBuffer.poll();
                    if (PacketUtils.isPacketOutbound(packet)) mc.getNetHandler().addToSendQueue(packet);
                    else mc.getNetHandler().getNetworkManager().processPacket(packet);
                }
            }
        }, delayTime);
    }

}
