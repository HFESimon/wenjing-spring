package com.dubbo.provider;

import com.dubbo.framework.URL;
import com.dubbo.framework.protocol.common.Protocol;
import com.dubbo.framework.protocol.common.ProtocolFactory;
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
 * @since 2024/4/18 8:08 下午
 */
public class Provider2 {

    public static void main(String[] args) throws UnknownHostException {

        String interfaceName = HelloApiService.class.getName();
        URL url = new URL(InetAddress.getLocalHost().getHostAddress(), 8082);

        // 本地注册 注册处理逻辑实现类
        LocalRegister.register(interfaceName, HelloApiServiceImpl.class);
        // 注册到注册中心 注册服务的 ip和端口
        ZookeeperRegister.register(interfaceName, url);

        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);

        System.out.println(String.format("success, 成功暴露 %s 服务，地址为 %s", interfaceName, url.toString()));
    }
}
