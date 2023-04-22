package br.com.cpa.spring.config.services;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

// @Configuration
public class EmailConfig {
  @Value("${spring.mail.host}")
  private String HOST;

  @Value("${spring.mail.port}")
  private Integer PORT;

  @Value("${spring.mail.username}")
  private String USERNAME;

  @Value("${spring.mail.password}")
  private String PASSWORD;

  @Value("${spring.mail.properties.mail.smtp.auth}")
  private Boolean AUTH;

  @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
  private Boolean START_TLS;

  // @Bean
  // public JavaMailSender getJavaMailSender() {
  // JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
  // mailSender.setHost(HOST);
  // mailSender.setPort(PORT);

  // mailSender.setUsername(USERNAME);
  // mailSender.setPassword(PASSWORD);

  // Properties props = mailSender.getJavaMailProperties();
  // props.put("mail.transport.protocol", "smtp");
  // props.put("mail.smtp.auth", AUTH);
  // props.put("mail.smtp.starttls.enable", START_TLS);
  // props.put("mail.debug", true);

  // return mailSender;
  // }

  @Bean
  public SpringTemplateEngine springTemplateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.addTemplateResolver(htmlTemplateResolver());
    return templateEngine;
  }

  @Bean
  public SpringResourceTemplateResolver htmlTemplateResolver() {
    SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
    emailTemplateResolver.setPrefix("classpath:/templates/email");
    emailTemplateResolver.setSuffix(".html");
    emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
    emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
    return emailTemplateResolver;
  }
}
