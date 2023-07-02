package cn.matrixaura.lepton.module;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.bind.Bind;
import cn.matrixaura.lepton.bind.BindDevice;
import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.util.trait.Nameable;
import cn.matrixaura.lepton.util.trait.Toggleable;

public class Module implements Nameable, Toggleable {
    protected static final MinecraftWrapper mc = MinecraftWrapper.get();

    private final String name, description;
    private final Category category;
    private final boolean canToggle;

    private final Bind bind;

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
        }, BindDevice.KEYBOARD, info.key());
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

    public boolean canSeen() {
        return getCategory() != Category.HIDDEN;
    }

    public Bind getBind() {
        return bind;
    }

    @Override
    public boolean isToggled() {
        return bind.isToggled();
    }

    @Override
    public void setState(boolean state) {
        bind.setState(state);
    }
}
