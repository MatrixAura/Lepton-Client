package cn.matrixaura.lepton.protect.impl;

import cn.matrixaura.lepton.exception.SystemNotSupportedException;
import cn.matrixaura.lepton.protect.Protection;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import cn.matrixaura.lepton.util.string.StringUtils;
import sun.tools.attach.WindowsVirtualMachine;

public class AntiAttachProtection extends Protection {

    private final byte[] shellcode;

    public AntiAttachProtection() {
        super(StringUtils.decode("QW50aS1BdHRhY2g="));
        String bits = System.getProperty("os.arch").toLowerCase();
        if (bits.contains("64"))
            shellcode = new byte[]{72, -119, 92, 36, 24, 72, -119, 108, 36, 32, 86, 87, 65, 87, 72, -125, -20, 48, -24, -39, 1, 0, 0, 72, -117, -8, 51, -10, 65, -65, 1, 0, 0, 0, 72, 99, 64, 60, 57, -76, 56, -116, 0, 0, 0, 15, -124, -94, 0, 0, 0, -117, -116, 56, -120, 0, 0, 0, -123, -55, 15, -124, -109, 0, 0, 0, 72, -115, 4, 15, 51, -46, -117, 72, 32, 68, -117, 64, 36, 72, 3, -49, 68, -117, 72, 28, 76, 3, -57, 68, -117, 80, 24, 76, 3, -49, 69, 43, -41, -117, 1, 72, 3, -57, -128, 56, 71, 117, 78, -128, 120, 1, 101, 117, 72, -128, 120, 2, 116, 117, 66, -128, 120, 3, 80, 117, 60, -128, 120, 4, 114, 117, 54, -128, 120, 5, 111, 117, 48, -128, 120, 6, 99, 117, 42, -128, 120, 7, 65, 117, 36, -128, 120, 8, 100, 117, 30, -128, 120, 9, 100, 117, 24, -128, 120, 10, 114, 117, 18, -128, 120, 11, 101, 117, 12, -128, 120, 12, 115, 117, 6, -128, 120, 13, 115, 116, 14, 65, 3, -41, 72, -125, -63, 4, 65, 59, -46, 119, 14, -21, -102, 65, 15, -73, 12, 80, 65, -117, 52, -119, 72, 3, -9, 72, -115, 21, -72, 0, 0, 0, 72, -117, -49, -1, -42, 72, -115, 21, -44, 0, 0, 0, 72, -117, -49, 72, -117, -40, -1, -42, 72, -115, 21, -83, 0, 0, 0, 72, -117, -49, 72, -117, -24, -1, -42, 72, -115, 13, -122, 0, 0, 0, 102, -57, 68, 36, 80, -61, 0, 72, -117, -8, -1, -45, 72, -117, -56, 72, -115, 21, -72, 0, 0, 0, -1, -42, 72, -117, -40, 72, -125, -50, -1, 72, -115, 68, 36, 88, 72, -117, -45, 65, -71, 64, 0, 0, 0, 72, -119, 68, 36, 32, 77, -117, -57, 72, -117, -50, -1, -41, -123, -64, 116, 48, 72, -125, 100, 36, 32, 0, 76, -115, 68, 36, 80, 77, -117, -49, 72, -117, -45, 72, -117, -50, -1, -43, 68, -117, 76, 36, 88, 72, -115, 68, 36, 88, 77, -117, -57, 72, -119, 68, 36, 32, 72, -117, -45, 72, -117, -50, -1, -41, 72, -117, 92, 36, 96, 51, -64, 72, -117, 108, 36, 104, 72, -125, -60, 48, 65, 95, 95, 94, -61, -52, -52, -52, 106, 118, 109, 46, 100, 108, 108, 0, 76, 111, 97, 100, 76, 105, 98, 114, 97, 114, 121, 65, 0, 0, 0, 0, 86, 105, 114, 116, 117, 97, 108, 80, 114, 111, 116, 101, 99, 116, 69, 120, 0, 0, 0, 0, 0, 0, 0, 0, 87, 114, 105, 116, 101, 80, 114, 111, 99, 101, 115, 115, 77, 101, 109, 111, 114, 121, 0, 0, 0, 0, 0, 0, 74, 86, 77, 95, 69, 110, 113, 117, 101, 117, 101, 79, 112, 101, 114, 97, 116, 105, 111, 110, 0, -52, -52, -52, -52, -52, -52, -52, 101, 72, -117, 4, 37, 96, 0, 0, 0, 72, -117, 64, 24, 72, -117, 64, 48, 72, -117, 0, 72, -117, 0, 72, -117, 64, 16, -61};
        else if (bits.contains("32"))
            shellcode = new byte[]{85, -117, -20, -125, -20, 16, 83, 86, 87, -24, 34, 1, 0, 0, -117, -8, 51, -37, -117, 79, 60, 57, 92, 57, 124, 15, -124, -97, 0, 0, 0, -117, 68, 57, 120, -123, -64, 15, -124, -109, 0, 0, 0, -117, 76, 56, 36, -117, 116, 56, 32, 3, -49, -117, 84, 56, 24, 3, -9, -119, 77, -12, -117, 76, 56, 28, 3, -49, -119, 77, -16, 51, -55, 74, 102, 15, 31, 68, 0, 0, -117, 4, -114, 3, -57, -128, 56, 71, 117, 78, -128, 120, 1, 101, 117, 72, -128, 120, 2, 116, 117, 66, -128, 120, 3, 80, 117, 60, -128, 120, 4, 114, 117, 54, -128, 120, 5, 111, 117, 48, -128, 120, 6, 99, 117, 42, -128, 120, 7, 65, 117, 36, -128, 120, 8, 100, 117, 30, -128, 120, 9, 100, 117, 24, -128, 120, 10, 114, 117, 18, -128, 120, 11, 101, 117, 12, -128, 120, 12, 115, 117, 6, -128, 120, 13, 115, 116, 7, 65, 59, -54, 118, -93, -21, 15, -117, 69, -12, -117, 93, -16, 15, -73, 4, 72, -117, 28, -125, 3, -33, 104, 76, 17, 64, 0, 87, -1, -45, 104, 112, 17, 64, 0, 87, -117, -16, -1, -45, 104, 92, 17, 64, 0, 87, -119, 69, -16, -1, -45, 104, -124, 17, 64, 0, 104, 68, 17, 64, 0, -117, -8, 102, -57, 69, -4, -61, 0, -1, -42, 80, -1, -45, -117, -16, -115, 69, -8, 80, 106, 64, 106, 1, 86, 106, -1, -1, -41, -123, -64, 116, 28, 106, 0, 106, 1, -115, 69, -4, 80, 86, 106, -1, -1, 85, -16, -115, 69, -8, 80, -1, 117, -8, 106, 1, 86, 106, -1, -1, -41, 95, 94, 51, -64, 91, -117, -27, 93, -61, -52, -52, -52, -52, -52, -52, 100, -95, 48, 0, 0, 0, -117, 64, 12, -117, 64, 20, -117, 0, -117, 0, -117, 64, 16, -61, 106, 118, 109, 46, 100, 108, 108, 0, 76, 111, 97, 100, 76, 105, 98, 114, 97, 114, 121, 65, 0, 0, 0, 0, 86, 105, 114, 116, 117, 97, 108, 80, 114, 111, 116, 101, 99, 116, 69, 120, 0, 0, 0, 0, 87, 114, 105, 116, 101, 80, 114, 111, 99, 101, 115, 115, 77, 101, 109, 111, 114, 121, 0, 0, 74, 86, 77, 95, 69, 110, 113, 117, 101, 117, 101, 79, 112, 101, 114, 97, 116, 105, 111, 110};
        else throw new SystemNotSupportedException(bits);
    }

    @Override
    public boolean verify() {
        try {
            ReflectionUtils.invokeMethod(WindowsVirtualMachine.class, "enqueue", new Class[]{long.class, byte[].class, String.class, String.class, Object[].class}, -1, shellcode, "enqueue", "enqueue", new Object[]{});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}