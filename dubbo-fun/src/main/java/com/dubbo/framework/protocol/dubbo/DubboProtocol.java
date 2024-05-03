package com.dubbo.framework.protocol.dubbo;

import com.dubbo.framework.URL;
import com.dubbo.framework.protocol.Protocol;
import com.dubbo.framework.protocol.dubbo.invoker.DubboInvoker;
import com.dubbo.framework.protocol.invoker.Invoker;
import com.dubbo.framework.protocol.netty.NettyServer;
import com.dubbo.framework.register.LocalRegister;
import com.dubbo.framework.register.ZookeeperRegister;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 6:17 下午
 */

public class DubboProtocol implements Protocol {

    @Override
    public void export(URL url) {
        // 本地注册 注册处理逻辑实现类
        LocalRegister.register(url.getInterfaceName(), url.getImplClass());
        // 注册到注册中心 注册服务的 ip和端口
        ZookeeperRegister.register(url.getInterfaceName(), url);
        System.out.printf("success, 成功暴露 %s 服务，地址为 %s%n", url.getInterfaceName(), url.toString());

        // 启动netty服务
        new NettyServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public Invoker refer(URL url) {
        return new DubboInvoker(url);
    }
}
