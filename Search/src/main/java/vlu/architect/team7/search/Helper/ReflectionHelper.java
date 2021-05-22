package vlu.architect.team7.search.Helper;

import java.lang.reflect.Method;

@SuppressWarnings("all")
public final class ReflectionHelper {


    public static Method getMethod(Class clazz, String functionName) throws NoSuchMethodException {
        return clazz.getDeclaredMethod(functionName);
    }
}
