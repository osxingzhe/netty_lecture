package pub.ants.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author magw
 * @version 1.0
 * @date 2020/12/20 上午10:17
 * @description: No Description
 */
public class ByteBufTest1 {

    public static void main(String[] args) {
        // 根据字符集字节*原来字节数=capacity  底层数组可以自动扩容
        ByteBuf buffer = Unpooled.copiedBuffer("张hello world", CharsetUtil.UTF_8);

        if(buffer.hasArray()){
            System.out.println("堆上缓冲");
            byte[] content = buffer.array();
            System.out.println(content.length);
            System.out.println(new String(content,0,buffer.writerIndex(),CharsetUtil.UTF_8));

            System.out.println(buffer);

            System.out.println(buffer.arrayOffset());
            System.out.println(buffer.readerIndex());
            System.out.println(buffer.writerIndex());
            System.out.println(buffer.capacity());
            // buffer.readableBytes() = buffer.writerIndex()
            System.out.println(buffer.readableBytes());
            System.out.println("----------");

            for (int i = 0; i < buffer.readableBytes(); i++) {
                System.out.println((char)buffer.getByte(i));
            }
            System.out.println(buffer.getCharSequence(0,4,CharsetUtil.UTF_8));
            System.out.println(buffer.getCharSequence(4,6,CharsetUtil.UTF_8));
        }

    }
}
