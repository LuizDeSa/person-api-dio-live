package one.digitalinnovation.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.enums.PhoneType;

import javax.persistence.*;

@Entity
@Data //anotação que agrupa os recursos de @ToString, @EqualsAndHashCode, @Getter / @Setter e @RequiredArgsConstructor juntos:
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // indica que se trata de um enum do tipo string
    @Column(nullable = false)//cria o not null no esquema do banco
    private PhoneType type;

    @Column(nullable = false)
    private String number;
}
