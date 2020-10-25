package pub.ants.netty.firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author magw
 * @version 1.0
 * @date 2020/10/24 下午10:40
 * @description: No Description
 */
public class TestServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取客户端发送过来的请求，并且向客户端返回响应的方法
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        // 打印msg类
        System.out.println(msg.getClass());
        // 打印远程的客户端地址
        System.out.println(ctx.channel().remoteAddress());

        Thread.sleep(20000);

        if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;
            System.out.println("请求方法名：" + httpRequest.method().name());
            String uri = httpRequest.uri();

            if ("/favicon.ico".equals(uri)) {
                System.out.println("请求favicon.ico");
                return;
            }

            // 构造返回对象需要使用byteBuf
            ByteBuf content = Unpooled.copiedBuffer("hello,world", CharsetUtil.UTF_8);
            // 构造响应内容
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            // 设置response的头信息
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 写回客户端并刷新
            ctx.writeAndFlush(response);
            // 异步关闭通道
            ctx.channel().close();
        }
    }

    /**
     * 当通道活动的时候调用这个方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel channelActive");
        ctx.fireChannelActive();
    }

    /**
     * 当注册通道调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel channelRegistered");
        super.channelRegistered(ctx);
    }

    /**
     * 通道被添加
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel handlerAdded");
        super.handlerAdded(ctx);
    }

    /**
     * 通道处于不活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel channelInactive");
        super.channelInactive(ctx);
    }

    /**
     * 通道取消注册
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel channelUnregistered");
        super.channelUnregistered(ctx);
    }
}
