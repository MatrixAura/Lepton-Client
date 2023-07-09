package cn.matrixaura.lepton.setting.settings;

import cn.matrixaura.lepton.setting.Setting;

public class ModeSetting extends Setting<String> {

    private final String[] values;

    public ModeSetting(String name, String defaultValue, String... values) {
        super(name, defaultValue);
        this.values = values;
    }

    public String[] getValues() {
        return values;
    }
}
