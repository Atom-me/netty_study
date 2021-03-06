package com.atom.netty.first_example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Atom
 */
public class TestServer {
    public static void main(String[] args) throws InterruptedException {
        //bossGroup 主要接收客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // workerGroup 处理任务的
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //具体属性装配
            final ChannelFuture channelFuture = serverBootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer())
                    .bind(8899)
                    .sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
