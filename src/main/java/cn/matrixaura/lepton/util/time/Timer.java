package cn.matrixaura.lepton.util.time;

public class Timer {
    private long lastTime;

    public Timer() {
        resetTime();
    }

    public boolean hasPassed(long time, boolean reset) {
        boolean passed = getPassedTimeMs() >= time;
        if (passed && reset) {
            resetTime();
        }

        return passed;
    }

    public boolean hasPassed(long time) {
        return hasPassed(time, false);
    }

    public void resetTime() {
        lastTime = System.nanoTime();
    }

    public long getPassedTimeMs() {
        return getPassedTime() / 1000000L;
    }

    public long getPassedTime() {
        return System.nanoTime() - lastTime;
    }

}
