package com.netty.splitpackage.handler;

import com.netty.splitpackage.dto.MyMessagePackage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/4 10:01 下午
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MyMessagePackage> {

    private int count = 0;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessagePackage msg) throws Exception {
        System.out.println("server receive message!");
        System.out.println("length: " + msg.getLen());
        System.out.println("msg: " + new String(msg.getContent(), CharsetUtil.UTF_8));
        System.out.println("count: " + ++count);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active: " + ctx.channel().localAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel inactive: " + ctx.channel().localAddress());
    }
}
