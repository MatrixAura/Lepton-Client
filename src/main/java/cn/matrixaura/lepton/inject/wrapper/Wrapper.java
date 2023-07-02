package cn.matrixaura.lepton.inject.wrapper;

import cn.matrixaura.lepton.util.inject.Mappings;

public class Wrapper {

    private final String name, obfName;
    private Class<?> clazz;

    public Wrapper(String name) {
        this.name = name;

        obfName = Mappings.getObfClass(name);
        if (obfName != null) {
            try {
                clazz = Class.forName(obfName);
            } catch (Exception ignored) {

            }
        }
    }

    public String getName() {
        return name;
    }

    public String getObfName() {
        return obfName;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
