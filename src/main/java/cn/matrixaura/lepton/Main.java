package cn.matrixaura.lepton;

import cn.matrixaura.lepton.inject.AgentMain;
import cn.matrixaura.lepton.shellcode.impl.InjectShellcode;
import cn.matrixaura.lepton.util.inject.InjectUtils;
import cn.matrixaura.lepton.util.logger.ConsoleColors;
import com.sun.tools.attach.VirtualMachine;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    private static VirtualMachine vm;
    public static InjectType injectType = InjectType.Shellcode;

    public static void main(String[] args) throws IOException, InterruptedException {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (vm != null) {
                try {
                    vm.detach();
                    info("Detached successfully");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            vm = null;
        }, "VM"));

        try {
            Class.forName("com.sun.tools.attach.VirtualMachine");
            debug("tools.jar found");
        } catch (Exception ignored) {
            debug("tools.jar not found, adding dynamically");
            try {
                Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                addURL.setAccessible(true);

                String sep = System.getProperty("file.separator");
                addURL.invoke(ClassLoader.getSystemClassLoader(),
                        new URL("file:///" + System.getenv("JAVA_HOME") + sep + "lib" + sep + "tools.jar"));

                debug("Added tools.jar");
            } catch (Exception e) {
                e.printStackTrace();

                fail("Could not add tools.jar to class loader. Exiting now");
                return;
            }
        }

        version(BuildConfig.VERSION);
        info("Waiting Minecraft process");
        int pid = InjectUtils.getMinecraftProcessId();
        info("Find a Minecraft process, PID: " + ConsoleColors.GREEN + pid + ConsoleColors.RESET);

        File agentJarFile = new File(Main.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getFile());

        try {
            switch (injectType) {
                case Agent: {
                    vm = VirtualMachine.attach(String.valueOf(pid));
                    vm.loadAgent(agentJarFile.getAbsolutePath());
                    break;
                }
                case Shellcode: {
                    AgentMain.agentmain(null, InjectShellcode.invoke(pid));
                    break;
                }
                default: throw new RuntimeException("Invalid type");
            }
            info("Attach to " + pid);
        } catch (Exception e) {
            fail("Failed to attach");
            e.printStackTrace();
        }
    }


    public static void debug(String s) {
        System.out.printf("%sdebug%s: %s\n", ConsoleColors.YELLOW, ConsoleColors.RESET, s);
    }

    public static void info(String s) {
        System.out.printf("%sinfo%s: %s\n", ConsoleColors.CYAN, ConsoleColors.RESET, s);
    }

    public static void fail(String s) {
        System.out.printf("%sfail%s: %s\n", ConsoleColors.RED, ConsoleColors.RESET, s);
    }

    public static void version(String s) {
        System.out.printf("%sclient%s: Version %s%s%s\n", ConsoleColors.PURPLE, ConsoleColors.RESET, ConsoleColors.BLUE, s, ConsoleColors.RESET);
    }

    public enum InjectType {
        Agent,
        Shellcode
    }
}
