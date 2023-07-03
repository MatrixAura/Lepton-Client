package cn.matrixaura.lepton.setting.settings;

import cn.matrixaura.lepton.setting.Setting;

public class NumberSetting extends Setting<Number> {

    private final Number min;
    private final Number max;
    private final Number inc;

    public NumberSetting(String name, Number defaultValue, Number min, Number max, Number inc) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
        this.inc = inc;
    }

    public Number getMin() {
        return min;
    }

    public Number getMax() {
        return max;
    }

    public Number getInc() {
        return inc;
    }

    public boolean isInRange(Number valueIn) {
        return valueIn.doubleValue() <= max.doubleValue() && valueIn.doubleValue() >= min.doubleValue();
    }

}
