package pub.ants.nio;

import java.nio.ByteBuffer;

/**
 * @author: magaowei
 * @version: 1.0
 * @date: 2020/11/29 9:24 上午
 * @description:
 *  ByteBuffer类型化的put get方法
 *  ByteBuffer 支持基础类put，但是需要按顺序get  类似io中的类
 *      DataOutputStream dos = new DataOutputStream(new FileOutputStream("dos.txt"));
 *      DataInputStream dis = new  DataInputStream(new FileInputStream("dos.txt"));
 */
public class NioTest5 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(1);
        buffer.putFloat(2F);
        buffer.putLong(345674567L);
        buffer.putDouble(2.3456D);
        buffer.putShort((short)1);
        buffer.putChar('h');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getFloat());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());
    }
}
