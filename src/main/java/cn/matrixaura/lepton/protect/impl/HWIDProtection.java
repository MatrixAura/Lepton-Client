package cn.matrixaura.lepton.protect.impl;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.protect.Protection;
import cn.matrixaura.lepton.util.file.FileUtils;
import cn.matrixaura.lepton.util.string.StringUtils;

public class HWIDProtection extends Protection {

    public HWIDProtection() {
        super(StringUtils.decode("SFdJRA=="));
    }

    @Override
    public boolean verify() {
        String hwid = Lepton.INSTANCE.getHWID();
        for (String str : FileUtils.readURLLines("Your HWID url")) { // TODO: HWID URL
            if (str.equals(hwid)) return true;
        }
        return false;
    }

}
