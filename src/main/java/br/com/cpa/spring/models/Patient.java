package br.com.cpa.spring.models;

import javax.persistence.*;

import org.hibernate.annotations.Where;

import lombok.*;

import java.time.LocalDate;

// Esse @Data já cria os getters, setters, contrutor e o toString() da classe
@Data
// Esse @NoArgsConstructor cria um construtor vazio
@NoArgsConstructor
@Entity(name = "patients")
@Table(name = "users")
@Where(clause = "deleted_at IS NULL")
@ToString
public class Patient extends User {
    public Patient(String nome, String email, String cpf, LocalDate dataDeNascimento, String telefoneFixo, String telefoneCelular) {
    }

    public Patient(String nome, String email, String cpf, LocalDate dataDeNascimento, String telefoneFixo, String telefoneCelular, Address endereco) {
    this.name = nome;
    this.email = email;
    this.cpf = cpf;
    this.getDataDeNascimento =
    }
}
