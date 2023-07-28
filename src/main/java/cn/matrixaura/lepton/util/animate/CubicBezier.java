package cn.matrixaura.lepton.util.animate;

public class CubicBezier extends Easing {

    private final double p0, p1, p2, p3;

    public CubicBezier(double p0, double p1, double p2, double p3) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public double solve(double process) {
        double inverseT = 1 - process;
        return Math.pow(inverseT, 3) * p0 +
                        3 * Math.pow(inverseT, 2) * process * p1 +
                        3 * inverseT * Math.pow(process, 2) * p2 +
                        Math.pow(process, 3) * p3;
    }

}
