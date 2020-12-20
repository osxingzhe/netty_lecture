package pub.ants.netty.handler3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.List;

/**
 * @author magw
 * @version 1.0
 * @date 2020/12/20 下午10:24
 * @description: No Description
 */
public class MyPersonDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
        throws Exception {
        System.out.println("MyPersonDecoder decode invoked!");

        int length = in.readInt();

        byte[] content = new byte[length];
        in.readBytes(content);

        PersonProtocol personProtocol= new PersonProtocol();
        personProtocol.setLength(length);
        personProtocol.setContent(content);

        out.add(personProtocol);
    }
}
