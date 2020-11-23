package pub.ants.netty.sixthexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author magw
 * @version 1.0
 * @date 2020/10/26 上午12:17
 * @description: No Description
 *  使用protobuf的最佳时间
 *   1, 使用git submodule 新建个工程存.proto文件、另外一个项目存生成类
 *   2. git subtree
 */
public class TestServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    // info级别日志，只针对boosGroup
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new TestServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
