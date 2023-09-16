package cn.matrixaura.lepton.module.impl.other.anticrash.checks;

import cn.matrixaura.lepton.module.impl.other.anticrash.CrashCheck;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ObjectUtils;
import cn.matrixaura.lepton.util.packet.PacketUtils;

import java.util.regex.Pattern;

public class LoggerRCECheck extends CrashCheck {

    private static final Pattern PATTERN = Pattern.compile(".*\\$\\{[^}]*\\}.*");

    public LoggerRCECheck() {
        super("Log4J RCE Check");
    }

    @Override
    public boolean handle(Object packet) {
        if (PacketUtils.isPacketInstanceof(packet, "S29PacketSoundEffect")) {
            String name = (String) ObjectUtils.invokeMethod(packet, Mappings.getObfMethod("func_149212_c"));
            return PATTERN.matcher(name).matches();
        }

        if (PacketUtils.isPacketInstanceof(packet, "S02PacketChat")) {
            Object component = ObjectUtils.invokeMethod(packet, Mappings.getObfMethod("func_148915_c"));
            String unformatted = (String) ObjectUtils.invokeMethod(component, Mappings.getObfMethod("func_150260_c"));
            String formatted = (String) ObjectUtils.invokeMethod(component, Mappings.getObfMethod("func_150254_d"));

            return PATTERN.matcher(unformatted).matches()
                    || PATTERN.matcher(formatted).matches();
        }

        return false;
    }
}
