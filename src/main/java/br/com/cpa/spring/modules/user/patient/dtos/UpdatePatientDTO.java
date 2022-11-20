package br.com.cpa.spring.modules.user.patient.dtos;

import br.com.cpa.spring.models.Address;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

// Esse @Data já cria os getters, setters, contrutor e o toString() da classe
@Data
public class UpdatePatientDTO {

    @NotBlank
    private String name;

    private String rg;

    @CPF
    private String cpf;

    private String about;

    @PastOrPresent
    private LocalDate birthday;

    private String addressline;

    private String phone;

    private Address address;

    @Email()
    @NotBlank()
    private String email;

    @Size(min = 8, max = 20)
    private String password;

}
