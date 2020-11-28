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
 */
public class NooTest4 {

    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");

        FileChannel inputStreamChannel = inputStream.getChannel();
        FileChannel outputStreamChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

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
