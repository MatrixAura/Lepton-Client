package wtf.agent.inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wtf.agent.client.Agent;
import wtf.agent.inject.asm.api.Transformers;
import wtf.agent.inject.mapping.Mappings;
import wtf.agent.inject.mapping.MinecraftVersion;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

public class AgentMain {
    private static final Logger logger = LogManager.getLogger("Agent");

    public static void agentmain(String args, Instrumentation is) {
        try {

            // TODO: autodetect somehow? prolly via game args
            Mappings.readMappings(MinecraftVersion.VER_1_8_8);
            logger.info("Read mappings successfully");
        } catch (Exception e) {
            logger.error("Failed to read mappings, {}", e.getStackTrace()[0]);
            return;
        }

        try {
            Transformers.init(is);
        } catch (IOException e) {
            logger.error("Failed to init mixins {}, {}", e.getMessage(), e.getStackTrace()[0]);
            e.printStackTrace();
        }

        // init client
        Agent.init();
    }
}
