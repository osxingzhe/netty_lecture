package pub.ants.nio;

import java.nio.ByteBuffer;

/**
 * @author: magaowei
 * @version: 1.0
 * @date: 2020/11/29 9:54 上午
 * @description: 只读buffer，两个buffer共享原底层数据
 * 可以随时将普通buffer调用asReadOnlyBuffer()将普通buffer转为只读buffer，但是不能讲只读buffer转换为普通buffer
 */
public class NioTest7 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        buffer.position(0);
        buffer.put((byte)120);
        readOnlyBuffer.clear();
        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }
    }
}
