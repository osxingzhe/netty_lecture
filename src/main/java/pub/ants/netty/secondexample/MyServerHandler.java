package pub.ants.netty.secondexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author magw
 * @version 1.0
 * @date 2020/10/25 上午10:22
 * @description: No Description
 * 客户端和服务端 互相传字符串
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+", "+msg);

        ctx.channel().writeAndFlush("from server: "+ UUID.randomUUID());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 出现异常，关闭连接
        ctx.close();
    }
}
