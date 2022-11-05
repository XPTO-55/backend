package br.com.cpa.spring.models;

import javax.persistence.*;

import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

// Esse @Data já cria os getters, setters, contrutor e o toString() da classe
@Data
@Table(name = "users")
// Esse @NoArgsConstructor cria um construtor vazio
@NoArgsConstructor
@Entity(name = "professionals")
public class Profissional extends User {
    @Column(name = "identification")
    private String identificacao;
}
