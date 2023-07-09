package cn.matrixaura.lepton.module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ModuleManager {

    private static final Logger logger = LogManager.getLogger("Modules");
    private final Map<Class<? extends Module>, Module> classModuleMap = new HashMap<>();

    public ModuleManager() {

        Reflections reflections = new Reflections("cn.matrixaura.lepton.module");

        Set<Class<? extends Module>> classes = reflections.getSubTypesOf(Module.class);

        for (Class<?> clazz : classes) {
            try {
                Module feature = (Module) clazz.newInstance();
                classModuleMap.put(feature.getClass(), feature);
            } catch (InstantiationException | IllegalAccessException e) {
                logger.info("Failed to load module {}", e.getStackTrace()[0]);
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
