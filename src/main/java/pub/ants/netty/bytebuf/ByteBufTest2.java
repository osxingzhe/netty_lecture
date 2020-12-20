package pub.ants.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import java.util.Iterator;

/**
 * @author magw
 * @version 1.0
 * @date 2020/12/20 上午10:48
 * @description: No Description
 * 混合缓冲区
 */
public class ByteBufTest2 {

    public static void main(String[] args) {
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

        ByteBuf heapBuf = Unpooled.buffer(10);
        ByteBuf directBuf = Unpooled.directBuffer(10);

        compositeByteBuf.addComponents(heapBuf, directBuf);

        Iterator<ByteBuf> iterator = compositeByteBuf.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
