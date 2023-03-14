package br.com.cpa.spring.modules.chat.messages.users;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.cpa.spring.models.Message;
import br.com.cpa.spring.models.MessageUser;
import br.com.cpa.spring.modules.chat.messages.dto.CreateReplyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users/messages")
@Tag(name = "Reply", description = "Reply Chat User Message Routes")
@SecurityRequirement(name = "jwtauth")
public class UserReplyController {
  @Autowired
  UserMessageRepository repository;

  @Operation(summary = "List expecific reply by id")
  @GetMapping("/{messageId}/reply/{replyId}")
  public MessageUser index(@PathVariable String messageId, @PathVariable String replyId) {
    if (!repository.existsById(messageId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
    }
    if (!repository.existsById(replyId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reply not found");
    }
    return this.repository.findById(messageId).get();
  }

  @Operation(summary = "Create reply")
  @PostMapping("/{messageId}/reply")
  public Message reply(@PathVariable String messageId, @RequestBody CreateReplyDTO replyMessageData) {
    if (!repository.existsById(messageId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
    }
    MessageUser message = repository.findById(messageId).get();
    MessageUser reply = new MessageUser();
    reply.setId(new ObjectId().toString());
    reply.setSenderId(replyMessageData.getSenderId());
    reply.setSenderName(replyMessageData.getSenderName());
    reply.setMessage(replyMessageData.getMessage());
    reply.setRecipient(null);
    message.addReply(reply);
    return this.repository.save(message);
  }

  @Operation(summary = "Update message by expecific reply by id")
  @PatchMapping("/{messageId}/reply/{replyId}")
  public MessageUser updateReply(@PathVariable String messageId, @PathVariable String replyId,
      @RequestBody CreateReplyDTO replyMessageData) {
    if (!repository.existsById(messageId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
    }
    if (!repository.existsById(replyId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reply not found");
    }
    MessageUser reply = repository.findById(replyId).get();
    reply.setMessage(replyMessageData.getMessage());
    return this.repository.save(reply);
  }
}
