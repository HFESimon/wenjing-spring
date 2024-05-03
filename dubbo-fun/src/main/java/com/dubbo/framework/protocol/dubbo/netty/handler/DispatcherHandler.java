package com.dubbo.framework.protocol.dubbo.netty.handler;

import com.dubbo.common.ThreadPoolFactory;
import com.dubbo.framework.protocol.invoker.Invocation;
import com.dubbo.framework.protocol.dubbo.netty.task.Task;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ExecutorService;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 10:51 上午
 */

public class DispatcherHandler implements ChannelHandler {

    private ChannelHandler channelHandler;

    private ExecutorService executorService;

    private static final String DISPATCHER_NAME = "dispatcher";

    public DispatcherHandler(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
        this.executorService = ThreadPoolFactory.createThreadPool(DISPATCHER_NAME);
    }

    @Override
    public void handler(ChannelHandlerContext ctx, Invocation invocation) throws Exception {
        System.out.println("threadPool-execute");
        executorService.execute(new Task(invocation, channelHandler, ctx));
    }
}
