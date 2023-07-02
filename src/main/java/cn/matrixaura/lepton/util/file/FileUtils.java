package cn.matrixaura.lepton.util.file;

import cn.matrixaura.lepton.util.inject.Mappings;

import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static String read(String path) {
        StringBuilder builder = new StringBuilder();

        InputStream is = null;
        try {
            is = Mappings.class.getResourceAsStream(path);
            if (is == null) return null;

            int b;
            while ((b = is.read()) != -1) {
                builder.append((char) b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return builder.toString();
    }

}
