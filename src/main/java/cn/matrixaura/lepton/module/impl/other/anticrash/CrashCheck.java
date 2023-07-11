package cn.matrixaura.lepton.module.impl.other.anticrash;

public abstract class CrashCheck {

    private final String name, message;

    public CrashCheck(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public abstract boolean handle(Object packet);

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

}
