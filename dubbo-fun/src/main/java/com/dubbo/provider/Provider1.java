package com.dubbo.provider;

import com.dubbo.framework.URL;
import com.dubbo.framework.protocol.Protocol;
import com.dubbo.framework.protocol.factory.ProtocolFactory;
import com.dubbo.framework.register.LocalRegister;
import com.dubbo.framework.register.ZookeeperRegister;
import com.dubbo.provider.api.HelloApiService;
import com.dubbo.provider.impl.HelloApiServiceImpl1;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 12:32 上午
 */

public class Provider1 {

    public static void main(String[] args) throws UnknownHostException {

        URL url = new URL("dubbo", InetAddress.getLocalHost().getHostAddress(), 8081,
                HelloApiService.class.getName(), HelloApiServiceImpl1.class);

        Protocol protocol = ProtocolFactory.getProtocol(url.getProtocol());

        protocol.export(url);
    }
}
