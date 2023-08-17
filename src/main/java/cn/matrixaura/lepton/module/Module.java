package cn.matrixaura.lepton.module;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.bind.KeyBind;
import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.setting.settings.BooleanSetting;
import cn.matrixaura.lepton.setting.settings.ModeSetting;
import cn.matrixaura.lepton.setting.settings.NumberSetting;
import cn.matrixaura.lepton.setting.settings.StringSetting;

import java.util.ArrayList;
import java.util.List;

public class Module {
    protected static final MinecraftWrapper mc = MinecraftWrapper.get();

    private final String name, description;
    private final Category category;
    private final KeyBind bind;
    private final List<Setting<?>> settings = new ArrayList<>();

    private boolean state = false;

    private final boolean canToggle;

    public Module() {
        ModuleInfo info = this.getClass().getAnnotation(ModuleInfo.class);

        if (info == null) {
            throw new RuntimeException(this.getClass().getName() + " is not annotated with @ModuleInfo");
        }

        this.name = info.name();
        this.description = info.description();
        this.category = info.category();
        this.canToggle = info.canToggle();

        bind = new KeyBind(
                info.key(),
                this::toggle
        );
        if (info.enabled()) switchState();
        Lepton.INSTANCE.getBindManager().addBind(bind);
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public boolean canToggle() {
        return canToggle;
    }

    public void toggle() {
        if (canToggle) {
            switchState();
        }
    }

    private void switchState() {
        state = !state;
        if (!state) {
            Lepton.getEventBus().unsubscribe(this);
            onDisable();
        } else {
            onEnable();
            Lepton.getEventBus().subscribe(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public KeyBind getBind() {
        return bind;
    }

    public List<Setting<?>> getSettings() {
        return settings;
    }

    public boolean isToggled() {
        return state;
    }

    public void setState(boolean state) {
        if (state != this.state) toggle();
    }

    public Setting<Boolean> setting(String name, boolean defaultValue) {
        BooleanSetting setting = new BooleanSetting(name, defaultValue);
        settings.add(setting);
        return setting;
    }

    public Setting<Number> setting(String name, Number defaultValue, Number minValue, Number maxValue, Number inc) {
        NumberSetting setting = new NumberSetting(name, defaultValue, minValue, maxValue, inc);
        settings.add(setting);
        return setting;
    }

    public Setting<String> setting(String name, String defaultValue) {
        StringSetting setting = new StringSetting(name, defaultValue);
        settings.add(setting);
        return setting;
    }

    public Setting<String> setting(String name, String defaultValue, String... values) {
        ModeSetting setting = new ModeSetting(name, defaultValue, values);
        settings.add(setting);
        return setting;
    }
}
