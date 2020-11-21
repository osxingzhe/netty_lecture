package pub.ants.netty.fifthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author magw
 * @version 1.0
 * @date 2020/10/27 上午12:11
 * @description: No Description
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        // 以块的方式写的处理器
        pipeline.addLast("chunkedWriteHandler",new ChunkedWriteHandler());
        // 对消息进行聚合，在netty中的http编程中常用
        // 会把请求分段聚合到一起，形成一个完成的请求或响应 字节长度
        pipeline.addLast("httpObjectAggregator",new HttpObjectAggregator(8192));
        pipeline.addLast("webSocketServerProtocolHandler",new WebSocketServerProtocolHandler("/ws"));

        //自定义websocket处理器
        pipeline.addLast("",null);
    }
}
