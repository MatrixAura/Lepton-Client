package wtf.agent.client.module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wtf.agent.client.module.combat.AutoClicker;
import wtf.agent.client.module.combat.NoClickDelay;
import wtf.agent.client.module.combat.Reach;
import wtf.agent.client.module.movement.BridgeAssist;
import wtf.agent.client.module.movement.Sprint;
import wtf.agent.client.module.visual.HUD;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ModuleManager {

    private static final Logger logger = LogManager.getLogger("Modules");
    private final Map<Class<? extends Module>, Module> classModuleMap = new HashMap<>();

    public ModuleManager() {

        classModuleMap.put(AutoClicker.class, new AutoClicker());
        classModuleMap.put(NoClickDelay.class, new NoClickDelay());
        classModuleMap.put(Reach.class, new Reach());

        classModuleMap.put(BridgeAssist.class, new BridgeAssist());
        classModuleMap.put(Sprint.class, new Sprint());

        classModuleMap.put(HUD.class, new HUD());

        logger.info("Loaded {} modules", classModuleMap.size());
    }

    public <T extends Module> T get(Class<T> clazz) {
        return (T) classModuleMap.getOrDefault(clazz, null);
    }

    public Collection<Module> get() {
        return classModuleMap.values();
    }
}
