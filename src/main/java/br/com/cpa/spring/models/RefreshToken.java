package br.com.cpa.spring.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "refreshtoken")
@Getter
@Setter
@ToString
public class RefreshToken {
    @Id
    @GeneratedValue
    @Column(name = "refresh_id")
    private Long id;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH }, orphanRemoval = true, targetEntity = Patient.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Patient user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(unique = false)
    private Date expirationTime;

    public RefreshToken() {
    }
}
