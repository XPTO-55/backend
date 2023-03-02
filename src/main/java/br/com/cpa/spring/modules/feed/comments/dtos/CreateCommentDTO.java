package br.com.cpa.spring.modules.feed.comments.dtos;

import lombok.Data;

@Data
public class CreateCommentDTO {
  private String comment;
  private Long patientId;
}
