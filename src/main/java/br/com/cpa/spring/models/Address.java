package br.com.cpa.spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @Column
    private Double latitute; // Coordenadas

    @Column
    private Double longitude; // Coordenadas

    public Address(Long id, String street, String district, String number, String complement, String zipcode, String city, String uf) {
        this.id = id;
        this.street = street;
        this.district = district;
        this.number = number;
        this.complement = complement;
        this.zipcode = zipcode;
        this.city = city;
        this.uf = uf;
        this.latitute = 0.0;
        this.longitude = 0.0;
    }
}
