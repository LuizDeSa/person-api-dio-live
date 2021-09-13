package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper mapper;

    @Autowired
    public PersonService(PersonRepository personRepository){ // fazer desta forma tem algumas vantagens
        this.personRepository = personRepository;
    }

    @Transactional
    public MessageResponseDTO save(PersonDTO personDTO){
        Person personToSave = mapper.toEntity(personDTO);

        Person savedPerson =  personRepository.save(personToSave);
        return createMessageResponse(savedPerson, "Created person with ID ");
    }

    @Transactional(readOnly = true)
    public List<PersonDTO> findAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PersonDTO findById(Long id) throws PersonNotFoundException {
        return mapper.toDTO(verifyIfExists(id));
    }

    @Transactional
    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    @Transactional
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {

        this.verifyIfExists(id);

        Person personToUpdate = mapper.toEntity(personDTO);

        Person savedPerson =  personRepository.save(personToUpdate);
        return createMessageResponse(savedPerson, "Updated person with ID ");
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Person savedPerson, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + savedPerson.getId())
                .build();
    }
}
