package pub.ants.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @author magw
 * @version 1.0
 * @date 2020/11/29 下午3:13
 * @description: No Description
 *  文件锁 共享锁 拍他锁
 */
public class NioTest10 {

    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("nioTest10.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();

        FileLock fileLock = channel.lock(1, 5, true);

        System.out.println("valid:"+fileLock.isValid());
        System.out.println("share:"+fileLock.isShared());

        fileLock.release();

        randomAccessFile.close();
    }

}
