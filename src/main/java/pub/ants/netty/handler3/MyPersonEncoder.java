package pub.ants.netty.handler3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author magw
 * @version 1.0
 * @date 2020/12/20 下午10:26
 * @description: No Description
 */
public class MyPersonEncoder extends MessageToByteEncoder<PersonProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out)
        throws Exception {

        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
