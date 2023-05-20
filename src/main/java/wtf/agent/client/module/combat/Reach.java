package wtf.agent.client.module.combat;

import wtf.agent.client.listener.bus.Listener;
import wtf.agent.client.listener.events.input.EventAttackReach;
import wtf.agent.client.module.Category;
import wtf.agent.client.module.Module;
import wtf.agent.inject.asm.api.Transformers;

public class Reach extends Module {
    public Reach() {
        super("Reach", "Reaches further than normal", Category.COMBAT);
        setState(true);
    }

    @Listener
    public void onAttackReach(EventAttackReach event) {

        Transformers.logger.info("got it");
        // TODO settings
        event.setReach(6.0);
    }
}
