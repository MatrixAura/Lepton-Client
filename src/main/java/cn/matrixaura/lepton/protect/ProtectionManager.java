package cn.matrixaura.lepton.protect;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.protect.impl.AntiAttachProtection;
import cn.matrixaura.lepton.protect.impl.HWIDProtection;

import java.util.ArrayList;
import java.util.List;

public class ProtectionManager {

    private static final List<Protection> protections = new ArrayList<>();

    static {
        protections.add(new HWIDProtection());
        protections.add(new AntiAttachProtection());
    }

    public static void process() {
        protections.forEach(it -> {
            if (!it.verify()) throw new RuntimeException("Verify Error: " + it.getName());
            else Lepton.logger.info("Verify Success: {}", it.getName());
        });
    }

}
