package com.netty.split.handler;

import com.netty.split.dto.MyMessagePackage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/4 10:02 下午
 */
public class MyClientHandler extends SimpleChannelInboundHandler<MyMessagePackage> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyMessagePackage messagePackage = getMyMessagePackage();

        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(messagePackage);
        }
    }

    private MyMessagePackage getMyMessagePackage() {
        MyMessagePackage messagePackage = new MyMessagePackage();
        String msg = "hello world";
        messagePackage.setLen(msg.getBytes(CharsetUtil.UTF_8).length);
        messagePackage.setContent(msg.getBytes(CharsetUtil.UTF_8));
        return messagePackage;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessagePackage msg) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
    }
}
