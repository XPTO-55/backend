package br.com.cpa.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Entity
@Table(name = "feed_comments")
public class Comment extends BaseEntity {
  @Id
  @GeneratedValue
  @Column(name = "comment_id")
  private Long id;

  @Column(name = "comment")
  private String comment;

  @Column(name = "post_id")
  private String postId;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private Patient patient;
}