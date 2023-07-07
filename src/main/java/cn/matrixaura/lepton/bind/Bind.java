package cn.matrixaura.lepton.bind;

import cn.matrixaura.lepton.util.trait.Nameable;
import cn.matrixaura.lepton.util.trait.Toggleable;

import java.util.function.Consumer;

/**
 * @author aesthetical
 * @since 04/27/23
 */
public class Bind implements Nameable, Toggleable {

    private final String name;

    private int key;

    private boolean state;
    private Consumer<Bind> action;

    public Bind(String name, Consumer<Bind> action, int key) {
        this.name = name;
        this.action = action;
        this.key = key;
    }

    @Override
    public void onEnable() {
        // empty method
    }

    @Override
    public void onDisable() {
        // empty method
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setAction(Consumer<Bind> action) {
        this.action = action;
    }

    @Override
    public boolean isToggled() {
        return state;
    }

    @Override
    public void setState(boolean state) {
        this.state = state;
        if (action != null) {
            action.accept(this);
        }
    }

    public void setValue(boolean state) {
        this.state = state;
    }

    @Override
    public String getName() {
        return name;
    }
}
