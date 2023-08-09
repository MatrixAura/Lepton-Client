package cn.matrixaura.lepton.protect;

public abstract class Protection {

    private final String name;

    public Protection(String name) {
        this.name = name;
    }

    public abstract boolean verify();

    public String getName() {
        return this.name;
    }

}
