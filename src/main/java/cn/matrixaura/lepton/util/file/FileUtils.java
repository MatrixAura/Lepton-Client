package cn.matrixaura.lepton.util.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static String readPath(String path) {
        StringBuilder builder = new StringBuilder();

        InputStream is = null;
        try {
            is = FileUtils.class.getResourceAsStream(path);
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


    public static String readInputStream(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null)
                stringBuilder.append(line).append('\n');

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static List<String> readURLLines(String urlString) {
        try {
            List<String> lines = new ArrayList<>();
            URL url = new URL(urlString);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
