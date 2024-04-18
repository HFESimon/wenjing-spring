package com.dubbo.provider;

import com.dubbo.framework.URL;
import com.dubbo.framework.protocol.netty.NettyServer;
import com.dubbo.framework.register.LocalRegister;
import com.dubbo.framework.register.ZookeeperRegister;
import com.dubbo.provider.api.HelloApiService;
import com.dubbo.provider.impl.HelloApiServiceImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 12:32 上午
 */

public class Provider {

    public static void main(String[] args) throws UnknownHostException {

        String interfaceName = HelloApiService.class.getName();
        URL url = new URL(InetAddress.getLocalHost().getHostAddress(), 8081);

        LocalRegister.register(interfaceName, HelloApiServiceImpl.class);
        ZookeeperRegister.register(interfaceName, url);

        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostName(), url.getPort());

        System.out.println(String.format("success, 成功暴露 %s 服务，地址为 %s", interfaceName, url.toString()));
    }
}
