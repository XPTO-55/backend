package br.com.cpa.spring.modules.user.professional;

import br.com.cpa.spring.models.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Notação que o ORM utiliza para definir que essa classe é um repository
@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    Profissional findByEmailAndPassword(String email, String password);

    Profissional findByEmail(String email);

    @Query("SELECT p FROM professionals p JOIN FETCH p.roles WHERE p.email =:email")
    Profissional findByEmailFetchRoles(@Param("email") String email);

    // JOIN FETCH
    @Query(value = "SELECT p FROM professionals p JOIN FETCH p.roles")
    List<Profissional> findAllAndRoles();

}
