package pub.ants.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author magw
 * @version 1.0
 * @date 2020/11/29 下午3:19
 * @description: No Description
 *  Scattering 读传递buffer数组，将一个channel读到多个buffer中，按照顺序将前一个读满再读下一个
 *  Gathering 传递buffer数组，写满前一个buffer，再写下一个
 */
public class NioTest11 {

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] byteBuffers = new ByteBuffer[3];

        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int bytesRead = 0;
            while (bytesRead < messageLength){
                long r = socketChannel.read(byteBuffers);
                bytesRead += r;

                System.out.println("bytesRead: "+bytesRead);

                Arrays.asList(byteBuffers).stream().map(buffer->"position:"+buffer.position()+",limit:"+buffer.limit())
                    .forEach(System.out::println);

                System.out.println("byteBuffers[0] position:"+byteBuffers[0]);
            }

            Arrays.asList(byteBuffers).stream().forEach(byteBuffer -> {
                byteBuffer.flip();
            });

            long bytesWrite = 0;
            while (bytesWrite<messageLength){
                long w = socketChannel.write(byteBuffers);
                bytesWrite += w;
            }

            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("bytesRead:"+bytesRead+", bytesWrite:"+bytesWrite+", messageLength:"+messageLength);
        }
    }

}
