package cn.matrixaura.lepton.util.animate;

@SuppressWarnings("unused")
public class EasingCurves {

    public static Easing easeNaturel = new CubicBezier(0.25, 0.1, 0.25, 1);

    public static Easing easeInSine = new CubicBezier(0.12, 0, 0.39, 0);
    public static Easing easeOutSine = new CubicBezier(0.61, 1, 0.88, 1);
    public static Easing easeInOutSine = new CubicBezier(0.37, 0, 0.63, 1);

    public static Easing easeInExpo = new CubicBezier(0.7, 0, 0.84, 0);
    public static Easing easeOutExpo = new CubicBezier(0.16, 1, 0.3, 1);
    public static Easing easeInOutExpo = new CubicBezier(0.87, 0, 0.13, 1);

    public static Easing easeInBack = new CubicBezier(0.36, 0, 0.66, -0.56);
    public static Easing easeOutBack = new CubicBezier(0.34, 1.56, 0.64, 1);
    public static Easing easeInOutBack = new CubicBezier(0.68, -0.6, 0.32, 1.6);

    public static Easing easeInQuad = new CubicBezier(0.11, 0, 0.5, 0);
    public static Easing easeOutQuad = new CubicBezier(0.5, 1, 0.89, 1);
    public static Easing easeInOutQuad = new CubicBezier(0.45, 0, 0.55, 1);

    public static Easing easeInCubic = new CubicBezier(0.32, 0, 0.67, 0);
    public static Easing easeOutCubic = new CubicBezier(0.33, 1, 0.68, 1);
    public static Easing easeInOutCubic = new CubicBezier(0.65, 0, 0.35, 1);

    public static Easing easeInQuart = new CubicBezier(0.5, 0, 0.75, 0);
    public static Easing easeOutQuart = new CubicBezier(0.25, 1, 0.5, 1);
    public static Easing easeInOutQuart = new CubicBezier(0.76, 0, 0.24, 1);

    public static Easing easeInQuint = new CubicBezier(0.64, 0, 0.78, 0);
    public static Easing easeOutQuint = new CubicBezier(0.22, 1, 0.36, 1);
    public static Easing easeInOutQuint = new CubicBezier(0.83, 0, 0.17, 1);

    public static Easing easeInCirc = new CubicBezier(0.55, 0, 1, 0.45);
    public static Easing easeOutCirc = new CubicBezier(0, 0.55, 0.45, 1);
    public static Easing easeInOutCirc = new CubicBezier(0.85, 0, 0.15, 1);

    public static Easing easeInElastic = new Easing() {
        @Override
        public double solve(double process) {
            return -Math.pow(2, 10 * process - 10) * Math.sin((process * 10 - 10.75) * (2 * Math.PI) / 3);
        }
    };
    public static Easing easeOutElastic = new Easing() {
        @Override
        public double solve(double process) {
            return Math.pow(2, -10 * process) * Math.sin((process * 10 - 0.75) * (2 * Math.PI) / 3) + 1;
        }
    };
    public static Easing easeInOutElastic = new Easing() {
        @Override
        public double solve(double process) {
            double sin = Math.sin((20 * process - 11.125) * (2 * Math.PI) / 4.5);
            return process < 0.5 ? -(Math.pow(2, 20 * process - 10) * sin) / 2
                    : (Math.pow(2, -20 * process + 10) * sin) / 2 + 1;
        }
    };

    public static Easing easeInBounce = new Easing() {
        @Override
        public double solve(double process) {
            return 1 - easeOutBounce.solve(1 - process);
        }
    };
    public static Easing easeOutBounce = new Easing() {
        @Override
        public double solve(double process) {
            if (process < 1 / 2.75) {
                return 7.5625 * process * process;
            } else if (process < 2 / 2.75) {
                return 7.5625 * (process -= 1.5 / 2.75) * process + 0.75;
            } else if (process < 2.5 / 2.75) {
                return 7.5625 * (process -= 2.25 / 2.75) * process + 0.9375;
            } else {
                return 7.5625 * (process -= 2.625 / 2.75) * process + 0.984375;
            }
        }
    };
    public static Easing easeInOutBounce = new Easing() {
        @Override
        public double solve(double process) {
            return process < 0.5 ? (1 - easeOutBounce.solve(1 - 2 * process)) / 2 : (1 + easeOutBounce.solve(2 * process - 1)) / 2;
        }
    };

}
/*
public class LegitSpeed extends Module {

    @Subscribe
    public void onMoveInput(MoveInputEvent e) {

    }
}
 */