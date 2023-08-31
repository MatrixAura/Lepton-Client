package cn.matrixaura.lepton.module.impl.other.anticrash;

public abstract class CrashCheck {

    private final String name;

    public CrashCheck(String name) {
        this.name = name;
    }

    public abstract boolean handle(Object packet);

    public String getName() {
        return name;
    }

}
