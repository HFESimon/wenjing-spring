package com.dubbo.framework.protocol.netty.handler;

import com.dubbo.framework.protocol.dto.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 10:50 上午
 */

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandler channelHandler;

    public NettyServerHandler(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("NettyServerHandler channelRead");
        channelHandler.handler(ctx, (Invocation) msg);
    }
}
