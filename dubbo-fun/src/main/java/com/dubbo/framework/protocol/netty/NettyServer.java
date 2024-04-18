package com.dubbo.framework.protocol.netty;

import com.dubbo.framework.protocol.netty.handler.DispatcherHandler;
import com.dubbo.framework.protocol.netty.handler.NettyServerHandler;
import com.dubbo.framework.protocol.netty.handler.RequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.concurrent.DefaultThreadFactory;
/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 10:49 上午
 */

public class NettyServer {

    private static final Integer threadNum = 10;

    public void start(String host, int port) {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("netty-server-boss", true));
        EventLoopGroup workerGroup = new NioEventLoopGroup(threadNum, new DefaultThreadFactory("netty-server-worker", true));

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("decoder", new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                            ch.pipeline().addLast("encoder", new ObjectEncoder());
                            ch.pipeline().addLast("handler", new NettyServerHandler(new DispatcherHandler(new RequestHandler())));
                        }
                    });
            // netty server start
            ChannelFuture channelFuture = serverBootstrap.bind(host, port).sync();

            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
