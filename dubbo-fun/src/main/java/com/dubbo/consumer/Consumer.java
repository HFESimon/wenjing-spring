package com.dubbo.consumer;

import com.dubbo.framework.proxy.ProxyFactory;
import com.dubbo.provider.api.HelloApiService;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 12:32 上午
 */
public class Consumer {

    public static void main(String[] args) {

        HelloApiService helloApiService = ProxyFactory.getProxy(HelloApiService.class);

        String res = helloApiService.sayHello("wenjing is handsome");
        System.out.println(res);
    }
}
