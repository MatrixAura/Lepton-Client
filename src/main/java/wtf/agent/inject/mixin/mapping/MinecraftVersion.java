package wtf.agent.inject.mixin.mapping;

public enum MinecraftVersion {
    VER_1_8("1.8"),
    VER_1_8_8("1.8.8"),
    VER_1_8_9("1.8.9");

    private final String dir;

    MinecraftVersion(String dir) {
        this.dir = dir;
    }

    public String getDir() {
        return dir;
    }

    public String getSrg() {
        return "/mappings/" + dir + "/joined.srg";
    }

    public String getFields() {
        return "/mappings/" + dir + "/fields.csv";
    }

    public String getMethods() {
        return "/mappings/" + dir + "/methods.csv";
    }
}
