package cn.matrixaura.lepton.util.math;

import java.security.SecureRandom;

public class RandomUtils {

    public static double doubleRandom(double min, double max) {
        final SecureRandom rng = new SecureRandom();
        return rng.nextDouble() * (max - min) + min;
    }

    public static float floatRandom(float min, float max) {
        final SecureRandom rng = new SecureRandom();
        return rng.nextFloat() * (max - min) + min;
    }

    public static int intRandom(int min, int max) {
        final SecureRandom rng = new SecureRandom();
        return rng.nextInt() * (max - min) + min;
    }

}
