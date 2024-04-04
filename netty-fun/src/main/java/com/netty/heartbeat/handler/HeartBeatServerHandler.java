package com.netty.heartbeat.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/4 10:41 下午
 */
public class HeartBeatServerHandler extends SimpleChannelInboundHandler<String> {

    private int timeout = 0;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("server received: " + msg);
        if ("heart beat package...".equals(msg)) {
            ctx.writeAndFlush("ok");
        } else {
            System.out.println("other msg...");
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent) evt;

        switch (event.state()) {
            case READER_IDLE:
                System.out.println("server read idle...");
                timeout++;
                break;
            case WRITER_IDLE:
                System.out.println("server write idle...");
                break;
            case ALL_IDLE:
                System.out.println("server all idle...");
                break;
            default:
                break;
        }

        System.out.println(ctx.channel().localAddress() + " timeout event" + " " + event.state());
        if (timeout > 3) {
            System.out.println("server timeout over 3 times, closing...");
            ctx.channel().writeAndFlush("idle close...");
            ctx.close();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channel active..." + ctx.channel().localAddress());
    }
}
