package br.com.cpa.spring.modules.chat.messages.dto;

import br.com.cpa.spring.models.Forum;
import lombok.Data;

@Data
public class CreateMessageDTO {
  private String message;

  private String senderName;

  private Long userId;
}
