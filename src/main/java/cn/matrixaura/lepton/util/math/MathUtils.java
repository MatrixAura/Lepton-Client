package cn.matrixaura.lepton.util.math;

import java.util.Random;

public class MathUtils {

    private static final Random RNG = new Random();

    public static int random(int min, int max) {
        return RNG.nextInt((max + 1) - min) + min;
    }

    public static Random getRandom() {
        return RNG;
    }

    public static float gaussianValue(float x, float sigma) {
        double PI = 3.141592653;
        double output = 1.0 / Math.sqrt(2.0 * PI * (sigma * sigma));
        return (float) (output * Math.exp(-(x * x) / (2.0 * (sigma * sigma))));
    }
}
