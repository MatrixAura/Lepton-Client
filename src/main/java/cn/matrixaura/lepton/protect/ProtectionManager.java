package cn.matrixaura.lepton.protect;

import cn.matrixaura.lepton.protect.impl.AntiAttachProtection;
import cn.matrixaura.lepton.protect.impl.HWIDProtection;
import cn.matrixaura.lepton.util.string.StringUtils;

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
            if (!it.verify()) throw new RuntimeException(StringUtils.decode("VmVyaWZ5IEVycm9y") + " " + it.getName());
        });
    }

}
