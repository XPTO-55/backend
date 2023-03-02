package br.com.cpa.spring.providers;

import org.springframework.beans.factory.annotation.Autowired;
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

  public void notifyUser(final Message message, final Long userId) {
    messagingTemplate.convertAndSendToUser(userId.toString(), "/queue/messages", message);
  }

  public void notifyForum(final Message message, final String forumId) {
    messagingTemplate.convertAndSendToUser(forumId.toString(), "/queue/messages", message);
    messagingTemplate.convertAndSendToUser("3", "/queue/messages", message);
    messagingTemplate.convertAndSend("user" + forumId.toString() + "/queue/messages", message);
    messagingTemplate.convertAndSend("user" + 3 + "/queue/messages", message);
    messagingTemplate.convertAndSend("/topic/public", message);
  }

  public void notifyAll(final Message message, final String forumId) {
    messagingTemplate.convertAndSend("/topic/public", message);
  }
}
