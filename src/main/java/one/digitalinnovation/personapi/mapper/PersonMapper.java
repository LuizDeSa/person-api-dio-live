package one.digitalinnovation.personapi.mapper;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person toEntity(PersonDTO dto){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, Person.class);
    }

    public PersonDTO toDTO(Person person){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(person, PersonDTO.class);
    }
}
