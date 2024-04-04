package com.netty.splitpackage.handler;

import com.netty.splitpackage.dto.MyMessagePackage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/4 10:01 下午
 */
public class MyMessageEncoder extends MessageToByteEncoder<MyMessagePackage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MyMessagePackage msg, ByteBuf out) throws Exception {
        Channel channel = ctx.channel();
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
