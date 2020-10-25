package pub.ants.netty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


/**
 * @author magw
 * @version 1.0
 * @date 2020/10/25 下午8:39
 * @description: No Description
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    // 保存一个个channel对象
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(x -> {
            if (x == channel) {
                // 当前channel是自己
                x.writeAndFlush("[自己]: " + msg + "\n");
            } else {
                // 不是自己客户端
                x.writeAndFlush(channel.remoteAddress() + " 发送消息: " + msg + "\n");
            }
        });
    }

    /**
     * 服务器端和客户端已经建立连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[服务器]-" + channel.remoteAddress() + "加入\n");
        channelGroup.add(channel);
    }

    /**
     * 连接断开
     * 当客户端与服务器端建立长链接，这时不退出应用关机或开机飞行模式，是不会触发handlerRemoved调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[服务器]-" + channel.remoteAddress() + "离开\n");
        // netty会自动调用，无需手动触发
        // channelGroup.remove(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("[服务器]-" + channel.remoteAddress() + "上线\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("[服务器]-" + channel.remoteAddress() + "下线\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
