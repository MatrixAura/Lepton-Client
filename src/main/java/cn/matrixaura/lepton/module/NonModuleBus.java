package cn.matrixaura.lepton.module;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.packet.EventPacket;
import cn.matrixaura.lepton.listener.events.render.EventRender2D;
import cn.matrixaura.lepton.uiengines.LAIT.LAIT;
import cn.matrixaura.lepton.util.packet.BlinkUtils;
import cn.matrixaura.lepton.util.player.Rotation;

import java.util.LinkedList;

public class NonModuleBus {

    private static Rotation serverSideRotations;
    public static LinkedList<LAIT> renderList = new LinkedList<>();

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

    @Listener
    public void onRender2D(EventRender2D event) {
        renderList.forEach(LAIT::render);
    }

}
