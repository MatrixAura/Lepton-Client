package wtf.agent.inject.mixin.wrapper;

import wtf.agent.inject.mixin.mapping.Mappings;

public class Wrapper {

    private final String name, obfName;
    private Class<?> clazz;

    public Wrapper(String name) {
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
