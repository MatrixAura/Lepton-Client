package wtf.agent.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wtf.agent.BuildConfig;
import wtf.agent.client.listener.bus.EventBus;

public class Agent {

    private static Agent singleton;

    private static final Logger logger = LogManager.getLogger(BuildConfig.NAME);

    private static final EventBus bus = new EventBus();

    private Agent() {
        singleton = this;

        logger.info("Loading Agent v{}-{}/{}", BuildConfig.VERSION, BuildConfig.HASH, BuildConfig.BRANCH);

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
