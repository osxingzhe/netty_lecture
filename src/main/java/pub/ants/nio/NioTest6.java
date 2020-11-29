package pub.ants.nio;

import java.nio.ByteBuffer;

/**
 * @author: magaowei
 * @version: 1.0
 * @date: 2020/11/29 9:36 上午
 * @description:
 *   使用slice进行分片，新的buffer和原buffer共享原先数组
 */
public class NioTest6 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        // 分片
        buffer.position(2);
        buffer.limit(6);
        ByteBuffer newBuffer = buffer.slice();

        for (int i = 0; i < newBuffer.capacity(); i++) {
            byte b = newBuffer.get();
            b *= 2;
            newBuffer.put(i, b);
        }

        // 重新定位buffer
        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
        buffer.clear();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }

}
