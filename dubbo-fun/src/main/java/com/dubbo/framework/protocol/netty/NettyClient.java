package com.dubbo.framework.protocol.netty;

import com.dubbo.common.ThreadPoolFactory;
import com.dubbo.framework.protocol.dto.Invocation;
import com.dubbo.framework.protocol.netty.handler.NettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 10:49 上午
 */
public class NettyClient {

    private NettyClientHandler nettyClientHandler = null;

    private static ExecutorService executor = ThreadPoolFactory.createThreadPool("client");

    public void start(String host, int port) {
        EventLoopGroup group = new NioEventLoopGroup();
        nettyClientHandler = new NettyClientHandler();
        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("encoder", new ObjectEncoder());
                            ch.pipeline().addLast("decoder", new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                            ch.pipeline().addLast("handler", nettyClientHandler);
                        }
                    });

            // client start
            bootstrap.connect(host, port).sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String send(String host, int port, Invocation invocation) {
        if (nettyClientHandler == null) {
            start(host, port);
        }

        nettyClientHandler.setInvocation(invocation);

        try {
            return (String) executor.submit(nettyClientHandler).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
