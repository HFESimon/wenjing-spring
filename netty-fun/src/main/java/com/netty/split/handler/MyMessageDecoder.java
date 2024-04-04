package com.netty.split.handler;

import com.netty.split.dto.MyMessagePackage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/4 10:01 下午
 */
public class MyMessageDecoder extends ByteToMessageDecoder {

    private int length = 0;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println(in);
        if (in.readableBytes() >= 4) {

            if (length == 0) {
                length = in.readInt();
            }

            if (in.readableBytes() >= length) {
                byte[] bytes = new byte[length];
                in.readBytes(bytes);
                MyMessagePackage messagePackage = new MyMessagePackage();
                messagePackage.setLen(length);
                messagePackage.setContent(bytes);
                out.add(messagePackage);
                length = 0;
            } else {
                System.out.println("readable data is no enough... waiting...");
                return;
            }
        }
    }
}
