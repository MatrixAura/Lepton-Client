package cn.matrixaura.lepton;

import cn.matrixaura.lepton.bind.BindManager;
import cn.matrixaura.lepton.listener.bus.EventBus;
import cn.matrixaura.lepton.module.Core;
import cn.matrixaura.lepton.module.ModuleManager;
import cn.matrixaura.lepton.protect.ProtectionManager;
import cn.matrixaura.lepton.server.LeptonHttpServer;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import cn.matrixaura.lepton.util.protect.HWIDUtils;
import cn.matrixaura.lepton.util.string.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Lepton {

    public static Lepton INSTANCE;

    public static final Logger logger = LogManager.getLogger(BuildConfig.NAME);
    private static final EventBus bus = new EventBus();

    private final BindManager bindManager;
    private final ModuleManager moduleManager;

    private final String HWID; // My HWID is 6a80d5b9-1dbc-37f7-9a69-ea600dcf5006 LOL - MatrixAura

    public static final boolean PROTECT_ENABLED = false;

    private Lepton() {
        INSTANCE = this;

        logger.info("Loading Lepton v{}-{}/{}", BuildConfig.VERSION, BuildConfig.HASH, BuildConfig.BRANCH);

        bindManager = new BindManager();
        moduleManager = new ModuleManager();

        try {
            LeptonHttpServer.start();
        } catch (IOException e) {
            logger.error("Failed to initialize server");
            e.printStackTrace();
        }

        if (PROTECT_ENABLED) {
            try {
                Class<?> systemClass = Class.forName(StringUtils.decode("amF2YS5sYW5nLlN5c3RlbQ=="));
                String os = (String) ReflectionUtils.invokeMethod(systemClass, StringUtils.decode("Z2V0UHJvcGVydHk="), new Class[]{String.class}, StringUtils.decode("b3MubmFtZQ=="));
                if (os.toLowerCase().contains(StringUtils.decode("d2luZG93cw==")))
                    throw new RuntimeException(StringUtils.decode("T1MgTm90IFN1cHBvcnRlZDog") + System.getProperty("os.name"));
                HWID = HWIDUtils.getHWID();
                ProtectionManager.process();
            } catch (Exception impossible) {
                throw new RuntimeException(impossible);
            }
        } else HWID = null;

        bus.subscribe(bindManager);
        bus.subscribe(new Core());
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

    public String getHWID() {
        return HWID;
    }
}
