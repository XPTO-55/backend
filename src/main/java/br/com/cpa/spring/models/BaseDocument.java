package br.com.cpa.spring.models;

import java.time.Instant;

import javax.persistence.Column;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class BaseDocument {
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @CreatedDate
  @Field(name = "created_at")
  private Instant createdAt = Instant.now();

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @LastModifiedDate
  @Field(name = "updated_at")
  private Instant updatedAt = Instant.now();
}
