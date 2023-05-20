package wtf.agent.client.module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wtf.agent.client.module.visual.HUD;

import java.util.HashMap;
import java.util.Map;

public class ModuleManager {

    private static final Logger logger = LogManager.getLogger("Modules");
    private final Map<Class<? extends Module>, Module> classModuleMap = new HashMap<>();

    public ModuleManager() {

        classModuleMap.put(HUD.class, new HUD());

        logger.info("Loaded {} modules", classModuleMap.size());
    }

    public <T extends Module> T get(Class<T> clazz) {
        return (T) classModuleMap.getOrDefault(clazz, null);
    }
}
