package cn.matrixaura.lepton.util.inject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtils {

    public static Object getFieldValue(Class<?> clazz, Object instance, String name) {
        Class<?> c = clazz;
        while (c.getSuperclass() != null) {

            try {
                Field field = c.getDeclaredField(name);
                field.setAccessible(true);
                return field.get(instance);
            } catch (IllegalAccessException | NoSuchFieldException ignored) {
            }

            c = c.getSuperclass();
        }

        return null;
    }

    public static void setFieldValue(Class<?> clazz, Object instance, String name, Object value) {
        Class<?> c = clazz;
        while (c.getSuperclass() != null) {

            try {
                Field field = c.getDeclaredField(name);
                field.setAccessible(true);
                field.set(instance, value);
            } catch (IllegalAccessException | NoSuchFieldException ignored) {
            }

            c = c.getSuperclass();
        }
    }

    public static Object invokeMethod(Class<?> clazz, Object instance, String name, Class<?>[] desc, Object... args) {
        Class<?> c = clazz;
        while (c.getSuperclass() != null) {
            try {
                Method method = c.getDeclaredMethod(name, desc);
                method.setAccessible(true);
                return method.invoke(instance, args);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
            }

            c = c.getSuperclass();
        }
        return null;
    }

    public static Object invokeMethod(Class<?> clazz, Object instance, String name) {
        Class<?> c = clazz;
        while (c.getSuperclass() != null) {
            try {
                Method method = c.getDeclaredMethod(name);
                method.setAccessible(true);
                return method.invoke(instance);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
            }

            c = c.getSuperclass();
        }
        return null;
    }

    public static Object invokeMethod(Class<?> clazz, String name) {
        Class<?> c = clazz;
        while (c.getSuperclass() != null) {
            try {
                Method method = c.getDeclaredMethod(name);
                method.setAccessible(true);
                return method.invoke(null);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
            }

            c = c.getSuperclass();
        }
        return null;
    }
}
