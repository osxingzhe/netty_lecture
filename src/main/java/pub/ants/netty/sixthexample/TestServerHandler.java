package pub.ants.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pub.ants.netty.sixthexample.MyDataInfo.MyMessage;
import pub.ants.netty.sixthexample.MyDataInfo.MyMessage.DataType;


/**
 * @author magw
 * @version 1.0
 * @date 2020/11/21 上午9:56
 * @description: No Description
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {
        DataType dataType = msg.getDataType();
        if (dataType == DataType.PersonType) {
            System.out.println(msg.getPerson().getName());
        } else if (dataType == DataType.DogType) {
            System.out.println(msg.getDog().getAge());
        } else {
            System.out.println(msg.getCat().getCity());

        }
    }
}
