package br.com.cpa.spring.modules.user.professional.dtos;

import br.com.cpa.spring.models.Address;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

// Esse @Data já cria os getters, setters, contrutor e o toString() da classe
@Data
public class UpdateProfissionalDTO {

    @NotBlank
    private String name;

    private String rg;

    // @CPF
    private String cpf;

    private String identificacao;

    private String especialidade;

    private String graduacao;

    private String about;

    @PastOrPresent
    private LocalDate birthday;

    private String landline;

    private String phone;

    private Address address;

    @Email()
    @NotBlank()
    private String email;
}
