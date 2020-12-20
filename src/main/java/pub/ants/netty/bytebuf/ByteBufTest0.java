package pub.ants.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author magw
 * @version 1.0
 * @date 2020/12/20 上午9:36
 * @description: No Description
 *
 * 两个索引变量支持读写，不需要filp进行读写转换
 * <pre>
 *      +-------------------+------------------+------------------+
 *      | discardable bytes |  readable bytes  |  writable bytes  |
 *      |                   |     (CONTENT)    |                  |
 *      +-------------------+------------------+------------------+
 *      |                   |                  |                  |
 *      0      <=      readerIndex   <=   writerIndex    <=    capacity
 * </pre>
 * 池化 非池化
 * 堆中 非堆中
 */
public class ByteBufTest0 {

    public static void main(String[] args) {
        /**
         * netty存储数据有两种，一种池化用完保存，另外一种非池化用完销毁
         */
        ByteBuf buffer = Unpooled.buffer(10);

        for(int i = 0; i< buffer.capacity(); i++){
            buffer.writeByte(i);
        }

        for(int i = 0; i< buffer.capacity(); i++){
            System.out.println(buffer.getByte(i));
            //System.out.println(buffer.readByte());
        }
        System.out.println(buffer.readByte());
    }
}
