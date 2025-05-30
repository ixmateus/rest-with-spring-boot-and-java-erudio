package br.com.ixmateus.services;

import br.com.ixmateus.data.dto.v1.PersonDTO;
import br.com.ixmateus.data.dto.v2.PersonDTOV2;
import br.com.ixmateus.exception.ResourceNotFoundException;
import static br.com.ixmateus.mapper.ObjectMapper.parseListObjects;
import static br.com.ixmateus.mapper.ObjectMapper.parseObject;

import br.com.ixmateus.mapper.custom.PersonMapper;
import br.com.ixmateus.model.Person;
import br.com.ixmateus.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    public List <PersonDTO> findAll () {

        logger.info("Finding all Person!");

        return parseListObjects(repository.findAll(), PersonDTO.class);
    }
    public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");

        var entity =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create (PersonDTO person) {

        logger.info("Creating one Person");
        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    // Atualização para V2!
    public PersonDTOV2 createV2 (PersonDTOV2 person) {

        logger.info("Creating one Person V2!");
        var entity = converter.convertDTOToEntity(person);

        return converter.convertEntityToDTO(repository.save(entity));
    }
    public PersonDTO update(PersonDTO person) {
        logger.info("Updating one Person");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting person with ID " + id);

         Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.deleteById(id);
    }

}
