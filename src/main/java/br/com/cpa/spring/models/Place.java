package br.com.cpa.spring.models;

import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity(name = "places")
@Table(name = "places")
@Where(clause = "deleted_at NOT IS NULL")
@SQLDelete(sql = "UPDATE places SET deleted_at=now() WHERE place_id=?")
public class Place extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "place_id")
    private Long idLugar;

    private String nomeLugar;

    private String observacoes;

    @OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH })
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;
}
