package com.dubbo.framework.protocol.dubbo.invoker;

import com.dubbo.framework.URL;
import com.dubbo.framework.protocol.dto.Invocation;
import com.dubbo.framework.protocol.invoker.Invoker;
import com.dubbo.framework.protocol.netty.NettyClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/3 1:00 下午
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DubboInvoker implements Invoker {

    private URL url;

    @Override
    public String invoke(Invocation invocation) {
        NettyClient client = new NettyClient();
        return client.send(url.getHostname(), url.getPort(), invocation);
    }
}
