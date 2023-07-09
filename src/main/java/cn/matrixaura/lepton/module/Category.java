package cn.matrixaura.lepton.module;

public enum Category {

    Combat("gps_fixed"),
    Movement("directions_run"),
    Visual("remove_red_eye"),
    World("map");

    private String icon;

    Category(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

}
