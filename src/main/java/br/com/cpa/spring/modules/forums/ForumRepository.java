package br.com.cpa.spring.modules.forums;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cpa.spring.models.Forum;

public interface ForumRepository extends MongoRepository<Forum, String> {

}
