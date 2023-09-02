package cn.matrixaura.lepton.module;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.packet.EventPacket;
import cn.matrixaura.lepton.util.packet.BlinkUtils;
import cn.matrixaura.lepton.util.player.Rotation;

public class Core {

    private static Rotation serverSideRotations;

    public static Rotation getRotations() {
        return serverSideRotations;
    }

    public static void setRotations(Rotation rotation) {
        serverSideRotations = rotation;
    }

    @Listener
    public void onPacket(EventPacket event) {
        if (BlinkUtils.isBlinking()) {
            BlinkUtils.blockPacket(event.getPacket());
            event.cancel();
        }
    }

}
