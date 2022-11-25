package br.com.cpa.spring.models;

import org.springframework.data.mongodb.core.mapping.Document;
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
@Document(collection = "forums")
public class Forum {
  @Id
  private String id;

  private String name;
}
