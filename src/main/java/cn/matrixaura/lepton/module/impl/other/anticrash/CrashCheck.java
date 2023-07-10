package cn.matrixaura.lepton.module.impl.other.anticrash;

public abstract class CrashCheck {

    private final String name, desc;

    public CrashCheck(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public abstract boolean handle(Object packet);

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

}
