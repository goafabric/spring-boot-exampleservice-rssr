package org.goafabric.common.spring.service.resilience;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by amautsch on 05.05.2015.
 */

public class ListCacheKeyGenerator implements KeyGenerator {
    public Object generate(Object target, Method method, Object... params) {
        return createKey(method.getDeclaringClass().getSimpleName(), method.getName(),
                params);
    }

    public static List<Object> createKey(
            final String className, final String methodName,  Object... params) {
        final List<Object> key = new ArrayList<Object>();
        key.add(className);
        key.add(methodName);
        key.addAll(Arrays.asList(params));
        return key;
    }
}
