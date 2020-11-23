package pub.ants.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Random;
import pub.ants.netty.sixthexample.MyDataInfo.MyMessage.DataType;
import pub.ants.netty.sixthexample.MyDataInfo.Person;
import pub.ants.protobuf.DataInfo;


/**
 * @author magw
 * @version 1.0
 * @date 2020/11/21 上午9:56
 * @description: No Description
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyDataInfo.MyMessage myMessage = null;
        int randomInt = new Random().nextInt(3);
        if(randomInt == 0){
            myMessage = MyDataInfo.MyMessage.newBuilder()
                .setDataType(DataType.PersonType)
                .setPerson(MyDataInfo.Person.newBuilder().setName("zhangsan")
                .setAge(18).setAddress("sh").build()).build();
        } else if(randomInt == 1){
            myMessage = MyDataInfo.MyMessage.newBuilder()
                .setDataType(DataType.DogType)
                .setDog(MyDataInfo.Dog.newBuilder().setName("dog")
                    .setAge(2).build()).build();
        }else{
            myMessage = MyDataInfo.MyMessage.newBuilder()
                .setDataType(DataType.CatType)
                .setCat(MyDataInfo.Cat.newBuilder().setName("cat")
                    .setCity("bj").build()).build();
        }

        ctx.channel().writeAndFlush(myMessage);
    }
}
