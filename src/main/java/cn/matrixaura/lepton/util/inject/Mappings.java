package cn.matrixaura.lepton.util.inject;

import cn.matrixaura.lepton.util.file.FileUtils;

import java.util.HashMap;
import java.util.Map;

public class Mappings {

    // friendly -> notch (Class)
    private static final Map<String, String> obfClass = new HashMap<>();

    // notch -> friendly (Class)
    private static final Map<String, String> unobfClass = new HashMap<>();

    // searge -> notch (Field)
    private static final Map<String, String> obfFields = new HashMap<>();

    // searge -> notch (Method)
    private static final Map<String, String> obfMethods = new HashMap<>();

    public static void readMappings(MinecraftVersion mcVer) {
        String content = FileUtils.read(mcVer.getSrg());
        if (content.isEmpty())
            throw new RuntimeException("Failed to read mappings, cannot continue (null or empty srg)");

        for (String line : content.split("\n")) {
            String[] parts = line
                    .substring(4)
                    .split(" ");

            if (line.startsWith("CL: ")) { // class name
                unobfClass.put(parts[0], parts[1]);
                obfClass.put(parts[1], parts[0]);
            } else if (line.startsWith("FD: ")) { // field name
                String notch = parts[0].split("/")[1];

                String[] split = parts[1].split("/");
                String searge = split[split.length - 1];

                obfFields.put(searge, notch);
            } else if (line.startsWith("MD: ")) { // method name

                String notch = parts[0].split("/")[1];

                String[] split = parts[2].split("/");
                String seargeMethod = split[split.length - 1];

                obfMethods.put(seargeMethod, notch);
            }
        }

    }

    public static String getObfClass(String friendlyName) {
        return obfClass.get(friendlyName);
    }

    public static String getUnobfClass(String obfName) {
        return unobfClass.get(obfName);
    }

    public static String getUnobfClass(Object obfClass) {
        return getUnobfClass(obfClass.getClass().getName().replace(".", "/"));
    }

    public static String getObfField(String searge) {
        return obfFields.get(searge);
    }

    public static String getObfMethod(String searge) {
        return obfMethods.get(searge);
    }

}
