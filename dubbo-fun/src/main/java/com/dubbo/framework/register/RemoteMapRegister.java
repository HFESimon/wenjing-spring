package com.dubbo.framework.register;

import com.dubbo.framework.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 2:29 下午
 */

public class RemoteMapRegister {

    private static final Map<String, List<URL>> REGISTER_MAP = new HashMap<>();

    public static void register(String interfaceName, URL url) {
        List<URL> urls = REGISTER_MAP.getOrDefault(interfaceName, new ArrayList<>());
        urls.add(url);
        REGISTER_MAP.put(interfaceName, urls);
    }

    public static List<URL> get(String interfaceName) {
        return REGISTER_MAP.get(interfaceName);
    }
}
