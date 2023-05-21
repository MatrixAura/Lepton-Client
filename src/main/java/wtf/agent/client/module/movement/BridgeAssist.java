package wtf.agent.client.module.movement;

import wtf.agent.client.listener.bus.Listener;
import wtf.agent.client.listener.events.player.EventUpdate;
import wtf.agent.client.module.Category;
import wtf.agent.client.module.Module;
import wtf.agent.inject.asm.wrapper.impl.HitResultWrapper.Type;

import static org.lwjgl.input.Keyboard.KEY_N;

public class BridgeAssist extends Module {
    private boolean sneakOverride;

    public BridgeAssist() {
        super("Bridge Assist", "Assists you at bridging", Category.MOVEMENT);

        getBind().setKey(KEY_N);
    }

    @Override
    public void onDisable() {
        super.onDisable();

        if (sneakOverride) {
            mc.getGameSettings().getKey("key.sneak").setPressed(false);
        }

        sneakOverride = false;
    }

    @Listener
    public void onUpdate(EventUpdate event) {
        if (mc.getHitResult().getType() == Type.BLOCK) {
            sneakOverride = mc.getWorld().isAir(mc.getPlayer().getPos().add(0, -1, 0));
            mc.getGameSettings().getKey("key.sneak").setPressed(sneakOverride);

            mc.rightClickMouse();
        } else {
            if (sneakOverride) {
                sneakOverride = false;
                mc.getGameSettings().getKey("key.sneak").setPressed(false);
            }
        }
    }
}
