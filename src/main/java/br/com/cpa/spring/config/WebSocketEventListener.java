package br.com.cpa.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import br.com.cpa.spring.models.Message;
import br.com.cpa.spring.models.Status;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WebSocketEventListener {
  @Autowired
  private SimpMessageSendingOperations messagingTemplate;

  @EventListener
  public void handleWebSocketConnectListener(SessionConnectedEvent event) {
    log.info("Received a new web socket connection");
  }

  @EventListener
  public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

    String username = (String) headerAccessor.getSessionAttributes().get("username");
    if (username != null) {
      Message chatMessage = new Message();
      chatMessage.setStatus(Status.LEAVE);
      chatMessage.setSenderName(username);
      messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
    log.info("Received a new web socket disconnection");
  }

}
