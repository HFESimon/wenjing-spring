package com.netty.chat.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/4 8:54 下午
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush("[客户端]" + channel.remoteAddress() + "发送消息:" + msg + "\n");
            } else {
                ch.writeAndFlush("[自己]发送消息:" + msg + "\n");
            }
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 将加入聊天的信息推送给其他在线客户
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "加入聊天室" + simpleDateFormat.format(new java.util.Date()) + "\n");
        channelGroup.add(channel);

        System.out.println(channel.remoteAddress() + " 加入聊天室\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "离开聊天室" + simpleDateFormat.format(new java.util.Date()) + "\n");
        System.out.println(channel.remoteAddress() + " 离开聊天室\n");
        System.out.println("当前在线人数: " + channelGroup.size());
    }
}
