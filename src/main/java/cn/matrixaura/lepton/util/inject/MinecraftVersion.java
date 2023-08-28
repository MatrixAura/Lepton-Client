package cn.matrixaura.lepton.util.inject;

public enum MinecraftVersion {
    VER_1710("1.7.10"),
    VER_189("1.8.9");

    private final String ver;

    MinecraftVersion(String ver) {
        this.ver = ver;
    }

    public String getVer() {
        return ver;
    }

}
