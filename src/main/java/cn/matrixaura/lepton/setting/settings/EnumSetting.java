package cn.matrixaura.lepton.setting.settings;

import cn.matrixaura.lepton.setting.Setting;

public class EnumSetting<E extends Enum<E>> extends Setting<E> {

    public EnumSetting(String name, E defaultValue) {
        super(name, defaultValue);
    }

    public void setByName(String name) {
        for (E value : value.getDeclaringClass().getEnumConstants()) {
            if (value.name().equals(name)) this.value = value;
        }
    }

    public void forwardLoop() {
        if (value.ordinal() == value.getDeclaringClass().getEnumConstants().length - 1) {
            value = value.getDeclaringClass().getEnumConstants()[0];
        } else value = value.getDeclaringClass().getEnumConstants()[value.ordinal() + 1];
    }

    public void backwardLoop() {
        if (value.ordinal() == 0) {
            value = value.getDeclaringClass().getEnumConstants()[value.getDeclaringClass().getEnumConstants().length - 1];
        } else value = value.getDeclaringClass().getEnumConstants()[value.ordinal() - 1];
    }

}