package cn.matrixaura.lepton.util.string;

import com.sun.net.httpserver.HttpExchange;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;

public class URLUtils {

    public static String encode(String original) {
        try {
            return URLEncoder.encode(original, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decode(String original) {
        try {
            return URLDecoder.decode(original, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] getValues(HttpExchange he) {
        try {
            String[] keyValues = he.getRequestURI().getRawQuery().split("&");
            String[] values = new String[keyValues.length];
            for (int i = 0; i < keyValues.length; i++) {
                values[i] = URLUtils.decode(keyValues[i].split("=")[1]);
            }
            return values;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
