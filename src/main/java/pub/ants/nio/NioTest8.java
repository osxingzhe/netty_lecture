package pub.ants.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author magw
 * @version 1.0
 * @date 2020/11/28 下午10:56
 * @description: No Description
 * .allocate 底层是new对应数据类型的数组
 * allocateDirect 直接缓冲，底层很多sun开头的包
 *   不在java的内存模型中，在堆外内存
 *   在Buffer类中的 long address； 在java JNI 提升效率
 *   DirectByteBuffer 本身是java对象，但是引用数据存储是在堆外，通过address指向堆外数据
 *   少了一次数据拷贝---零拷贝
 *
 *   操作系统会java内存模型外面会开辟一块内存区域，这块内存直接和io设备进行交互
 *     在进行读写时会讲java堆中的数据拷贝到这块内存，再进行io交互
 *   操作系统是可以直接访问java内存模型堆中数据，但是在java内存模型中堆会发生垃圾回收，
 *     一种方法是native在操作时，固定对象内存，不出现移动，虚拟机基于考量不行
 *     一种方法是不让发生垃圾回收，容易出现OOM
 *     另外一种就是将数据拷贝到java堆中找一块内存拷贝，拷贝的时间（很短）不会产生gc，vm会保证拷贝时间不产生gc
 *      当DirectByteBuffer被回收，通过address找到堆外内存，通过JNI回收内存，不会造成内存溢出
 *
 * 如果用了HeapByteBuffer多了一次拷贝，会把java内存中的数据拷贝到操作系统内存，通过外设进行处理
 *
 *   总结：
 *   DirectByteBuffer是存储在java堆中对象，但它持有一个操作系统的内存引用（直接内存模型）；
 *     在直接内存模型之前java堆中数据要和外界交换数据，必须经过native堆，经过一次数据的拷贝，从java堆拷贝到native堆
 *      多了一次内存拷贝
 *    通过直接内存，直接可以和外设交互，实现零拷贝操作
 */
public class NioTest8 {

    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("input2.txt");
        FileOutputStream outputStream = new FileOutputStream("output2.txt");

        FileChannel inputStreamChannel = inputStream.getChannel();
        FileChannel outputStreamChannel = outputStream.getChannel();

        // ByteBuffer buffer = ByteBuffer.allocate(1024);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true){
           // buffer.clear();
            int read = inputStreamChannel.read(buffer);
            System.out.println(read);
            if(read == -1){
                break;
            }

            buffer.flip();
            outputStreamChannel.write(buffer);
        }
        inputStreamChannel.close();
        outputStream.close();
    }

}
