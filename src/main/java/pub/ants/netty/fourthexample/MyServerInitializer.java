package pub.ants.netty.fourthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author magw
 * @version 1.0
 * @date 2020/10/26 上午12:21
 * @description: No Description
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 空闲状态检测处理器,netty本身提供
        pipeline.addLast("idleStateHandler",new IdleStateHandler(5,7,10, TimeUnit.SECONDS));
        pipeline.addLast("myServerHandler",new MyServerHandler());
    }
}
