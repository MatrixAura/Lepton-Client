package cn.matrixaura.lepton.inject.asm.api;

import cn.matrixaura.lepton.util.inject.Mappings;

public class Transformer {

    private final String name, obfName;
    private Class<?> clazz;

    public Transformer(String name) {
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
