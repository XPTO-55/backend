package br.com.cpa.spring.modules.user.professional;

import br.com.cpa.spring.models.Profissional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Notação que o ORM utiliza para definir que essa classe é um repository
@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    Profissional findByEmailAndPassword(String email, String password);

    Profissional findByEmail(String email);

    @Query(value = "SELECT p FROM professionals p JOIN FETCH p.roles WHERE p.email =:email")
    Profissional findByEmailFetchRoles(@Param("email") String email);

    // JOIN FETCH
    @Query(value = "SELECT p FROM professionals p JOIN FETCH p.roles")
    List<Profissional> findAllAndRoles();

    @Query(value = "SELECT p FROM professionals p LEFT JOIN p.ratings")
    List<Profissional> findAllAndRatings();

    @Modifying
    @Query(value = "UPDATE professionals p SET deleted_at=now() WHERE p.id=:id")
    void deleteById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE professionals p set p.password =:password where p.id =:id")
    void updatePassword(@Param("password") String password, @Param("id") Long id);
}
