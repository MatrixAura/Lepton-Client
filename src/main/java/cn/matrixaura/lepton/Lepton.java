package cn.matrixaura.lepton;

import cn.matrixaura.lepton.bind.BindManager;
import cn.matrixaura.lepton.listener.bus.EventBus;
import cn.matrixaura.lepton.module.Core;
import cn.matrixaura.lepton.module.ModuleManager;
import cn.matrixaura.lepton.protect.ProtectionManager;
import cn.matrixaura.lepton.server.LeptonHttpServer;
import cn.matrixaura.lepton.util.protect.HWIDUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

public class Lepton {

    public static Lepton INSTANCE;

    public static final Logger logger = LogManager.getLogger(BuildConfig.NAME);
    private static final EventBus bus = new EventBus();

    private final BindManager bindManager;
    private final ModuleManager moduleManager;

    private final String HWID;
    private final Instrumentation inst;

    private static final boolean PROTECT_STATUS = false;

    private Lepton(Instrumentation inst) {
        INSTANCE = this;
        this.inst = inst;

        bindManager = new BindManager();
        moduleManager = new ModuleManager();

        try {
            LeptonHttpServer.start();
        } catch (IOException e) {
            logger.error("Failed to initialize server {}, {}", e.getMessage(), e.getStackTrace()[0]);
        }

        if (PROTECT_STATUS) {
            try {
                String os = System.getProperty("os.name");
                if (!os.toLowerCase().contains("windows"))
                    throw new RuntimeException("OS Not Supported: " + System.getProperty("os.name"));
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

    public static void init(Instrumentation inst) {
        if (INSTANCE == null) new Lepton(inst);
    }

    public static EventBus getEventBus() {
        return bus;
    }

    public String getHWID() {
        return HWID;
    }

    public Instrumentation getInst() {
        return inst;
    }

    public boolean isProtectEnabled() {
        return PROTECT_STATUS;
    }
}
