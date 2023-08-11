package cn.matrixaura.lepton.listener.events.input;

import cn.matrixaura.lepton.listener.bus.Event;

public class EventKeyInput extends Event {
    private final int keyCode;

    public EventKeyInput(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
