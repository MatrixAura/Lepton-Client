package cn.matrixaura.lepton.module.impl.other.anticrash.checks;

import cn.matrixaura.lepton.module.impl.other.anticrash.CrashCheck;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;

import java.util.regex.Pattern;

public class LoggerRCECheck extends CrashCheck {

    private static final Pattern PATTERN = Pattern.compile(".*\\$\\{[^}]*\\}.*");

    public LoggerRCECheck() {
        super("Log4J RCE Check", "Blocked a packet using the Log4J exploit");
    }

    @Override
    public boolean handle(Object packet) {
        if (Mappings.getUnobfClass(packet.getClass().getName().replace(".", "/")).equals("net/minecraft/network/play/server/S29PacketSoundEffect")) {
            String name = (String) ReflectionUtils.invokeMethod(packet.getClass(), packet, Mappings.seargeToNotchMethod("func_149212_c"));
            return PATTERN.matcher(name).matches();
        }

        if (Mappings.getUnobfClass(packet.getClass().getName().replace(".", "/")).equals("net/minecraft/network/play/server/S02PacketChat")) {
            Object component = ReflectionUtils.invokeMethod(packet.getClass(), packet, Mappings.seargeToNotchMethod("func_148915_c"));
            String unformatted = (String) ReflectionUtils.invokeMethod(component.getClass(), component, Mappings.seargeToNotchMethod("func_150260_c"));
            String formatted = (String) ReflectionUtils.invokeMethod(component.getClass(), component, Mappings.seargeToNotchMethod("func_150254_d"));

            return PATTERN.matcher(unformatted).matches()
                    || PATTERN.matcher(formatted).matches();
        }

        return false;
    }
}
