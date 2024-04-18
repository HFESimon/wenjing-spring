package com.dubbo.framework.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 2:26 下午
 */

public class LocalRegister {

    private static final Map<String, Class<?>> REGISTRY = new HashMap<>();

    public static void register(String interfaceName, Class<?> implClass) {
        REGISTRY.put(interfaceName, implClass);
    }

    public static Class<?> get(String interfaceName) {
        return REGISTRY.get(interfaceName);
    }
}
