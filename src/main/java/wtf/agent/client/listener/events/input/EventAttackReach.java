package wtf.agent.client.listener.events.input;

public class EventAttackReach {
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
