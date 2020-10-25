package pub.ants.netty.fourthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author magw
 * @version 1.0
 * @date 2020/10/26 上午12:26
 * @description: No Description
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 默认：当事件被触发时，会转下一个handler
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;

            String eventType = null;
            switch (event.state()) {
                case READER_IDLE: {
                    eventType = "读空闲";
                    break;
                }
                case WRITER_IDLE: {
                    eventType = "写空闲";
                    break;
                }
                case ALL_IDLE: {
                    eventType = "读写空闲";
                    break;
                }
            }
            System.out.println(ctx.channel().remoteAddress()+"超时事件： "+eventType);
            ctx.channel().close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
