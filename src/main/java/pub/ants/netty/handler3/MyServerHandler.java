package pub.ants.netty.handler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import java.util.UUID;

/**
 * @author magw
 * @version 1.0
 * @date 2020/12/20 下午10:28
 * @description: No Description
 */
public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("服务端接受到的数据");
        System.out.println("长度："+length);
        System.out.println("内容："+new String(content, CharsetUtil.UTF_8));

        System.out.println("服务端接受消息的数量："+(++this.count));

        String  responseMessage = UUID.randomUUID().toString();
        int responseLength = responseMessage.length();
        byte[] responseContent = responseMessage.getBytes();

        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(responseLength);
        personProtocol.setContent(responseContent);

        ctx.writeAndFlush(personProtocol);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
