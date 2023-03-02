package br.com.cpa.spring.modules.chat.forums;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cpa.spring.models.Forum;

public interface ForumRepository extends MongoRepository<Forum, String> {
  @Query("{$or: [{ 'deleted_at': { $exists: false } }, {'deleted_at': null}]}")
  List<Forum> findAll();
}
