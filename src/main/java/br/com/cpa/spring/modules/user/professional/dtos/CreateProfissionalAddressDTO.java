package br.com.cpa.spring.modules.user.professional.dtos;

import lombok.Data;
import org.springframework.data.geo.Point;

@Data
public class CreateProfissionalAddressDTO {

    private String street; // Rua

    private String district; // Bairro

    private String number; // Número

    private String complement; // Complemento

    private String zipcode; // CEP

    private String city; // Cidade

    private String uf; // Estado

    private Double latitute; // Coordenadas

    private Double longitude; // Coordenadas
}
