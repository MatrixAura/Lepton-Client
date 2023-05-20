package wtf.agent.inject.mixin.api;

import wtf.agent.inject.mixin.mapping.Mappings;

public class Mixin {

    private final String name, obfName;

    private Class<?> clazz;

    public Mixin(String name) {
        this.name = name;

        obfName = Mappings.getUnobfClass(name);
        if (obfName != null) {
            try {
                clazz = Class.forName(obfName);
            } catch (Exception e) {

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
