package cn.matrixaura.lepton.shellcode.impl;

import cn.matrixaura.lepton.shellcode.Shellcode;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import sun.tools.attach.WindowsVirtualMachine;

public class AntiAttachShellcode extends Shellcode {

    public AntiAttachShellcode() {
        super("Anti Attach", new byte[]{});
    }

    public static void invoke(int pid) {

    }

}
