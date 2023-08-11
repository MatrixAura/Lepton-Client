package cn.matrixaura.lepton.listener.events.render;

import cn.matrixaura.lepton.listener.bus.Event;

public class EventRender3D extends Event {
    private final float partialTicks;

    public EventRender3D(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
