package br.com.cpa.spring.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@Configuration
public class RabbitMQConfig {

  @Value("${broker.host}")
  private String brokerHost;

  @Value("${broker.port}")
  private int brokerPort;

  @Value("${broker.username}")
  private String brokerUser;

  @Value("${broker.password}")
  private String brokerPass;

  @Bean
  public Queue forum_messages(){
    return new Queue("chat.v1.messages.forum_messages");
  }

  @Bean
  public Queue user_messages(){
    return new Queue("chat.v1.messages.user_messages");
  }

  @Bean
  public TopicExchange exchange(){
    return new TopicExchange("cpa_chat_exchange");
  }

  @Bean
  public Binding bindingForum(){
    return BindingBuilder
      .bind(forum_messages())
      .to(exchange())
      .with("forums.*");
  }

  @Bean
  public Binding bindingUser(){
    return BindingBuilder
      .bind(user_messages())
      .to(exchange())
      .with("users.*");
  }

  @Bean
  ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    // connectionFactory.setAddresses(address);
    connectionFactory.setUsername(brokerUser);
    connectionFactory.setPassword(brokerPass);
    return connectionFactory;
}

  @Bean
  public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
    return new RabbitAdmin(connectionFactory);
  }

  @Bean
  public ApplicationListener<ApplicationReadyEvent> ApplicationListenerApplicationReadyEvent(
    RabbitAdmin rabbitAdmin) {
    return event -> rabbitAdmin.initialize();
  }
}
