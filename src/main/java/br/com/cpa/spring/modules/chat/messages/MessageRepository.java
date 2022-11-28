package br.com.cpa.spring.modules.chat.messages;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cpa.spring.models.Message;

public interface MessageRepository extends MongoRepository<Message, String> {
  public List<Message> findByForumId(String id);

  public List<Message> findByForumId(String id, Pageable pageable);
}
