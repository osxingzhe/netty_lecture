package pub.ants.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @author magw
 * @version 1.0
 * @date 2020/11/22 上午9:15
 * @description: No Description
 */
public class ThriftClient {

    public static void main(String[] args) {
        TTransport tTransport = new TFramedTransport(new TSocket("localhost",8899),600);
        TCompactProtocol protocol = new TCompactProtocol(tTransport);

        PersonService.Client client = new PersonService.Client(protocol);

        try{
            tTransport.open();
            Person person = client.getPersonByUsername("zhangsan");
            System.out.println(person);

            System.out.println("------------");
            Person savePerson = new Person();
            savePerson.setUsername("thrift");
            savePerson.setAge(1);
            savePerson.setMarried(false);
            client.savePerson(savePerson);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }finally {
            tTransport.close();
        }
    }
}
