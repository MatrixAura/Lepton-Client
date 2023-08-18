package cn.matrixaura.lepton.util.inject;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.inject.asm.api.Transformers;
import cn.matrixaura.lepton.server.LeptonHttpServer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.UnmodifiableClassException;

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
                user32.GetWindowThreadProcessId(hWnd, pid);
            } else {
                Thread.sleep(100L);
            }
        } while (pid.getValue() == -1);
        return pid.getValue();
    }

    public static void redefineClass(Class<?> clazz, byte[] newByte) throws UnmodifiableClassException, ClassNotFoundException {
        Lepton.INSTANCE.getInst().redefineClasses(new ClassDefinition(clazz, newByte));
    }

    public static void destroyClient() {
        Transformers.transformers.forEach(it -> {
            try {
                InjectUtils.redefineClass(it.getClazz(), it.getOldBytes());
            } catch (UnmodifiableClassException | ClassNotFoundException ignored) {
            }
        });
        LeptonHttpServer.stop();
    }

    public static byte[] getClassBytes(Class<?> c) throws IOException {
        String className = c.getName();
        String classAsPath = className.replace('.', '/') + ".class";
        InputStream stream = c.getClassLoader().getResourceAsStream(classAsPath);
        return stream == null ? null : IOUtils.toByteArray(stream);
    }

}
