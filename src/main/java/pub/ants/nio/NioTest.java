package pub.ants.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author magw
 * @version 1.0
 * @date 2020/11/23 下午10:39
 * @description: No Description
 *  java.io中最为核心的一个概念是流，面向流编程，Java中，一个流要么是输入流要么是输出流，不可能即使输入流又是输出流
 *  java.nio中拥有3个核心概念：selector、channel与buffer，在nio中，是面向快或是缓冲去编程的。
 *      buffer本身是块内存，底层是数组，数据的读写都是通过buffer实现的。
 *  除了数组之外，Buffer还提供流对数据的结构化访问方式，并且可以追踪到系统的读写过程。
 *  Java中的7种（除了boolean）原生数据类型都提供各自对应的buffer类型。
 *
 *  channel指的是可以向其写入数据或是从中读取数据的对象，它蕾丝java.io包中的stream
 *  所有数据的读写都是通过Buffer来进行的，永远不会出现直接向channel写入数据的情况，或是直接从channel读取数据的情况。
 *  与stream不同的是，channel是双向的，一个流只能读或写，而channel打开后即可写也可读
 *  由于channel是双向的，因此它能更好的反映出操作系统的真实情况。
 *
 *  buffer.java
 *  position：元素的个数，永远不会变，不会为负数,可以看做当前最大元素索引的下一个位置
 *  limit：第一个不应该被读写的第一个索引，，不会为负数，不会超过position值
 *  capacity：下一个将要读写的索引，不会为负数，不会超过limit
 *  0 <= m <= p <= l <= c
 *
 *  flip（）改变了position和limit值
 *  public final Buffer flip() {
 *         limit = position;
 *         position = 0;
 *         mark = -1;
 *         return this;
 *     }
 *
 * 通过NIO读取文件涉及到的三个步骤
 * 1。 从fileInputChannel获取到FileChannel对象
 * 2。 创建Buffer
 * 3。 将数据从Channel读取到Buffer中。
 *
 * 绝对方法与相对方法
 * 1。 相对方法：limit与position值会在操作时会被考虑到
 * 2。 绝对方法：忽略到limit、position，是直接操作底层数组
 *
 */
public class NioTest {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);

        for(int i=0;i<intBuffer.capacity();i++){
            int val = new SecureRandom().nextInt(10);
            intBuffer.put(val);
        }

        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }

}
