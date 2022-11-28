package br.com.cpa.spring.modules.chat.forums;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cpa.spring.models.Forum;

public interface ForumRepository extends MongoRepository<Forum, String> {

}
