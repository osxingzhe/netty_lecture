package pub.ants.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * @author magw
 * @version 1.0
 * @date 2020/11/29 下午3:05
 * @description: No Description
 *  用于内存映射文件的内存是堆外内存
 *  内存映射文件允许java直接操作内存，可以修改文件
 */
public class NioTest9 {

    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("nioTest9.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = channel.map(MapMode.READ_WRITE, 0, 2);

        mappedByteBuffer.put(0,(byte)'a');

        randomAccessFile.close();
    }

}
