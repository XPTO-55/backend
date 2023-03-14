package br.com.cpa.spring.modules.chat.messages.forum;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.cpa.spring.models.Forum;
import br.com.cpa.spring.models.MessageForum;
import br.com.cpa.spring.modules.chat.forums.ForumRepository;
import br.com.cpa.spring.modules.chat.messages.dto.CreateMessageDTO;
import br.com.cpa.spring.modules.chat.messages.dto.UpdateMessageDTO;
import br.com.cpa.spring.providers.WebSocketProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController("/messages")
@Tag(name = "Forum Message", description = "Chat Forum Messages Routes")
@SecurityRequirement(name = "jwtauth")
public class ForumMessageController {
  @Autowired
  ForumMessageRepository forumMessagerepository;

  @Autowired
  ForumRepository forumRepository;

  @Autowired
  private WebSocketProvider webSocketProvider;

  @Operation(summary = "Get all messages from forumId")
  @GetMapping("/forums/{forumId}/messages")
  public List<MessageForum> index(
      @PathVariable String forumId,
      @RequestParam(defaultValue = "0", required = false) int page,
      @RequestParam(defaultValue = "20", required = false) int size,
      @RequestParam(defaultValue = "DESC", required = false) Sort.Direction sort
      ) {
    if (!forumRepository.existsById(forumId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found");
    }
    Pageable pageable = PageRequest.of(page, size, Sort.by(sort,"created_at"));
    List<MessageForum> messages = this.forumMessagerepository.findByForumId(forumId, pageable);
    Collections.reverse(messages);
    return messages;
  }

  @Operation(summary = "List forum message by expecific id")
  @GetMapping("/forums/{forumId}/messages/{messageId}")
  public MessageForum show(
      @PathVariable String forumId,
      @PathVariable String messageId) {
    if (!forumRepository.existsById(forumId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found");
    }
    if (!forumMessagerepository.existsById(messageId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
    }
    return this.forumMessagerepository.findById(messageId).get();
  }

  @Operation(summary = "create forum message")
  @PostMapping("/forums/{forumId}/messages")
  public void store(
      @PathVariable String forumId,
      @RequestBody @Valid CreateMessageDTO createMessageData) {
    if (!forumRepository.existsById(forumId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found");
    }
    Forum forum = new Forum();
    forum.setId(forumId);
    MessageForum message = new MessageForum();
    message.setSenderId(createMessageData.getSenderId());
    message.setSenderName(createMessageData.getSenderName());
    message.setMessage(createMessageData.getMessage());
    message.setForum(forum);
    MessageForum messageResponse = this.forumMessagerepository.save(message);
    webSocketProvider.notifyForum(messageResponse);
  }

  @Operation(summary = "update forum message by expecific id")
  @PatchMapping("/forums/{forumId}/messages/{id}")
  public MessageForum update(
      @PathVariable String forumId,
      @PathVariable String id,
      @RequestBody UpdateMessageDTO updateMessageData) {
    if (!forumRepository.existsById(forumId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found");
    }
    if (!forumMessagerepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
    }
    MessageForum message = forumMessagerepository.findById(id).get();
    message.setMessage(updateMessageData.getMessage());
    return this.forumMessagerepository.save(message);
  }

}
