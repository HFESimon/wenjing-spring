package com.dubbo.framework.protocol.netty.task;

import com.dubbo.framework.protocol.dto.Invocation;
import com.dubbo.framework.protocol.netty.handler.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 10:51 上午
 */

public class Task implements Runnable {

    private Invocation invocation;

    private ChannelHandler channelHandler;

    private ChannelHandlerContext ctx;

    public Task(Invocation invocation, ChannelHandler channelHandler, ChannelHandlerContext ctx) {
        this.invocation = invocation;
        this.channelHandler = channelHandler;
        this.ctx = ctx;
    }

    @Override
    public void run() {
        try {
            channelHandler.handler(ctx, invocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
