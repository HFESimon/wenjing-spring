package com.dubbo.framework.register;

import com.alibaba.fastjson.JSONObject;
import com.dubbo.framework.URL;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 2:34 下午
 */
public class ZookeeperRegister {

    static CuratorFramework client;

    static Map<String, List<URL>> urlCache = new HashMap<>();

    static {
        client = CuratorFrameworkFactory.newClient("localhost:2181", new RetryNTimes(3, 1000));
        client.start();
    }

    private static final Map<String, List<URL>> REGISTER = new HashMap<>();

    public static void register(String interfaceName, URL url) {
        try {
            String result = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                    .forPath(String.format("/dubbo/service/%s/%s", interfaceName, JSONObject.toJSONString(url)), null);
            System.out.println("register success:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<URL> get(String interfaceName) {
        if (urlCache.containsKey(interfaceName)) {
            return urlCache.get(interfaceName);
        }

        List<URL> urls = new ArrayList<>();

        try {
             List<String> children = client.getChildren().forPath(String.format("/dubbo/service/%s", interfaceName));
             for (String child : children) {
                 URL url = JSONObject.parseObject(child, URL.class);
                 urls.add(url);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        urlCache.put(interfaceName, urls);
        return urls;
    }
}
