package cn.matrixaura.lepton.util.inject;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;

public class ClassUtils {

    public static List<Class<?>> getClasses(String packageName) throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        boolean recursive = true;
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                try {
                    Enumeration<JarEntry> entries = ((JarURLConnection) dirs.nextElement().openConnection()).getJarFile().entries();
                    findClassesInPackageByJar(packageName, entries, packageDirName, recursive, classes);
                } catch (IOException ignored) {
                }
            }
        } catch (IOException ignored) {
        }
        return classes;
    }

    public static void findClassesInPackageByJar(String packageName, Enumeration<JarEntry> entries, String packageDirName, boolean recursive, List<Class<?>> classes) {
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            if (name.charAt(0) == '/') {
                name = name.substring(1);
            }
            if (name.startsWith(packageDirName)) {
                int idx = name.lastIndexOf('/');
                if (idx != -1) {
                    packageName = name.substring(0, idx).replace('/', '.');
                }
                if ((idx != -1) || recursive) {
                    if (name.endsWith(".class") && !entry.isDirectory()) {
                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                        try {
                            classes.add(Class.forName(packageName + '.' + className));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


}
