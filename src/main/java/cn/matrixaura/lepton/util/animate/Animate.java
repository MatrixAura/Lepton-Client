package cn.matrixaura.lepton.util.animate;

import cn.matrixaura.lepton.util.time.Timer;

@SuppressWarnings("unused")
public class Animate {

    private final Easing easing;
    private double start, end, time;
    private final Timer timer = new Timer();

    public Animate(Easing easing, double start, double end, double time) {
        this.easing = easing;
        this.start = start;
        this.end = end;
        this.time = time;
    }


    public void setStart(double start) {
        this.start = start;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void reset() {
        timer.resetTime();
    }

    public double getOutput() {
        if (timer.hasPassed((long) time, true)) return end;
        return start + (start - end) * easing.solve(timer.getPassedTime() / time);
    }

}
