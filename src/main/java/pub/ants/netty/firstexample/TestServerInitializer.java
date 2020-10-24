package pub.ants.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author magw
 * @version 1.0
 * @date 2020/10/24 下午10:36
 * @description: No Description
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 连接被创建就初始化管道
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 管道，不要使用单例，需要多实例
        // 添加netty、自定义的处理器
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        pipeline.addLast("testServerHandler",new TestServerHandler());
    }
}
