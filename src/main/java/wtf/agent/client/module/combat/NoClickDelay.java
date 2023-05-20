package wtf.agent.client.module.combat;

import wtf.agent.client.listener.bus.Listener;
import wtf.agent.client.listener.events.player.EventUpdate;
import wtf.agent.client.module.Category;
import wtf.agent.client.module.Module;

public class NoClickDelay extends Module {
    public NoClickDelay() {
        super("No Click Delay", "Stops left click delay", Category.COMBAT);
        setState(true);
    }

    @Listener
    public void onUpdate(EventUpdate event) {
        mc.setLeftClickCounter(0);
    }
}
