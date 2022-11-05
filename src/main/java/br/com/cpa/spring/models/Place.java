package br.com.cpa.spring.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue
    @Column(name = "place_id")
    private Long idLugar;

    private String nomeLugar;

    private String observacoes;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;
}
