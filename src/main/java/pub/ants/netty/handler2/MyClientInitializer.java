package pub.ants.netty.handler2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import pub.ants.netty.handler.MyByteToLongDecoder2;

/**
 * @author magw
 * @version 1.0
 * @date 2020/10/25 下午7:18
 * @description: No Description
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("myClientHandler", new MyClientHandler());
    }
}
