package cn.matrixaura.lepton.module.impl.combat;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.packet.EventPacket;
import cn.matrixaura.lepton.listener.events.player.EventUpdate;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.util.packet.PacketUtils;

@ModuleInfo(name = "Velocity", description = "Reduces your velocity", category = Category.Combat)
public class VelocityModule extends Module {

    public Setting<String> mode = setting("Mode", "Cancel", "Cancel", "Cancel C0F", "Cancel C00", "Freeze When Hurt");
    public Setting<Number> hurtTime = setting("Min Hurt Time", 5, 1, 10, 1);
    private int cancelPackets;

    @Override
    public void onEnable() {
        cancelPackets = 0;
    }

    @Listener
    public void onUpdate(EventUpdate event) {
        switch (mode.getValue()) {
            case "Freeze When Hurt": {
                if (mc.getPlayer().getHurtTime() >= hurtTime.getValue().intValue()) {
                    mc.getPlayer().setMotionX(0);
                    mc.getPlayer().setMotionY(0);
                    mc.getPlayer().setMotionZ(0);
                }
            }
        }
    }

    @Listener
    public void onPacket(EventPacket event) {
        if (PacketUtils.isPacketInstanceof(event.getPacket(), "S12PacketEntityVelocity")) {
            switch (mode.getValue()) {
                case "Cancel": {
                    event.cancel();
                    break;
                }
                case "Cancel C00":
                case "Cancel C0F": {
                    cancelPackets = 6;
                    event.cancel();
                    break;
                }
            }
        } else if (cancelPackets != 0) {
            switch (mode.getValue()) {
                case "Cancel C00": {
                    if (PacketUtils.isPacketInstanceof(event.getPacket(), "C00PacketKeepAlive")) {
                        cancelPackets--;
                        event.cancel();
                    }
                    break;
                }
                case "Cancel C0F": {
                    if (PacketUtils.isPacketInstanceof(event.getPacket(), "C0FPacketConfirmTransaction")) {
                        cancelPackets--;
                        event.cancel();
                    }
                }
                case "Freeze When Hurt": {
                    if (PacketUtils.isPacketInstanceof(event.getPacket(), "C03PacketPlayer") && mc.getPlayer().getHurtTime() >= hurtTime.getValue().intValue()) {
                        event.cancel();
                    }
                }
            }

        }
    }

}
