package vlu.architect.team7.partner.futa.service.Helper;

import java.util.HashMap;
import java.util.Map;

public final class MathHelper {

    private static final Map<Integer, Integer> memoize = new HashMap<>();

    public static int factorial(int number) {
        if (memoize.containsKey(number))
            return memoize.get(number);
        int result = 1;
        for (int i = 2; i <= number; i++) {
            result *= i;
            memoize.put(i, result);
        }
        return result;
    }

    public static int combinationSize(int k, int n) {
        if (n < k)
            throw new IllegalArgumentException("k must be greater than or equal to n!");
        return (factorial(n) / (factorial(n - k) * factorial(k)));
    }
}
