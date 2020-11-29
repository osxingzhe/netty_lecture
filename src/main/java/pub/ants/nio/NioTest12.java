package pub.ants.nio;

import java.nio.channels.Selector;

/**
 * @author magw
 * @version 1.0
 * @date 2020/11/29 下午6:12
 * @description: No Description
 *  服务器端监听5个端口号，用1个线程处理5个客户端连接
 */
public class NioTest12 {

    public static void main(String[] args) throws Exception {
        int[] ports = new int[5];

        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();

    }

}