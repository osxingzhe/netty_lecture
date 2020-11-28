package pub.ants.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author magw
 * @version 1.0
 * @date 2020/11/23 下午11:09
 * @description: No Description
 */
public class NioTest2 {

    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream("NioTest2.txt");
        FileChannel fileChannel = fis.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);

        byteBuffer.flip();

        while (byteBuffer.remaining()>0){
            byte b = byteBuffer.get();
            System.out.println("Character:"+(char)b);
        }

        fileChannel.close();
    }

}
