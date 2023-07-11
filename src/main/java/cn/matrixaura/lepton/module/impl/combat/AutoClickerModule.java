package cn.matrixaura.lepton.module.impl.combat;

import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.input.EventMouseInput;
import cn.matrixaura.lepton.listener.events.player.EventUpdate;
import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.util.time.Timer;
import org.lwjgl.input.Mouse;

import static cn.matrixaura.lepton.util.math.MathUtils.random;

@ModuleInfo(name = "Auto Clicker", description = "Automatically clicks for you", category = Category.Combat)
public class AutoClickerModule extends Module {
    private final Timer timer = new Timer();
    private final Timer cps = new Timer();

    private int clicks;

    @Override
    public void onDisable() {
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
