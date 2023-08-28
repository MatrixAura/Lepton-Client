package cn.matrixaura.lepton.inject;

import cn.matrixaura.lepton.BuildConfig;
import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.inject.asm.api.Transformers;
import cn.matrixaura.lepton.inject.dynamic.Dynamics;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.MinecraftType;
import cn.matrixaura.lepton.util.inject.MinecraftVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

public class AgentMain {
    public static final Logger logger = LogManager.getLogger("Agent");

    public static void agentmain(String args, Instrumentation is) {
        logger.info("Loading Lepton v{}-{}/{}", BuildConfig.VERSION, BuildConfig.HASH, BuildConfig.BRANCH);

        try {
            Mappings.readMappings(MinecraftVersion.VER_189, MinecraftType.VANILLA);
            logger.info("Read mappings successfully");
        } catch (Exception e) {
            logger.error("Failed to read mappings, {}", e.getStackTrace()[0]);
            return;
        }

        try {
            Dynamics.defineClasses();
            logger.info("Define dynamic classes successfully");
        } catch (Exception e) {
            logger.error("Failed to init dynamic classes {}, {}", e.getMessage(), e.getStackTrace()[0]);
            e.printStackTrace();
        }

        try {
            Lepton.init(is);
            logger.info("Initialize client successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize client {}, {}", e.getMessage(), e.getStackTrace()[0]);
        }

        try {
            Transformers.transform(is);
            logger.info("Transform classes successfully");
        } catch (IOException e) {
            logger.error("Failed to init transformers {}, {}", e.getMessage(), e.getStackTrace()[0]);
            e.printStackTrace();
        }
    }
}
