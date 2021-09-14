package one.digitalinnovation.personapi.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")//utilizado no test
public class PersonDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100)
    @Pattern(regexp = "^[[a-z][A-Z]]+$")
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 100)
    @Pattern(regexp = "^[[a-z][A-Z]]+$")
    private String lastName;

    @NotEmpty
    @CPF
    private String cpf;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR")
    private LocalDate birthDate;

    @Valid
    @NotEmpty
    private List<PhoneDTO> phones;

}
