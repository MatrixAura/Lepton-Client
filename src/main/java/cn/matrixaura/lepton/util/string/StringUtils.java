package cn.matrixaura.lepton.util.string;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class StringUtils {

    public static String encode(String input) {
        byte[] encodedBytes = Base64.getEncoder().encode(input.getBytes(StandardCharsets.UTF_8));
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }

    public static String decode(String input) {
        byte[] decodedBytes = Base64.getDecoder().decode(input.getBytes(StandardCharsets.UTF_8));
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

}