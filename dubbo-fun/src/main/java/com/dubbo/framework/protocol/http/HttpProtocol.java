package com.dubbo.framework.protocol.http;

import com.dubbo.framework.URL;
import com.dubbo.framework.protocol.Protocol;
import com.dubbo.framework.protocol.invoker.Invoker;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/3 2:23 下午
 */
public class HttpProtocol implements Protocol {
    
    @Override
    public void export(URL url) {
        // TODO: 2024/5/3  
    }

    @Override
    public Invoker refer(URL url) {
        // TODO: 2024/5/3  
        return null;
    }
}
