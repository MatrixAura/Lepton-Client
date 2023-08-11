package cn.matrixaura.lepton.listener.events.input;

import cn.matrixaura.lepton.listener.bus.Event;

public class EventAttackReach extends Event {
    private double reach;

    public EventAttackReach(double reach) {
        this.reach = reach;
    }

    public void setReach(double reach) {
        this.reach = reach;
    }

    public double getReach() {
        return reach;
    }
}
