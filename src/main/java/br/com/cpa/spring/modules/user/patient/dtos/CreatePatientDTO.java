package br.com.cpa.spring.modules.user.patient.dtos;

import br.com.cpa.spring.models.Address;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

// Esse @Data já cria os getters, setters, contrutor e o toString() da classe
@Data
@Getter
@Setter
@ToString
public class CreatePatientDTO {

    @NotBlank
    private String name;

    @CPF
    private String cpf;

    @PastOrPresent
    private LocalDate birthday;

    private String addressline;

    private String phone;

    private CreateUserAddressDTO address;

    @Email()
    @NotBlank()
    private String email;

    @Size(min = 8, max = 20)
    private String password;

}
