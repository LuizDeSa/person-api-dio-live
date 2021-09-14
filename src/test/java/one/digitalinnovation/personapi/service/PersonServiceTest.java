package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.request.PhoneDTO;
import one.digitalinnovation.personapi.enums.PhoneType;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class PersonServiceTest {
    @Autowired
    PersonService personService;

    @Test
    void saveTest(){
        List<PhoneDTO> phoneDTOs = new ArrayList<>();

        phoneDTOs.add(new PhoneDTO(null, PhoneType.MOBILE, "(63)999995555"));
        phoneDTOs.add(new PhoneDTO(null, PhoneType.HOME, "(63)888884444"));

        PersonDTO personDTO =  PersonDTO.builder()
                                        .firstName("Luiz")
                                        .lastName("Alberto")
                                        .cpf("216.613.320-72")
                                        .birthDate(LocalDate.now())
                                        .phones(phoneDTOs)
                                        .build();

        PersonDTO personDTOSaved = personService.save(personDTO);

        Assertions.assertEquals(personDTO, personDTOSaved);
    }

    @BeforeAll
    void deleteBefore(){
        List<PersonDTO> personDTOList = personService.findAll();
        personDTOList.stream().forEach(personDTO -> {
            try {
                personService.deleteById(personDTO.getId());
            } catch (PersonNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private MessageResponseDTO builderMessageResponseDTO(String message){
        return MessageResponseDTO.builder().message(message).build();
    }
}
