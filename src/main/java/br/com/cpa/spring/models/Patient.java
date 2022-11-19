package br.com.cpa.spring.models;

import javax.persistence.*;

import lombok.*;

// Esse @Data já cria os getters, setters, contrutor e o toString() da classe
@Data
// Esse @NoArgsConstructor cria um construtor vazio
@NoArgsConstructor
@Entity(name = "patients")
@Table(name = "users")
@ToString
public class Patient extends User {
}
