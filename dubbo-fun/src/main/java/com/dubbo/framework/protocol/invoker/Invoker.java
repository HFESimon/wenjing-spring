package com.dubbo.framework.protocol.invoker;

import com.dubbo.framework.protocol.dto.Invocation;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/3 1:30 下午
 */
public interface Invoker {

    /**
     *
     * @param invocation
     * @return
     */
    String invoke(Invocation invocation);
}
