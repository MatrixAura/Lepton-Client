package wtf.agent.client;

import wtf.agent.client.listener.bus.EventBus;

public class Agent {

    private static final EventBus bus = new EventBus();

    public static void init() {

    }

    public static EventBus getBus() {
        return bus;
    }
}
