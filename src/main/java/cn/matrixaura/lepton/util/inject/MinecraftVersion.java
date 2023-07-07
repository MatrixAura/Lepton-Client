package cn.matrixaura.lepton.util.inject;

public enum MinecraftVersion {
    VER_1_7_10("1.7.10"),
    VER_1_8_9("1.8.9");

    private final String dir;

    MinecraftVersion(String dir) {
        this.dir = dir;
    }

    public String getDir() {
        return dir;
    }

    public String getSrg() {
        return "/assets/lepton/client/mappings/" + dir + "/joined.srg";
    }

    public String getFields() {
        return "/assets/lepton/client/mappings/" + dir + "/fields.csv";
    }

    public String getMethods() {
        return "/assets/lepton/client/mappings/" + dir + "/methods.csv";
    }
}
