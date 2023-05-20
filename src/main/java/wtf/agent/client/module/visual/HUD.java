package wtf.agent.client.module.visual;

import org.lwjgl.input.Keyboard;
import wtf.agent.BuildConfig;
import wtf.agent.client.Agent;
import wtf.agent.client.listener.bus.Listener;
import wtf.agent.client.listener.events.render.EventRender2D;
import wtf.agent.client.module.Category;
import wtf.agent.client.module.Module;

public class HUD extends Module {
    public HUD() {
        super("HUD", "Draws a HUD over the vanilla HUD", Category.VISUAL);
        getBind().setKey(Keyboard.KEY_RCONTROL);
    }

    @Listener
    public void onRender2D(EventRender2D event) {
        mc.getFontRenderer().drawString("Agent " + BuildConfig.VERSION, 2, 2, -1);

        int y = 2;
        for (Module module : Agent.getInstance().getModules().get()) {
            if (!module.isToggled()) continue;

            mc.getFontRenderer().drawString(module.getName(), event.getWidth() - 2 - mc.getFontRenderer().getStringWidth(module.getName()), y, -1);
            y += mc.getFontRenderer().getHeight() + 2;
        }
    }
}
