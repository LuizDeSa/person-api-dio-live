package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper mapper;

    @Autowired
    public PersonService(PersonRepository personRepository){ // fazer desta forma tem algumas vantagens
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = mapper.toEntity(personDTO);

        Person savedPerson =  personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " +savedPerson.getId() )
                .build();
    }
}
