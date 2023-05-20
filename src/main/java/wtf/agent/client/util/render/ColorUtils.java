package wtf.agent.client.util.render;

import java.awt.*;

public class ColorUtils {
    public static int rainbowCycle(int delay, double speed) {
        double hue = (((System.currentTimeMillis() + 10) + delay) * (speed / 100.0)) % 360.0;
        return Color.HSBtoRGB((float) hue / 360.0f, 1.0f, 1.0f);
    }
}
