package wtf.agent.client.util.math;

import java.util.Random;

public class MathUtils {

    private static final Random RNG = new Random();

    public static int random(int min, int max) {
        return RNG.nextInt((max + 1) - min) + min;
    }

    public static Random getRandom() {
        return RNG;
    }
}
