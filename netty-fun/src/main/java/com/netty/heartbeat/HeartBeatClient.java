package com.netty.heartbeat;

import com.netty.heartbeat.handler.HeartBeatClientHandler;
import com.netty.splitpackage.handler.MyClientHandler;
import com.netty.splitpackage.handler.MyMessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Random;
import java.util.Scanner;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/4 10:41 下午
 */
public class HeartBeatClient {

    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("encoder", new StringEncoder());
                            ch.pipeline().addLast("decoder", new StringDecoder());
                            ch.pipeline().addLast(new HeartBeatClientHandler());
                        }
                    });

            ChannelFuture sync = bootstrap.connect("127.0.0.1", 9000).sync();

            Channel channel = sync.channel();
            System.out.println("======" + channel.localAddress() + "======");

            String msg = "heart beat package...";
            while(true) {
                int i = new Random().nextInt(8);
                Thread.sleep(1000 * i);
                channel.writeAndFlush(msg);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
