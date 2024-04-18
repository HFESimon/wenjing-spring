package com.dubbo.framework.protocol.netty.handler;

import com.dubbo.framework.protocol.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 10:50 上午
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable<Object> {

    private Invocation invocation;

    private ChannelHandlerContext context;

    private String result;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        notify();
    }

    @Override
    public Object call() throws Exception {
        context.writeAndFlush(invocation);
        wait();
        return result;
    }

    public void setInvocation(Invocation invocation) {
        this.invocation = invocation;
    }
}
