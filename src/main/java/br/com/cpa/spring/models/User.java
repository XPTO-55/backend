package br.com.cpa.spring.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.*;

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
@SQLDelete(sql = "UPDATE users SET deleted_at=now() WHERE user_id=?")
@Where(clause = "deleted_at NOT IS NULL")
public abstract class User extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "profile_url")
    private String profileUrl;

    @NotBlank
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "about")
    private String about;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "landline")
    private String landline;

    @Column(name = "phone")
    private String phone;

    @JoinTable(
            name = "users_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id", unique = false)
            }, inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "role_id", unique = false)
            }
    )
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH }, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "profissional")
    @JsonManagedReference
    private Set<Rating> ratings = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH })
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    // @OneToOne(mappedBy = "refreshToken")
    // private RefreshToken refreshToken;

    public void addRole(Role role) {
        this.roles.add(role);
    }

    // @PreRemove
    // public void delete() {
    // this.setDeletedAt(new Date());
    // }

    public abstract String getUserType();

    public User(String name, String email, String cpf, LocalDate birthday, String landline, String phone,
                    Address address) {
            this.id = null;
            this.name = name;
            this.profileUrl = "";
            this.email = email;
            this.password = "";
            this.cpf = cpf;
            this.about = "";
            this.birthday = birthday;
            this.landline = landline;
            this.phone = phone;
            this.roles = new HashSet<>();
            this.ratings = new HashSet<>();
            this.address = address;
    }

    public User(String name, String email, String cpf, LocalDate birthday, String landline, String phone) {
            this.id = null;
            this.name = name;
            this.profileUrl = "";
            this.email = email;
            this.password = "";
            this.cpf = cpf;
            this.about = "";
            this.birthday = birthday;
            this.landline = landline;
            this.phone = phone;
            this.roles = new HashSet<>();
            this.ratings = new HashSet<>();
            this.address = null;
    }

}
