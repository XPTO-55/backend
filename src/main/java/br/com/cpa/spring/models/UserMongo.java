package br.com.cpa.spring.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserMongo extends BaseDocument {
  @Id
  private String id;

  private String name;

  @Field(name = "image_url")
  private String imageUrl;

  private List<UserMongo> contacts = new ArrayList<UserMongo>();

  public void addReply(UserMongo contact) {
    this.contacts.add(contact);
  }
}
