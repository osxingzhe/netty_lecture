package pub.ants.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author magw
 * @version 1.0
 * @date 2020/10/24 下午10:22
 * @description: No Description
 */
public class TestServer {

    public static void main(String[] args) throws InterruptedException {
        // 定义事件循环组,都是死循环
        // boosGroup处理连接，将连接传给workGroup
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        // workGroup对连接进行处理
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            // netty提供用于启动服务端类，轻松的启动服务端代码
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // netty中方法链
            serverBootstrap.group(boosGroup, workGroup)
                    // 管道，实例是用反射创建的
                    .channel(NioServerSocketChannel.class)
                    // 子处理器
                    .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 优雅关机
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
