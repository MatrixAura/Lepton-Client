package cn.matrixaura.lepton.util.inject;

public enum MinecraftVersion {
    VER_1_7_10("1.7.10"),
    VER_1_8_9("1.8.9");

    private final String ver;

    MinecraftVersion(String ver) {
        this.ver = ver;
    }

    public String getVer() {
        return ver;
    }

    public String getSrg() {
        return "/assets/lepton/client/mappings/" + ver.replace(".", "_") + ".srg";
    }

}
