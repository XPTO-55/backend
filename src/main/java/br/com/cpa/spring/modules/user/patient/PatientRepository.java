package br.com.cpa.spring.modules.user.patient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import br.com.cpa.spring.models.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Notação que o ORM utiliza para definir que essa classe é um repository
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmailAndPassword(String email, String password);

    Patient findByEmail(String email);

    @Query("SELECT p FROM patients p JOIN FETCH p.roles WHERE p.email =:email")
    Patient findByEmailFetchRoles(@Param("email") String email);

    // JOIN FETCH
    @Query(value = "SELECT p FROM patients p JOIN FETCH p.roles")
    List<Patient> findAllAndRoles();

    @Modifying
    @Query(value = "UPDATE patients p SET deleted_at=now() WHERE p.id=:id")
    void deleteById(@Param("id") Long id);

}
