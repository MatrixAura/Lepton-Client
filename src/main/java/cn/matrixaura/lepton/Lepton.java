package cn.matrixaura.lepton;

import cn.matrixaura.lepton.bind.BindManager;
import cn.matrixaura.lepton.listener.bus.EventBus;
import cn.matrixaura.lepton.module.ModuleManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Lepton {

    public static Lepton INSTANCE;

    public static final Logger logger = LogManager.getLogger(BuildConfig.NAME);
    private static final EventBus bus = new EventBus();

    private final BindManager bindManager;
    private final ModuleManager moduleManager;

    private Lepton() {
        INSTANCE = this;

        logger.info("Loading Lepton v{}-{}/{}", BuildConfig.VERSION, BuildConfig.HASH, BuildConfig.BRANCH);

        bindManager = new BindManager();
        moduleManager = new ModuleManager();

        bus.subscribe(bindManager);
    }

    public BindManager getBindManager() {
        return bindManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static void init() {
        if (INSTANCE == null) new Lepton();
    }

    public static EventBus getBus() {
        return bus;
    }
}
