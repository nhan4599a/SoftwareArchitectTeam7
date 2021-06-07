package CircuitBreaker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static Reflection.ReflectionHelper.getMethod;
import static Reflection.ReflectionHelper.invokeMethod;

@SuppressWarnings("all")
public final class CircuitBreakerHelper {
    private static final HashMap<String, CircuitItem> openedCircuit = new HashMap<>();

    public static Object execute(Class clazz, String functionName, Object instance,
                                 Object defaultReturnValue, Object... parameters)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        Method method = getMethod(clazz, functionName);
        String itemName = clazz.getTypeName() + "." + method.getName();
        try {
            if (openedCircuit.containsKey(itemName)) {
                if (openedCircuit.get(itemName).isTimedOut())
                    openedCircuit.remove(itemName);
                else
                    return defaultReturnValue;
            }
            return invokeMethod(method, instance, parameters);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            CircuitItem item = new CircuitItem();
            item.open();
            openedCircuit.put(itemName, item);
            return defaultReturnValue;
        }
    }
}
