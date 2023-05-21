package wtf.agent.client.module.combat;

import org.lwjgl.input.Mouse;
import wtf.agent.client.bind.BindDevice;
import wtf.agent.client.listener.bus.Listener;
import wtf.agent.client.listener.events.input.EventMouseInput;
import wtf.agent.client.listener.events.player.EventUpdate;
import wtf.agent.client.module.Category;
import wtf.agent.client.module.Module;
import wtf.agent.client.util.time.Timer;

import static wtf.agent.client.util.math.MathUtils.random;

public class AutoClicker extends Module {
    private final Timer timer = new Timer();
    private final Timer cps = new Timer();

    private int clicks;

    public AutoClicker() {
        super("Auto Clicker", "Automatically clicks for you", Category.COMBAT);
        getBind().setKey(4);
        getBind().setDevice(BindDevice.MOUSE);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        clicks = 0;
    }

    @Listener
    public void onUpdate(EventUpdate event) {
        if (mc.getCurrentScreen() != null) return;

        if (timer.hasPassed(1000L / random(8, 14)) && Mouse.isButtonDown(0) && !Mouse.isButtonDown(1)) {

            if (cps.hasPassed(1000L, true)) {
                clicks = 0;
            }

            if (clicks > 20) return;

            mc.clickMouse();
            timer.resetTime();
        }
    }

    @Listener
    public void onMouseInput(EventMouseInput event) {
        if (event.getButton() == 0) ++clicks;
    }
}
