package br.com.cpa.spring.modules.user.patient.dtos;

import lombok.Data;
import org.springframework.data.geo.Point;

import javax.persistence.Column;

@Data
public class CreateUserAddressDTO {

    private String street; // Rua

    private String district; // Bairro

    private String number; // Número

    private String complement; // Complemento

    private String zipcode; // CEP

    private String city; // Cidade

    private String uf; // Estado

    private Point coordenates; // Coor
}
