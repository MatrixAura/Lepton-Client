package cn.matrixaura.lepton.listener.events.input;

import cn.matrixaura.lepton.listener.bus.Event;

public class EventMouseInput extends Event {
    private final int button;

    public EventMouseInput(int button) {
        this.button = button;
    }

    public int getButton() {
        return button;
    }
}
