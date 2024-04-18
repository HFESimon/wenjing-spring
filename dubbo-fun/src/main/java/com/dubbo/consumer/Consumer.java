package com.dubbo.consumer;

import com.dubbo.framework.protocol.dto.Invocation;
import com.dubbo.framework.protocol.netty.NettyClient;
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

        for (int i = 0; i < 10; i++) {
            String res = helloApiService.sayHello("wenjing is handsome" + i);
            System.out.println(res);
        }
    }
}
