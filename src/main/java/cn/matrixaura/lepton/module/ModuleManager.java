package cn.matrixaura.lepton.module;

import cn.matrixaura.lepton.util.inject.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ModuleManager {

    private static final Logger logger = LogManager.getLogger("Modules");
    private final Map<Class<? extends Module>, Module> classModuleMap = new HashMap<>();

    public ModuleManager() {
        for (Class<?> clazz : ObjectUtils.getSubTypesOf("cn.matrixaura.lepton.module.impl", Module.class)) {
            try {
                Module feature = (Module) clazz.newInstance();
                classModuleMap.put(feature.getClass(), feature);
            } catch (InstantiationException | IllegalAccessException | RuntimeException e) {
                logger.error("Failed to load module {}", e.getStackTrace()[0]);
            }
        }

        logger.info("Loaded {} modules", classModuleMap.size());
    }

    public <T extends Module> T get(Class<T> clazz) {
        return (T) classModuleMap.getOrDefault(clazz, null);
    }

    public Collection<Module> get() {
        return classModuleMap.values();
    }

    public <T extends Module> T get(String name) {
        if (classModuleMap.values().stream().anyMatch(it -> it.getName().equals(name)))
            return (T) classModuleMap.values().stream()
                    .filter(it -> it.getName().equals(name))
                    .findAny()
                    .get();
        return null;
    }
}
