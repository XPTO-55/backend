package br.com.cpa.spring.modules.chat.messages.users;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cpa.spring.models.MessageUser;

public interface UserMessageRepository extends MongoRepository<MessageUser, String> {
  public List<MessageUser> findBySenderIdAndRecipientId(String senderId, String recipientId);
  public List<MessageUser> findBySenderIdAndRecipientId(String senderId, String recipientId, Pageable pageable);
}
