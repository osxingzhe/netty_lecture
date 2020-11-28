package pub.ants.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author magw
 * @version 1.0
 * @date 2020/11/23 下午11:09
 * @description: No Description
 */
public class NioTest3 {

    public static void main(String[] args) throws Exception{
        FileOutputStream fos = new FileOutputStream("NioTest3.txt");
        FileChannel fileChannel = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] message = "hello nio".getBytes();

        for(int i=0;i<message.length;i++){
            byteBuffer.put(message[i]);
        }

        byteBuffer.flip();

        fileChannel.write(byteBuffer);
        fileChannel.close();
    }

}
