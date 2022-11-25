package br.com.cpa.spring.models;

import javax.persistence.*;

import org.hibernate.annotations.Where;

import lombok.*;

// Esse @Data já cria os getters, setters, contrutor e o toString() da classe
@Data
@Table(name = "users")
@Where(clause = "identification IS NOT NULL and deleted_at IS NULL")
// Esse @NoArgsConstructor cria um construtor vazio
@NoArgsConstructor
@Entity(name = "professionals")
public class Profissional extends User {
    @Transient
    private String userType = "professionals";

    @Column(name = "identification")
    private String identificacao = "none";

    @Column(name = "verified")
    private Boolean verificado = false;

    @Column(name = "especiality")
    private String especialidade;

    @Column(name = "graduation")
    private String graduacao;
}
