package br.com.cpa.spring.modules.user.rating.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class CreateRatingDTO {
  @Min(0)
  @Max(5)
  private Double rating;

  private String comment;
}
