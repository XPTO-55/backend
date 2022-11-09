package br.com.cpa.spring.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

// Esse @Data já cria os getters, setters, contrutor e o toString() da classe
@Data
@AllArgsConstructor
// Esse @NoArgsConstructor cria um construtor vazio
@NoArgsConstructor
// Esse MappedSuperclass diz que é uma super classe
@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "birthday")
    private LocalDate dataDeNascimento;

    @Column(name = "landline")
    private String telefoneFixo;

    @Column(name = "phone")
    private String telefoneCelular;

    @Column
    @ElementCollection(targetClass = Role.class)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", unique = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", unique = false)
    )
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
