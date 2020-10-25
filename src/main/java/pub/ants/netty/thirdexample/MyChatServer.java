package pub.ants.netty.thirdexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author magw
 * @version 1.0
 * @date 2020/10/25 下午8:30
 * @description: No Description
 *  服务器首先启动
 *  n个客户端进行连接
 *  a客户端进行连接，不需要别的操作
 *  b客户端进行连接，在服务器端打印b上线，广播a客户端b以上先
 *  c客户端进行连接，在服务器端打印c上线，广播a、b客户端c以上先
 *  当a发送消息，服务器打印a发送信息，广播b、c发送消息
 */
public class MyChatServer {
    public static void main(String[] args) throws Exception{
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyChatServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
