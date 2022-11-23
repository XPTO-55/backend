package br.com.cpa.spring.models;

import lombok.Data;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    @Column
    private String street; // Rua

    @Column
    private String district; // Bairro

    @Column
    private String number; // Número

    @Column
    private String complement; // Complemento

    @Column
    private String zipcode; // CEP

    @Column
    private String city; // Cidade

    @Column
    private String uf; // Estado

    @Column(columnDefinition = "POINT")
    private Point coordenates; // Coordenadas
}
