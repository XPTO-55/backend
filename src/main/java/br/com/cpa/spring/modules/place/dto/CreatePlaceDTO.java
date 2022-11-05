package br.com.cpa.spring.modules.place.dto;

import br.com.cpa.spring.models.Address;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreatePlaceDTO {

    @NotBlank
    private String nomeLugar;

    private String observacoes;

    private Address address;

}
