package cn.matrixaura.lepton.module;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.bind.Bind;
import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.setting.Setting;
import cn.matrixaura.lepton.setting.settings.BooleanSetting;
import cn.matrixaura.lepton.setting.settings.EnumSetting;
import cn.matrixaura.lepton.setting.settings.NumberSetting;
import cn.matrixaura.lepton.setting.settings.StringSetting;
import cn.matrixaura.lepton.util.trait.Nameable;
import cn.matrixaura.lepton.util.trait.Toggleable;

import java.util.ArrayList;
import java.util.List;

public class Module implements Nameable, Toggleable {
    protected static final MinecraftWrapper mc = MinecraftWrapper.get();

    private final String name, description;
    private final Category category;
    private final Bind bind;
    private final List<Setting<?>> settings = new ArrayList<>();

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

        bind = new Bind(name, (x) -> {
            if (canToggle) {
                if (x.isToggled()) {
                    onEnable();
                    Lepton.getBus().subscribe(this);
                } else {
                    Lepton.getBus().unsubscribe(this);
                    onDisable();
                }
            }
        }, info.key());
        bind.setState(info.enabled());
        Lepton.INSTANCE.getBindManager().addBind(bind);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public Bind getBind() {
        return bind;
    }

    public List<Setting<?>> getSettings() {
        return settings;
    }

    @Override
    public boolean isToggled() {
        return bind.isToggled();
    }

    @Override
    public void setState(boolean state) {
        bind.setState(state);
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

    public <E extends Enum<E>> Setting<E> setting(String name, E defaultValue) {
        EnumSetting<E> setting = new EnumSetting<>(name, defaultValue);
        settings.add(setting);
        return setting;
    }

    public Setting<String> setting(String name, String defaultValue) {
        StringSetting setting = new StringSetting(name, defaultValue);
        settings.add(setting);
        return setting;
    }
}
