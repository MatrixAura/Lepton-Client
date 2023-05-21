package wtf.agent.client.module.combat;

import wtf.agent.client.listener.bus.Listener;
import wtf.agent.client.listener.events.input.EventAttackReach;
import wtf.agent.client.module.Category;
import wtf.agent.client.module.Module;

import static org.lwjgl.input.Keyboard.KEY_R;

public class Reach extends Module {
    public Reach() {
        super("Reach", "Reaches further than normal", Category.COMBAT);
        getBind().setKey(KEY_R);
    }

    @Listener
    public void onAttackReach(EventAttackReach event) {
        // TODO settings
        event.setReach(3.2 + (Math.random() / 100.0));
    }
}
