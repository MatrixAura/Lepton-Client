package cn.matrixaura.lepton.util.protect;

import cn.matrixaura.lepton.util.inject.ReflectionUtils;
import cn.matrixaura.lepton.util.string.StringUtils;

import java.nio.charset.StandardCharsets;

public class HWIDUtils {

    public static String getHWID() throws Exception { // All strings are base64-encoded
        Class<?> systemClass = Class.forName(StringUtils.decode("amF2YS5sYW5nLlN5c3RlbQ=="));
        Class<?> uuidClass = Class.forName(StringUtils.decode("amF2YS51dGlsLlVVSUQ="));
        String str = (String) ReflectionUtils.invokeMethod(systemClass, StringUtils.decode("Z2V0ZW52"), new Class[]{String.class}, StringUtils.decode("b3M=")) +
                ReflectionUtils.invokeMethod(systemClass, StringUtils.decode("Z2V0UHJvcGVydHk="), new Class[]{String.class}, StringUtils.decode("b3MubmFtZQ==")) +
                ReflectionUtils.invokeMethod(systemClass, StringUtils.decode("Z2V0UHJvcGVydHk="), new Class[]{String.class}, StringUtils.decode("b3MuYXJjaA==")) +
                ReflectionUtils.invokeMethod(systemClass, StringUtils.decode("Z2V0ZW52"), new Class[]{String.class}, StringUtils.decode("UFJPQ0VTU09SX0xFVkVM")) +
                ReflectionUtils.invokeMethod(systemClass, StringUtils.decode("Z2V0ZW52"), new Class[]{String.class}, StringUtils.decode("UFJPQ0VTU09SX1JFVklTSU9O")) +
                ReflectionUtils.invokeMethod(systemClass, StringUtils.decode("Z2V0ZW52"), new Class[]{String.class}, StringUtils.decode("UFJPQ0VTU09SX0lERU5USUZJRVI=")) +
                ReflectionUtils.invokeMethod(systemClass, StringUtils.decode("Z2V0ZW52"), new Class[]{String.class}, StringUtils.decode("UFJPQ0VTU09SX0FSQ0hJVEVDVFVSRQ==")) +
                ReflectionUtils.invokeMethod(systemClass, StringUtils.decode("Z2V0ZW52"), new Class[]{String.class}, StringUtils.decode("UFJPQ0VTU09SX0FSQ0hJVEVXNjQzMg==")) +
                ReflectionUtils.invokeMethod(systemClass, StringUtils.decode("Z2V0ZW52"), new Class[]{String.class}, StringUtils.decode("TlVNQkVSX09GX1BST0NFU1NPUlM="));
        Object obj = str.getBytes(StandardCharsets.UTF_8);
        return (String) ReflectionUtils.invokeMethod(ReflectionUtils.invokeMethod(uuidClass, StringUtils.decode("bmFtZVVVSURGcm9tQnl0ZXM="), new Class[]{byte[].class}, obj), StringUtils.decode("dG9TdHJpbmc="));
    }

}