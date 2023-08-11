package cn.matrixaura.lepton.protect.impl;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.protect.Protection;
import cn.matrixaura.lepton.util.file.FileUtils;

public class HWIDProtection extends Protection {

    public HWIDProtection() {
        super("HWID");
    }

    @Override
    public boolean verify() {
        String hwid = Lepton.INSTANCE.getHWID();
        for (String str : FileUtils.readURLLines("Your HWID URL")) { // TODO: HWID URL
            if (str.equals(hwid)) return true;
        }
        return false;
    }

}
