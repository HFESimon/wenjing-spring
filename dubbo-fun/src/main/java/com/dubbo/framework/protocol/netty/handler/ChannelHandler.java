package com.dubbo.framework.protocol.netty.handler;

import com.dubbo.framework.protocol.dto.Invocation;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 10:44 上午
 */
public interface ChannelHandler {

    void handler(ChannelHandlerContext ctx, Invocation invocation) throws Exception;
}
