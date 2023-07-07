package cn.matrixaura.lepton.util.inject;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;

public class InjectUtils {

    public static int getMinecraftProcessId() throws InterruptedException {
        User32 user32 = User32.INSTANCE;
        IntByReference pid = new IntByReference(-1);
        do {
            WinDef.HWND findWindow;
            WinDef.HWND hWnd = findWindow = user32.FindWindow("LWJGL", null);
            if (findWindow != null && findWindow.getPointer() != null) {
                char[] buffer = new char[1024];
                user32.GetWindowText(hWnd, buffer, buffer.length);
                final String windowText = new String(buffer);
                if (windowText.toLowerCase().contains("minecraft") || windowText.toLowerCase().contains("client")) {
                    user32.GetWindowThreadProcessId(hWnd, pid);
                }
            } else {
                Thread.sleep(100L);
            }
        } while (pid.getValue() == -1);
        return pid.getValue();
    }

}
