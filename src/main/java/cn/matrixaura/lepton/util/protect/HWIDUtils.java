package cn.matrixaura.lepton.util.protect;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class HWIDUtils {

    public static String getHWID() {
        String str = System.getenv("os") +
                System.getProperty("os.name") +
                System.getProperty("os.arch") +
                System.getenv("PROCESSOR_LEVEL") +
                System.getenv("PROCESSOR_REVISION") +
                System.getenv("PROCESSOR_IDENTIFIER") +
                System.getenv("NUMBER_OF_PROCESSORS") +
                System.getenv("PROCESSOR_ARCHITECTURE") +
                System.getenv("PROCESSOR_ARCHITEW6432");
        return UUID.nameUUIDFromBytes(str.getBytes(StandardCharsets.UTF_8)).toString();
    }

}