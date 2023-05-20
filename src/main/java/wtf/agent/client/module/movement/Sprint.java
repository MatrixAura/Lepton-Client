package wtf.agent.client.module.movement;

import org.lwjgl.input.Keyboard;
import wtf.agent.client.listener.bus.Listener;
import wtf.agent.client.listener.events.player.EventUpdate;
import wtf.agent.client.module.Category;
import wtf.agent.client.module.Module;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Automatically sprints for you", Category.MOVEMENT);
        getBind().setKey(Keyboard.KEY_O);
    }

    @Listener
    public void onUpdate(EventUpdate event) {
        if (mc.getPlayer().isNull()) return;

        if (!mc.getPlayer().isSprinting()) mc.getPlayer().setSprinting(true);
    }
}
