package com.dubbo.framework.protocol.factory;

import com.dubbo.framework.URL;
import com.dubbo.framework.protocol.dto.Invocation;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 6:14 下午
 */
public interface Protocol {

    /**
     * 启动服务
     * @param url
     */
    void start(URL url);


    /**
     * 发送请求
     * @param url
     * @param invocation
     * @return
     */
    String send(URL url, Invocation invocation);
}
