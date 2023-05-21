package wtf.agent.client.module.movement;

import wtf.agent.client.listener.bus.Listener;
import wtf.agent.client.listener.events.player.EventUpdate;
import wtf.agent.client.module.Category;
import wtf.agent.client.module.Module;

import static org.lwjgl.input.Keyboard.KEY_N;

public class BridgeAssist extends Module {
    public BridgeAssist() {
        super("Bridge Assist", "Assists you at bridging", Category.MOVEMENT);

        getBind().setKey(KEY_N);
    }

    @Listener
    public void onUpdate(EventUpdate event) {

    }
}
