package pub.ants.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.List;

/**
 * @author magw
 * @version 1.0
 * @date 2020/12/20 下午5:54
 * @description: No Description
 */
public class MyByteToLongDecoder2 extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
        throws Exception {
        System.out.println("MyByteToLongDecoder2");

        out.add(in.readLong());

    }
}
