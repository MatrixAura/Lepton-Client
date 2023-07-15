package cn.matrixaura.lepton.inject.wrapper.impl.other;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;

public class TimerWrapper extends Wrapper {
    private final Object timerObj;

    public TimerWrapper(Object obj) {
        super("net/minecraft/util/Timer");
        timerObj = obj;
    }

    public float getTimerSpeed() {
        return (Float) ReflectionUtils.getFieldValue(timerObj, Mappings.getObfField("field_74278_d"));
    }

    public void setTimerSpeed(Float speed) {
        ReflectionUtils.setFieldValue(timerObj, Mappings.getObfField("field_74278_d"), speed);
    }

}
