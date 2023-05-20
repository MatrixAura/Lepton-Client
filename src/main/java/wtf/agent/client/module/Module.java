package wtf.agent.client.module;

import wtf.agent.client.Agent;
import wtf.agent.client.bind.Bind;
import wtf.agent.client.bind.BindDevice;
import wtf.agent.client.util.trait.Nameable;
import wtf.agent.client.util.trait.Toggleable;
import wtf.agent.inject.asm.api.Transformers;
import wtf.agent.inject.asm.wrapper.impl.MinecraftWrapper;

public class Module implements Nameable, Toggleable {
    protected static final MinecraftWrapper mc = MinecraftWrapper.get();

    private final String name, description;
    private final Category category;

    private final Bind bind;

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;

        bind = new Bind(name, (x) -> {
            if (x.isToggled()) {
                onEnable();
            } else {
                onDisable();
            }
        }, BindDevice.KEYBOARD, -1);
        Agent.getInstance().getBinds().addBind(bind);
    }

    @Override
    public void onEnable() {
        Agent.getBus().subscribe(this);
    }

    @Override
    public void onDisable() {
        Agent.getBus().unsubscribe(this);
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

    @Override
    public boolean isToggled() {
        return bind.isToggled();
    }

    @Override
    public void setState(boolean state) {
        bind.setState(state);
    }
}
