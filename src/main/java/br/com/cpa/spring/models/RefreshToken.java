package br.com.cpa.spring.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "refreshtoken")
@Getter
@Setter
@ToString
public class RefreshToken {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "user_id")
    private Patient user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(unique = false)
    private Date expirationTime;

    public RefreshToken() {
    }
}
