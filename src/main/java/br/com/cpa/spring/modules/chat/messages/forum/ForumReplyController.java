package br.com.cpa.spring.modules.chat.messages.forum;

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

import br.com.cpa.spring.models.MessageForum;
import br.com.cpa.spring.modules.chat.messages.dto.CreateReplyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/forums/messages")
@Tag(name = "Reply", description = "Reply Chat Message Routes")
@SecurityRequirement(name = "jwtauth")
public class ForumReplyController {
  @Autowired
  ForumMessageRepository repository;

  @Operation(summary = "List expecific reply by id")
  @GetMapping("/{messageId}/reply/{replyId}")
  public MessageForum index(@PathVariable String messageId, @PathVariable String replyId) {
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
  public MessageForum reply(@PathVariable String messageId, @RequestBody CreateReplyDTO replyMessageData) {
    if (!repository.existsById(messageId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
    }
    MessageForum message = repository.findById(messageId).get();
    MessageForum reply = new MessageForum();
    reply.setId(new ObjectId().toString());
    reply.setSenderId(replyMessageData.getSenderId());
    reply.setSenderName(replyMessageData.getSenderName());
    reply.setMessage(replyMessageData.getMessage());
    reply.setForum(null);
    message.addReply(reply);
    return this.repository.save(message);
  }

  @Operation(summary = "Update message by expecific reply by id")
  @PatchMapping("/{messageId}/reply/{replyId}")
  public MessageForum updateReply(@PathVariable String messageId, @PathVariable String replyId,
      @RequestBody CreateReplyDTO replyMessageData) {
    if (!repository.existsById(messageId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
    }
    if (!repository.existsById(replyId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reply not found");
    }
    MessageForum reply = repository.findById(replyId).get();
    reply.setMessage(replyMessageData.getMessage());
    return this.repository.save(reply);
  }
}
