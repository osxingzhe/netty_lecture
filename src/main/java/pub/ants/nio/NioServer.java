package pub.ants.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author: magaowei
 * @version: 1.0
 * @date: 2020/11/29 11:00 下午
 * @description: 聊天程序服务端
 *  消息量不大，但是连接多
 */
public class NioServer {

    /**
     * 保存客户端信息
     */
    private static Map<String,SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // 创建ServerSocketChannel对象
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 配置成非阻塞的
        serverSocketChannel.configureBlocking(false);
        // 获取服务器端Socket对象
        ServerSocket serverSocket = serverSocketChannel.socket();
        // 绑定端口
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        // 服务器端关注链接事件
        // 将serverSocketChannel注册到selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);



        // 进行相应的事件处理
        while (true) {
            try {
                // 返回关注事件的数量
                selector.select();
                // 获得关注的时间的集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;
                    try {
                        if (selectionKey.isAcceptable()) {
                            // 一开始仅将serverSocketChannel注册到selector上
                            ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
                            // 返回socketChannel对象,与客户端交互，server完成建立链接的任务
                            client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector,SelectionKey.OP_READ);

                            String key = "【"+ UUID.randomUUID().toString() +"]";
                            // 记录客户端信息
                            clientMap.put(key,client);
                        } else if (selectionKey.isReadable()) {
                            client = (SocketChannel)selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            int count = client.read(byteBuffer);
                            if(count>0){
                                byteBuffer.flip();
                                Charset charset = Charset.forName("utf-8");
                                String receivedMessage = String.valueOf(charset.decode(byteBuffer).array());
                                System.out.println("receivedMessage: "+receivedMessage);

                                String sendKey = null;
                                for(Map.Entry<String,SocketChannel> entry:clientMap.entrySet()){
                                    if(client == entry.getValue()){
                                        sendKey = entry.getKey();
                                    }
                                }
                                for(Map.Entry<String,SocketChannel> entry:clientMap.entrySet()){
                                    SocketChannel socketChannel = entry.getValue();
                                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                    writeBuffer.put((sendKey+":"+receivedMessage).getBytes());
                                    writeBuffer.flip();
                                    socketChannel.write(writeBuffer);
                                }
                            }
                        }
                        selectionKeys.clear();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}