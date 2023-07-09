package cn.matrixaura.lepton.module;

public enum Category {

    Combat("gps_fixed"),
    Movement("directions_run"),
    Visual("camera"),
    World("map");

    private final String icon;

    Category(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

}
