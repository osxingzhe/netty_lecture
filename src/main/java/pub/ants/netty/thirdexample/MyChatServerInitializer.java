package pub.ants.netty.thirdexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author magw
 * @version 1.0
 * @date 2020/10/25 下午8:34
 * @description: No Description
 */
public class MyChatServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("delimiterBasedFrameDecoder",new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
        pipeline.addLast("stringDecoder",new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("stringEncoder",new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast("myChatServerHandler",new MyChatServerHandler());
    }
}
