package br.com.cpa.spring.modules.chat.messages;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import br.com.cpa.spring.models.Forum;
import br.com.cpa.spring.models.Message;
import br.com.cpa.spring.modules.chat.forums.ForumRepository;
import br.com.cpa.spring.modules.chat.messages.dto.CreateMessageDTO;
import br.com.cpa.spring.modules.chat.messages.dto.UpdateMessageDTO;
import br.com.cpa.spring.providers.WebSocketProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/forums/{forumId}/messages")
@Tag(name = "Message", description = "Chat Message Routes")
@SecurityRequirement(name = "jwtauth")
public class MessageController {
  @Autowired
  MessageRepository repository;

  @Autowired
  ForumRepository forumRepository;

  @Autowired
  private WebSocketProvider webSocketProvider;

  @Operation(summary = "Get all messages from forumId")
  @GetMapping
  @ResponseBody
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
  @ResponseBody
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
  @ResponseBody
  public void store(
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
    Message messageResponse = this.repository.save(message);
    webSocketProvider.notifyForum(messageResponse, forumId);
    webSocketProvider.notifyUser(messageResponse, 3L);
  }

  @Operation(summary = "update message by expecific id")
  @PatchMapping("/{id}")
  @ResponseBody
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

  @MessageMapping("/message/{forumId}")
  @SendTo("/chatroom/public")
  public Message sendMessage(@DestinationVariable("forumId") String forumId, @Payload CreateMessageDTO messageData) {
    if (!forumRepository.existsById(forumId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found");
    }
    Forum forum = new Forum();
    forum.setId(forumId);
    Message message = new Message();
    message.setUserId(messageData.getUserId());
    message.setSenderName(messageData.getSenderName());
    message.setMessage(messageData.getMessage());
    message.setForum(forum);
    System.out.println("message: " + message.getMessage());
    return this.repository.save(message);
  }
}
