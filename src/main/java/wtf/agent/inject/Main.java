package wtf.agent.inject;

import com.sun.tools.attach.*;
import net.bytebuddy.agent.ByteBuddyAgent;
import wtf.agent.inject.logger.ConsoleColors;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// this code is also dogshit wow
public class Main {

    private static final List<String> MINECRAFT_START = new ArrayList<>();
    private static boolean attached;

    static {
        MINECRAFT_START.add("net.minecraft.client.main.Main");
        MINECRAFT_START.add("net.minecraft.launchwrapper.Launch");
    }

    public static void main(String[] args) throws IOException {

        File agentJarFile;
        if (args.length != 0 && args[0].equals("--dev")) {
            dbug("develop mode");
            agentJarFile = new File(System.getProperty("user.dir") + "/build/libs/Agent-3.0.jar");
        } else {
            agentJarFile = new File(Main.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getFile());
        }

        try {
            Class.forName("com.sun.tools.attach.VirtualMachine");
            dbug("tools.jar found");
        } catch (Exception ignored) {
            dbug("tools.jar not found, adding dynamically");
            try {

                // thanks linus
                Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                addURL.setAccessible(true);

                String sep = System.getProperty("file.separator");
                addURL.invoke(ClassLoader.getSystemClassLoader(),
                        new URL("file:///" + System.getenv("JAVA_HOME") + sep + "lib" + sep + "tools.jar"));

                dbug("added tools.jar");
            } catch (Exception e) {
                e.printStackTrace();

                fail("could not add tools.jar to class loader. exiting now");
                return;
            }
        }

        Scanner sc = new Scanner(System.in);

        while (!attached) {
            List<VirtualMachineDescriptor> desc = VirtualMachine.list();

            List<String> valid = new ArrayList<>();
            desc.forEach((x) -> {
                String name = x.displayName();
                if (name == null || (name = name.trim()).isEmpty()) return;

                if (name.equals(Main.class.getName())) return;

                valid.add(x.id());

                String format = "PID %s%s%s -> %s";
                for (String c : MINECRAFT_START) {
                    if (x.displayName().startsWith(c)) {
                        format += " " + ConsoleColors.BLUE + "(recommended)" + ConsoleColors.RESET;
                    }
                }

                info(String.format(format, ConsoleColors.GREEN, x.id(), ConsoleColors.RESET, name.split(" ")[0]));
            });
            info("Select the " + ConsoleColors.GREEN + "PID" + ConsoleColors.RESET + " for Minecraft 1.8 (or \"r\" for refresh): ");
            String pid = sc.nextLine();

            if (pid.equalsIgnoreCase("r") || !valid.contains(pid)) continue;

            System.out.println();
            dbug("selected PID " + pid);

            try {
                ByteBuddyAgent.attach(agentJarFile, pid);
                attached = true;
                info("attach to " + pid);
            } catch (Exception e) {
                fail("failed to attach");
                e.printStackTrace();
                return;
            }

        }

        if (attached) {
            info("Type anything to exit");
            sc.next();
        }
    }

    private static void dbug(String s) {
        System.out.printf("%sdbug%s: %s\n", ConsoleColors.YELLOW, ConsoleColors.RESET, s);
    }

    private static void info(String s) {
        System.out.printf("%sinfo%s: %s\n", ConsoleColors.CYAN, ConsoleColors.RESET, s);
    }

    private static void fail(String s) {
        System.out.printf("%sfail%s: %s\n", ConsoleColors.RED, ConsoleColors.RESET, s);
    }
}
