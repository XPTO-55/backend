package br.com.cpa.spring.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import br.com.cpa.spring.models.Message;

@Service
public class WebSocketProvider {
  private final SimpMessagingTemplate messagingTemplate;

  @Autowired
  public WebSocketProvider(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  public void notifyUser(final Message message) {
    messagingTemplate.convertAndSend( "/queue/private", message);
  }

  public void notifyUser(final Message message, final Long userId) {
    messagingTemplate.convertAndSend("/queue/private-"+userId.toString(), message);
  }

  public void notifyForum(final Message message, final String forumId) {
    messagingTemplate.convertAndSendToUser(forumId, "/queue/chat.v1.messages.forum_messages"+forumId, message);
  }

  public void notifyForum(final Message message) {
    messagingTemplate.convertAndSend("/queue/chat.v1.messages.forum_messages", message);
  }

  public void notifyAll(final Message message) {
    messagingTemplate.convertAndSend("/topic/public", message);
  }
}
