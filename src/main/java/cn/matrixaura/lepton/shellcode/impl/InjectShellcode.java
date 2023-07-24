package cn.matrixaura.lepton.shellcode.impl;

import cn.matrixaura.lepton.shellcode.Shellcode;

import java.lang.instrument.Instrumentation;

public class InjectShellcode extends Shellcode {

    public InjectShellcode() {
        super("Inject", new byte[]{});
    }

    public static Instrumentation invoke(long pid) {
        return null; // TODO
    }

}
