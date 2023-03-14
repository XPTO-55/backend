package br.com.cpa.spring.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMQProvider {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProvider.class);

  private RabbitTemplate rabbitTemplate;
  private String exchange = "cpa_chat_exchange";
  private String userRoutingKey = "users";
  private String forumRoutingKey = "forums";

  public RabbitMQProvider(RabbitTemplate rabbitTemplate){
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendUserMessage(String message){
    LOGGER.info(String.format("Message sent -> %s", message));
    rabbitTemplate.convertAndSend(exchange,userRoutingKey, message);
  }

  public void sendForumMessage(String message){
    LOGGER.info(String.format("Message sent -> %s", message));
    rabbitTemplate.convertAndSend(exchange,forumRoutingKey, message);
  }
}
