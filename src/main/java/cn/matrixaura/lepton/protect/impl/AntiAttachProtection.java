package cn.matrixaura.lepton.protect.impl;

import cn.matrixaura.lepton.protect.Protection;
import cn.matrixaura.lepton.util.string.StringUtils;

public class AntiAttachProtection extends Protection {

    private static final byte[] x64ByteCode = {}; // TODO: Until 2023/8/10
    private static final byte[] x86ByteCode = {}; // TODO: Until 2023/8/10

    public AntiAttachProtection() {
        super(StringUtils.decode("QW50aS1BdHRhY2g="));
    }

    @Override
    public boolean verify() {
        try {

            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
/*
    char retn[] = { '\xc3',0 };
    LPVOID dst = fn_GetProcAddress(jvmDll, "JVM_EnqueueOperation");
    DWORD old;
    if (fn_VirtualProtectEx((HANDLE)-1, dst, 1, PAGE_EXECUTE_READWRITE, &old)) {
        fn_WriteProcessMemory((HANDLE)-1, dst, retn, 1, NULL);
        fn_VirtualProtectEx((HANDLE)-1, dst, 1, old, &old);
    }
 */