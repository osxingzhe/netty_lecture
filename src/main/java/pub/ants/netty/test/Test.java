package pub.ants.netty.test;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @author: magaowei
 * @version: 1.0
 * @date: 2020/12/5 10:14 下午
 * @description:
 *  NioEventLoopGroup:任务提交和任务运行解耦
 *      ThreadPerTaskExecutor：命令模式、代理模式
 */
public class Test {

    public static void main(String[] args) {
        // 超线程设置，将本机的线程数(4核心)*2
        int i = Math.max(1, SystemPropertyUtil.getInt(
            "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(i);

    }
}
