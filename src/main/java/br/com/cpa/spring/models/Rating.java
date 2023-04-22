package br.com.cpa.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity(name = "ratings")
@Table(name = "ratings")
public class Rating {
  @Id
  @GeneratedValue
  @Column(name = "rating_id")
  private Long id;

  @Min(0)
  @Max(5)
  @Column(name = "rating")
  private Double rating;

  @Column(name = "comment")
  private String comment;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private Profissional profissional;
}
