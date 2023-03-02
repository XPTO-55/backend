package br.com.cpa.spring.modules.chat.messages.dto;

import groovy.transform.ToString;
import lombok.Data;

@Data
@ToString
public class CreateMessageDTO {
  private String message;

  private String senderName;

  private Long userId;
}
