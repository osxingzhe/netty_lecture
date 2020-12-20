package pub.ants.netty.handler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import java.util.UUID;

/**
 * @author magw
 * @version 1.0
 * @date 2020/12/20 下午10:33
 * @description: No Description
 */
public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("客户端接受到的数据");
        System.out.println("长度："+length);
        System.out.println("内容："+new String(content, CharsetUtil.UTF_8));

        System.out.println("客户接受消息的数量："+(++this.count));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0;i<10;i++){
            String message = "send from client: "+i;
            int length = message.length();
            byte[] content = message.getBytes();

            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setLength(length);
            personProtocol.setContent(content);
            ctx.writeAndFlush(personProtocol);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
