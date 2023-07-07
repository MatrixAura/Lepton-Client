package cn.matrixaura.lepton.util.inject;

import cn.matrixaura.lepton.util.file.FileUtils;

import java.util.HashMap;
import java.util.Map;

public class Mappings {

    // friendly -> notch
    private static final Map<String, String> obfClassNames = new HashMap<>();

    // notch -> friendly
    private static final Map<String, String> classNames = new HashMap<>();

    // searge -> notch
    private static final Map<String, String> obfFields = new HashMap<>();

    // friendly -> searge
    private static final Map<String, String> deobfFields = new HashMap<>();

    // notch -> searge
    private static final Map<String, String> obfMethods = new HashMap<>();

    // searge -> friendly
    private static final Map<String, String> deobfMethods = new HashMap<>();

    public static void readMappings(MinecraftVersion mcVer) {
        String content = readSrgFile(mcVer);
        if (content == null || content.isEmpty())
            throw new RuntimeException("Failed to read mappings, cannot continue (null or empty srg)");

        for (String line : content.split("\n")) {
            String[] parts = line
                    .substring(4)
                    .split(" ");

            if (line.startsWith("CL: ")) { // class name
                classNames.put(parts[0], parts[1]);
                obfClassNames.put(parts[1], parts[0]);
            } else if (line.startsWith("FD: ")) { // field name
                String notch = parts[0].split("/")[1];

                String[] split = parts[1].split("/");
                String searge = split[split.length - 1];

                obfFields.put(searge, notch);
            } else if (line.startsWith("MD: ")) { // method name
                // MD: a/a (I)La; net/minecraft/util/EnumChatFormatting/func_175744_a (I)Lnet/minecraft/util/EnumChatFormatting;
                //   | 0   1      2                                                   3

                String notch = parts[0].split("/")[1];

                String[] split = parts[2].split("/");
                String seargeMethod = split[split.length - 1];

                obfMethods.put(seargeMethod, notch);
            }
        }

        readCsv(mcVer);
    }

    private static void readCsv(MinecraftVersion mcVer) {
        String content = FileUtils.read(mcVer.getFields());
        if (content == null || content.isEmpty())
            throw new RuntimeException("Failed to read mappings, cannot continue (null or empty fields csv)");

        // field_100013_f,isPotionDurationMax,0,"True if potion effect duration is at maximum, false otherwise."

        for (String line : content.split("\n")) {
            String[] parts = line.split(",");
            deobfFields.put(parts[1], parts[0]);
        }

        content = FileUtils.read(mcVer.getMethods());
        if (content == null || content.isEmpty())
            throw new RuntimeException("Failed to read mappings, cannot continue (null or empty fields ");

        // func_100011_g,getIsPotionDurationMax,0,

        for (String line : content.split("\n")) {
            String[] parts = line.split(",");
            deobfMethods.put(parts[1], parts[0]);
        }
    }

    /**
     * Gets the notch obfuscated name for this class
     * <p>
     * For example, let's say you have the class name "EnumChatFormatting".
     * This is not helpful the minecraft jar we injected into as it is still obfuscated.
     * This will take the friendly "EnumChatFormatting" and turn it into the notch name "a"
     * </p>
     *
     * @param friendlyClassName the friendly name
     * @return the notch obfuscated name
     */
    public static String getObfClass(String friendlyClassName) {
        return obfClassNames.get(friendlyClassName);
    }

    public static String getUnobfClass(String obfName) {
        return classNames.get(obfName);
    }

    public static String fieldToNotch(String name) {
        String searge = deobfFields.get(name);
        if (searge == null) return null;
        return obfFields.get(name);
    }

    public static String seargeToNotchField(String searge) {
        return obfFields.get(searge);
    }

    public static String seargeToNotchMethod(String searge) {
        return obfMethods.get(searge);
    }

    public static String methodToNotch(String name) {
        String searge = deobfMethods.get(name);
        if (searge == null) return null;
        return obfMethods.get(searge);
    }

    private static String readSrgFile(MinecraftVersion mc) {
        return FileUtils.read(mc.getSrg());
    }

}
