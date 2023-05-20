package wtf.agent.client.util.math;

import java.util.Random;

public class MathUtils {

    private static final Random RNG = new Random();

    public static Random getRandom() {
        return RNG;
    }
}
