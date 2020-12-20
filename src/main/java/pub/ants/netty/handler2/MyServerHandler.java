package pub.ants.netty.handler2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import java.util.UUID;

/**
 * @author magw
 * @version 1.0
 * @date 2020/10/25 上午10:22
 * @description: No Description
 * 客户端和服务端 互相传字符串
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);

        String message = new String (bytes, CharsetUtil.UTF_8);
        System.out.println("服务端接受到的消息内容：" + message);
        System.out.println("服务端接受消息数量：" + (++count));

        ByteBuf response = Unpooled.copiedBuffer(UUID.randomUUID().toString(),CharsetUtil.UTF_8);
        ctx.writeAndFlush(response);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 出现异常，关闭连接
        ctx.close();
    }
}
