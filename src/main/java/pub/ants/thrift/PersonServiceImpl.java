package pub.ants.thrift;

import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @author magw
 * @version 1.0
 * @date 2020/11/22 上午9:12
 * @description: No Description
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("got client param:" + username);

        Person person = new Person();
        person.setUsername(username);
        person.setAge(10);
        person.setMarried(false);

        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("got client param:" );

        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
