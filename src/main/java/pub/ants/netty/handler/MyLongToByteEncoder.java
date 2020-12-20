package pub.ants.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author magw
 * @version 1.0
 * @date 2020/12/20 下午5:20
 * @description: No Description
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("encode invoked!");
        System.out.println(msg);
        out.writeLong(msg);

    }
}
