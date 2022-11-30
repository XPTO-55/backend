package br.com.cpa.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
@SQLDelete(sql = "UPDATE appointments SET deleted_at=now() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class Appointment extends BaseEntity {
  @Id
  @GeneratedValue
  @Column(name = "appointment_id")
  private Long id;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "patient_id", referencedColumnName = "user_id")
  Patient patient;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "professional_id", referencedColumnName = "user_id")
  Profissional professional;
}
