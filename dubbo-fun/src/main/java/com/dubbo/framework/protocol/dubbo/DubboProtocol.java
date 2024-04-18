package com.dubbo.framework.protocol.dubbo;

import com.dubbo.framework.URL;
import com.dubbo.framework.protocol.dto.Invocation;
import com.dubbo.framework.protocol.common.Protocol;
import com.dubbo.framework.protocol.netty.NettyClient;
import com.dubbo.framework.protocol.netty.NettyServer;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 6:17 下午
 */

public class DubboProtocol implements Protocol {

    @Override
    public void start(URL url) {
        NettyServer server = new NettyServer();
        server.start(url.getHostName(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        NettyClient client = new NettyClient();
        return client.send(url.getHostName(), url.getPort(), invocation);
    }
}
