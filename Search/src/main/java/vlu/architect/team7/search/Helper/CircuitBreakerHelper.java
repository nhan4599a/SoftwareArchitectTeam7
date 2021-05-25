package vlu.architect.team7.search.Helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@SuppressWarnings("all")
public final class CircuitBreakerHelper {

    private static final HashMap<String, CircuitItem> openedCircuit = new HashMap<>();

    public static Object execute(Class clazz, String functionName, Object instance, Object defaultReturnValue, Object... parameters)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        Method method = ReflectionHelper.getMethod(clazz, functionName);
        String itemName = method.getDeclaringClass().getTypeName() + "." + method.getName();
        try {
            if (openedCircuit.containsKey(itemName)) {
                if (openedCircuit.get(itemName).isTimedOut())
                    openedCircuit.remove(itemName);
                else
                    return defaultReturnValue;
            }
            if (!method.isAccessible())
                method.setAccessible(true);
            return method.invoke(instance, parameters);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            CircuitItem item = new CircuitItem();
            item.open();
            openedCircuit.put(itemName, item);
            return defaultReturnValue;
        }
    }
}
