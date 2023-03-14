package br.com.cpa.spring.modules.chat.messages.forum;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cpa.spring.models.MessageForum;

public interface ForumMessageRepository extends MongoRepository<MessageForum, String> {
  public List<MessageForum> findByForumId(String id);
  public List<MessageForum> findByForumId(String id, Pageable pageable);
}
