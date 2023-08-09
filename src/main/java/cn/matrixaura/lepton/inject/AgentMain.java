package cn.matrixaura.lepton.inject;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.exception.SystemNotSupportedException;
import cn.matrixaura.lepton.inject.asm.api.Transformers;
import cn.matrixaura.lepton.inject.dynamic.Dynamics;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.MinecraftVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

public class AgentMain {
    public static final Logger logger = LogManager.getLogger("Agent");

    public static void agentmain(String args, Instrumentation is) {
        try {
            Mappings.readMappings(MinecraftVersion.VER_1_8_9);
            logger.info("Read mappings successfully");
        } catch (Exception e) {
            logger.error("Failed to read mappings, {}", e.getStackTrace()[0]);
            return;
        }

        try {
            Dynamics.defineClasses();
        } catch (Exception e) {
            logger.error("Failed to init dynamic classes {}, {}", e.getMessage(), e.getStackTrace()[0]);
            e.printStackTrace();
        }

        Lepton.init();

        try {
            Transformers.transform(is);
        } catch (IOException e) {
            logger.error("Failed to init transformers {}, {}", e.getMessage(), e.getStackTrace()[0]);
            e.printStackTrace();
        }
    }
}
