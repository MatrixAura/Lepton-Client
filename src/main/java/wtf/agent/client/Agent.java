package wtf.agent.client;

import org.lwjgl.input.Keyboard;
import wtf.agent.inject.mixin.api.Mixins;

public class Agent {

    public static void init() {

    }

    public static void keyPress(int k) {
        Mixins.logger.info("Pressed key " + Keyboard.getKeyName(k));
    }
}
