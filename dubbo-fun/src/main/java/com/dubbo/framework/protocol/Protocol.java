package com.dubbo.framework.protocol;

import com.dubbo.framework.URL;
import com.dubbo.framework.protocol.invoker.Invoker;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 6:14 下午
 */
public interface Protocol {

    /**
     * 服务导出
     * @param url
     */
    void export(URL url);

    /**
     * 服务引入
     * @param url
     * @return
     */
    Invoker refer(URL url);
}
