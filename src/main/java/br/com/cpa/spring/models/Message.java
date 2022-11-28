package br.com.cpa.spring.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Document(collection = "forum_messages")
public class Message extends BaseDocument {
  @Id
  private String id;

  private String message;

  @Field(name = "sender_name")
  private String senderName;

  @Field(name = "user_id")
  private Long userId;

  private Forum forum;

  private List<Message> replyes = new ArrayList<>();

  public void addReply(Message reply) {
    this.replyes.add(reply);
  }
}
