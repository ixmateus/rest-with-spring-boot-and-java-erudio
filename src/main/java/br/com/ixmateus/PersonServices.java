package br.com.ixmateus;

import br.com.ixmateus.model.Person;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());
    public List <Person> findAll () {
        logger.info("Finding all Person!");
        var persons = new ArrayList<Person>();
        for (int i = 0; i < 8; i++) {
            persons.add(mockPerson(i));
        }
        return persons;
    }
    public Person findById(String id) {
        logger.info("Finding one Person!");

        var person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Mateus");
        person.setLastName("Monteiro");
        person.setAddress("Brazil");

        return person;
    }

    public Person create (Person person) {
        logger.info("Creating one Person");
        return person;
    }
    public Person update(Person person) {
        logger.info("Updating one Person");
        return person; // você pode simular uma atualização aqui
    }

    public void delete(String id) {
        logger.info("Deleting person with ID " + id);
    }


    private Person mockPerson(int i) {
        var person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("FirstName " + i);
        person.setLastName("LastName " + i);
        person.setAddress("Address " + i);
        person.setGender(i % 2 == 0 ? "Male" : "Female");
        return person;
    }


}
