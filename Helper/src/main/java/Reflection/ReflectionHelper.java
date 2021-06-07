package Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("all")
public final class ReflectionHelper {
    public static Method getMethod(Class clazz, String functionName) throws NoSuchMethodException {
        return clazz.getDeclaredMethod(functionName);
    }

    public static void setProperty(Class clazz, Object instance, String propertyName, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(propertyName);
        if (!field.isAccessible())
            field.setAccessible(true);
        field.set(instance, value);
    }

    public static Object invokeMethod(Method method, Object instance, Object... args)
            throws InvocationTargetException, IllegalAccessException {
        if (!method.isAccessible())
            method.setAccessible(true);
        return method.invoke(instance, args);
    }
}
