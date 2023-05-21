package wtf.agent.inject.asm.api;

import java.lang.reflect.Field;

public class ReflectionUtils {

    public static Object getFieldValue(Class<?> clazz, Object o, String name) {
        Class<?> c = clazz;
        while (c.getSuperclass() != null) {

            try {
                Field[] fields = c.getDeclaredFields();
                for (Field field : fields) {
                    if (field.getName().equals(name)) return field.get(o);
                }
            } catch (IllegalAccessException ignored) {
            }

            c = c.getSuperclass();
        }

        return null;
    }
}
