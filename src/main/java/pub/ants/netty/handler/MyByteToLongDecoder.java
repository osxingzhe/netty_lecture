package pub.ants.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * @author magw
 * @version 1.0
 * @date 2020/12/20 下午5:18
 * @description: No Description
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
        throws Exception {
        System.out.println("decode invoked!");
        System.out.println(in.readableBytes());

        if(in.readableBytes() >= 8){
            out.add(in.readLong());
        }
    }
}
