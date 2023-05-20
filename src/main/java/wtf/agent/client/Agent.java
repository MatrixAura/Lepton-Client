package wtf.agent.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wtf.agent.BuildConfig;
import wtf.agent.client.bind.BindManager;
import wtf.agent.client.listener.bus.EventBus;
import wtf.agent.client.module.ModuleManager;

public class Agent {

    private static Agent singleton;

    private static final Logger logger = LogManager.getLogger(BuildConfig.NAME);
    private static final EventBus bus = new EventBus();

    private final BindManager binds;
    private final ModuleManager modules;

    private Agent() {
        singleton = this;

        logger.info("Loading Agent v{}-{}/{}", BuildConfig.VERSION, BuildConfig.HASH, BuildConfig.BRANCH);

        binds = new BindManager();
        modules = new ModuleManager();

        bus.subscribe(binds);
    }

    public BindManager getBinds() {
        return binds;
    }

    public ModuleManager getModules() {
        return modules;
    }

    public static void init() {
        if (singleton == null) new Agent();
    }

    public static EventBus getBus() {
        return bus;
    }

    public static Agent getInstance() {
        return singleton;
    }
}
