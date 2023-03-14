package br.com.cpa.spring.modules.chat.messages.users;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.cpa.spring.models.MessageUser;
import br.com.cpa.spring.models.UserMongo;
import br.com.cpa.spring.modules.chat.messages.dto.CreateMessageDTO;
import br.com.cpa.spring.modules.chat.messages.dto.UpdateMessageDTO;
import br.com.cpa.spring.providers.WebSocketProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/messages/users")
@Tag(name = "User Messages", description = "Chat Message Users Routes")
@SecurityRequirement(name = "jwtauth")
public class UserMessageController {
  @Autowired
  UserMessageRepository userMessageRepository;

  @Autowired
  UserMongoRepository userRepository;

  @Autowired
  private WebSocketProvider webSocketProvider;

  @Operation(summary = "Get all messages from senderId and recipientId")
  @GetMapping("/{senderId}/recipient/{recipientId}")
  public List<MessageUser> index(
      @PathVariable String userId,
      @PathVariable String recipientId,
      @RequestParam(defaultValue = "0", required = false) int page,
      @RequestParam(defaultValue = "20", required = false) int size,
      @RequestParam(defaultValue = "DESC", required = false) Sort.Direction sort
      ) {
    if (!userRepository.existsById(userId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    if (!userRepository.existsById(recipientId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipient not found");
    }
    Pageable pageable = PageRequest.of(page, size, Sort.by(sort,"created_at"));
    List<MessageUser> messages = this.userMessageRepository.findBySenderIdAndRecipientId(userId, recipientId, pageable);
    Collections.reverse(messages);
    return messages;
  }

  @Operation(summary = "List user message by expecific id")
  @GetMapping("/{messageId}")
  public MessageUser show(
      @PathVariable String forumId,
      @PathVariable String messageId) {
    if (!userRepository.existsById(forumId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    if (!userMessageRepository.existsById(messageId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
    }
    return this.userMessageRepository.findById(messageId).get();
  }

  @Operation(summary = "create user message")
  @PostMapping("/{senderId}/recipient/{recipientId}")
  public void store(
      @PathVariable String recipientId,
      @RequestBody @Valid CreateMessageDTO createMessageData) {
    if (!userRepository.existsById(recipientId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    UserMongo recipient = new UserMongo();
    recipient.setId(recipientId);
    MessageUser message = new MessageUser();
    message.setSenderId(createMessageData.getSenderId());
    message.setSenderName(createMessageData.getSenderName());
    message.setMessage(createMessageData.getMessage());
    message.setRecipient(recipient);
    MessageUser messageResponse = this.userMessageRepository.save(message);
    webSocketProvider.notifyUser(messageResponse);
  }

  @Operation(summary = "update user message by expecific id")
  @PatchMapping("/{senderId}/recipient/{recipientId}/messages/{messageId}")
  public MessageUser update(
      @PathVariable String senderId,
      @PathVariable String recipientId,
      @PathVariable String messageId,
      @RequestBody UpdateMessageDTO updateMessageData) {
    if (!userRepository.existsById(senderId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender not found");
    }
    if (!userRepository.existsById(recipientId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipient not found");
    }
    if (!userMessageRepository.existsById(messageId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
    }
    MessageUser message = userMessageRepository.findById(messageId).get();
    message.setMessage(updateMessageData.getMessage());
    return this.userMessageRepository.save(message);
  }

}
