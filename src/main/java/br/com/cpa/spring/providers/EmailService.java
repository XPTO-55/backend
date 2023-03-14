package br.com.cpa.spring.providers;

import br.com.cpa.spring.resources.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
 
    String sendMailWithAttachment(EmailDetails details);
}
