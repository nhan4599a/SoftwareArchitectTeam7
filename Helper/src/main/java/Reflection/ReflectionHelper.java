package Reflection;

import java.lang.reflect.Field;

@SuppressWarnings("all")
public final class ReflectionHelper {

    public static void setProperty(Class clazz, Object instance, String propertyName, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(propertyName);
        if (!field.isAccessible())
            field.setAccessible(true);
        field.set(instance, value);
    }
}
