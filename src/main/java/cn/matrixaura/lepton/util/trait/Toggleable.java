package cn.matrixaura.lepton.util.trait;

public interface Toggleable {

    void onEnable();

    void onDisable();

    boolean isToggled();

    void setState(boolean state);
}
