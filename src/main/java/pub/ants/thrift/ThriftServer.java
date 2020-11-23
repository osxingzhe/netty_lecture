package pub.ants.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol.Factory;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.THsHaServer.Args;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import thrift.generated.PersonService;
import thrift.generated.PersonService.Processor;

/**
 * @author magw
 * @version 1.0
 * @date 2020/11/22 上午9:15
 * @description: No Description
 * 安装thrift：brew install thrift
 * 生成文件：thrift --gen java src/thrift/data.thrift
 */
public class ThriftServer {

    public static void main(String[] args) throws Exception {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        Args arg = new Args(socket);
        arg.minWorkerThreads(2);
        arg.maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor =
            new Processor<>(new PersonServiceImpl());

        arg.protocolFactory(new Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);

        System.out.println("thrift server started");

        server.serve();
    }
}
