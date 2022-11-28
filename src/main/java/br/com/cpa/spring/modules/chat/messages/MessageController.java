package br.com.cpa.spring.modules.chat.messages;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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

import br.com.cpa.spring.models.Forum;
import br.com.cpa.spring.models.Message;
import br.com.cpa.spring.modules.chat.forums.ForumRepository;
import br.com.cpa.spring.modules.chat.messages.dto.CreateMessageDTO;
import br.com.cpa.spring.modules.chat.messages.dto.UpdateMessageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/forums/{forumId}/messages")
@Tag(name = "Message", description = "Chat Message Routes")
@SecurityRequirement(name = "jwtauth")
public class MessageController {
  @Autowired
  MessageRepository repository;

  @Autowired
  ForumRepository forumRepository;

  @Operation(summary = "Get all messages from forumId")
  @GetMapping
  public List<Message> index(
      @PathVariable String forumId,
      @RequestParam(defaultValue = "0", required = false) int page,
      @RequestParam(defaultValue = "20", required = false) int size) {
    if (!forumRepository.existsById(forumId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found");
    }
    Pageable pageable = PageRequest.of(page, size);
    return this.repository.findByForumId(forumId, pageable);
  }

  @Operation(summary = "List message by expecific id")
  @GetMapping("/{messageId}")
  public Message show(
      @PathVariable String forumId,
      @PathVariable String messageId) {
    if (!forumRepository.existsById(forumId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found");
    }
    if (!repository.existsById(messageId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
    }
    return this.repository.findById(messageId).get();
  }

  @Operation(summary = "create message")
  @PostMapping
  public Message store(
      @PathVariable String forumId,
      @RequestBody @Valid CreateMessageDTO createMessageData) {
    if (!forumRepository.existsById(forumId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found");
    }
    Forum forum = new Forum();
    forum.setId(forumId);

    Message message = new Message();
    message.setUserId(createMessageData.getUserId());
    message.setSenderName(createMessageData.getSenderName());
    message.setMessage(createMessageData.getMessage());
    message.setForum(forum);
    return this.repository.save(message);
  }

  @Operation(summary = "update message by expecific id")
  @PatchMapping("/{id}")
  public Message update(
      @PathVariable String forumId,
      @PathVariable String id,
      @RequestBody UpdateMessageDTO updateMessageData) {
    if (!forumRepository.existsById(forumId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found");
    }
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
    }
    Message message = repository.findById(id).get();
    message.setMessage(updateMessageData.getMessage());
    return this.repository.save(message);
  }
}

// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsdWthc2FsdmVzMjcxQGdtYWlsLmNvbSIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sImV4cCI6MTY3MDM4MTgyMCwiaWF0IjoxNjY5NDgxODIwfQ.pQsKx0p3-71WsRHSxeT3pOgZJ1O-cY7qsSQidr5lTiM
