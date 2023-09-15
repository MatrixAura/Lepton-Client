package cn.matrixaura.lepton.module.impl.combat;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.packet.EventPacket;
import cn.matrixaura.lepton.listener.events.player.EventUpdate;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import cn.matrixaura.lepton.util.packet.PacketUtils;

@ModuleInfo(name = "Velocity", description = "Reduces your velocity", category = Category.Combat)
public class VelocityModule extends Module {

    public Setting<String> mode = setting("Mode", "Cancel", "Cancel", "Old GrimAC", "Cancel C00", "Intave Freeze", "Latest GrimAC");
    private int cancelPackets;

    @Override
    public void onEnable() {
        cancelPackets = 0;
    }

    @Listener
    public void onUpdate(EventUpdate event) {
        switch (mode.getValue()) {
            case "Intave Freeze": {
                if (mc.getPlayer().getHurtTime() > 7 && mc.getPlayer().isOnGround()) {
                    mc.getPlayer().setMotionX(0);
                    mc.getPlayer().setMotionY(0);
                    mc.getPlayer().setMotionZ(0);
                }
            }
        }
    }

    @Listener
    public void onPacket(EventPacket event) {
        if (PacketUtils.isPacketInstanceof(event.getPacket(), "S12PacketEntityVelocity") &&
                (Integer) ReflectionUtils.getFieldValue(event.getPacket(), Mappings.getObfField("field_149417_a")) == mc.getPlayer().getEntityID()) {
            switch (mode.getValue()) {
                case "Cancel": {
                    event.cancel();
                    break;
                }
                case "Cancel C00":
                case "Old GrimAC": {
                    cancelPackets = 6;
                    event.cancel();
                    break;
                }
                case "Latest GrimAC": {
                    event.cancel();
                    Object diggingPacket;
                    try {
                        Class<?> action = Class.forName("net/minecraft/network/play/client/C07PacketPlayerDigging$Action");
                        Class<?> ef = Class.forName("net/minecraft/util/EnumFacing");
                        diggingPacket = ReflectionUtils.newInstance(
                                Class.forName(Mappings.getObfClass("net/minecraft/network/play/client/C07PacketPlayerDigging")),
                                new Class[]{action, Class.forName("net/minecraft/util/BlockPos"), ef},
                                ReflectionUtils.getFieldValue(action, Mappings.getObfField("net/minecraft/network/play/client/C07PacketPlayerDigging$Action/STOP_DESTROY_BLOCK")),
                                mc.getPlayer().getPosObj(),
                                ReflectionUtils.getFieldValue(ef, Mappings.getObfField("net/minecraft/util/EnumFacing/UP"))
                        );
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    mc.getNetHandler().addToSendQueue(diggingPacket);
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
                case "Old GrimAC": {
                    if (PacketUtils.isPacketInstanceof(event.getPacket(), "C0FPacketConfirmTransaction")) {
                        cancelPackets--;
                        event.cancel();
                    }
                }
            }
        }
        if (mode.is("Intave Freeze") && mc.getPlayer().getHurtTime() > 7 && mc.getPlayer().isOnGround()) {
            if (PacketUtils.isPacketInstanceof(event.getPacket(), "C06PacketPlayerPosLook")) {
                event.cancel();
                try {
                    mc.getNetHandler().addToSendQueue(ReflectionUtils.newInstance(
                            Class.forName(Mappings.getObfClass("net/minecraft/network/play/client/C03PacketPlayer$C05PacketPlayerLook")),
                            new Class[]{float.class, float.class, boolean.class},
                            ReflectionUtils.invokeMethod(event.getPacket(), Mappings.getObfMethod("func_149462_g")),
                            ReflectionUtils.invokeMethod(event.getPacket(), Mappings.getObfMethod("func_149470_h")),
                            ReflectionUtils.invokeMethod(event.getPacket(), Mappings.getObfMethod("func_149465_i"))
                    ));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else if (PacketUtils.isPacketInstanceof(event.getPacket(), "C04PacketPlayerPosition")) {
                event.cancel();
                try {
                    mc.getNetHandler().addToSendQueue(ReflectionUtils.newInstance(
                            Class.forName(Mappings.getObfClass("net/minecraft/network/play/client/C03PacketPlayer")),
                            new Class[]{boolean.class},
                            ReflectionUtils.invokeMethod(event.getPacket(), Mappings.getObfMethod("func_149465_i"))
                    ));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
