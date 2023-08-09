package cn.matrixaura.lepton.util.inject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtils {

    public static Object getFieldValue(Object instance, String name) {
        Class<?> c = instance.getClass();
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

    public static Object getFieldValue(Class<?> clazz, String name) {
        Class<?> c = clazz;
        while (c.getSuperclass() != null) {

            try {
                Field field = c.getDeclaredField(name);
                field.setAccessible(true);
                return field.get(null);
            } catch (IllegalAccessException | NoSuchFieldException ignored) {
            }

            c = c.getSuperclass();
        }

        return null;
    }

    public static void setFieldValue(Object instance, String name, Object value) {
        Class<?> c = instance.getClass();
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

    public static Object invokeMethod(Object instance, String name, Class<?>[] desc, Object... args) {
        Class<?> c = instance.getClass();
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

    public static Object invokeMethod(Class<?> clazz, String name, Class<?>[] desc, Object... args) {
        Class<?> c = clazz;
        while (c.getSuperclass() != null) {
            try {
                Method method = c.getDeclaredMethod(name, desc);
                method.setAccessible(true);
                return method.invoke(null, args);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
            }

            c = c.getSuperclass();
        }
        return null;
    }

    public static Object invokeMethod(Object instance, String name) {
        Class<?> c = instance.getClass();
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

    public static Object newInstance(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    public static Object newInstance(Class<?> clazz, Class<?>[] desc, Object... args) {
        Class<?> c = clazz;
        while (c.getSuperclass() != null) {
            try {
                Constructor<?> method = c.getDeclaredConstructor(desc);
                method.setAccessible(true);
                return method.newInstance(args);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException ignored) {

            }

            c = c.getSuperclass();
        }
        return null;
    }
}
