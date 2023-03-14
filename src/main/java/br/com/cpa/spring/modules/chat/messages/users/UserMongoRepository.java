package br.com.cpa.spring.modules.chat.messages.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.cpa.spring.models.UserMongo;

public interface UserMongoRepository extends MongoRepository<UserMongo, String> {}
