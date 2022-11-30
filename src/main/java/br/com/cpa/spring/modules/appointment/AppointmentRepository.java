package br.com.cpa.spring.modules.appointment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cpa.spring.models.Appointment;
import br.com.cpa.spring.modules.appointment.dtos.ListAppointmentResponseDTO;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
  @Query("SELECT new br.com.cpa.spring.modules.appointment.dtos.ListAppointmentResponseDTO(a.createdAt, a.patient.name, a.professional.name, a.professional.especialidade) FROM Appointment a WHERE a.patient.id =:userId")
  List<ListAppointmentResponseDTO> findAllByPatientId(@Param("userId") Long userId);

  @Query("SELECT new br.com.cpa.spring.modules.appointment.dtos.ListAppointmentResponseDTO(a.createdAt, a.patient.name, a.professional.name, a.professional.especialidade) FROM Appointment a WHERE a.professional.id =:userId")
  List<ListAppointmentResponseDTO> findAllByProfessionalId(@Param("userId") Long userId);

  @Modifying
  @Query(value = "UPDATE Appointment a SET deleted_at=now() WHERE a.id=:id")
  void deleteById(@Param("id") Long id);
}
