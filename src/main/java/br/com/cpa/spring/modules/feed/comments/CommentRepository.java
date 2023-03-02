package br.com.cpa.spring.modules.feed.comments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cpa.spring.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByPostId(String postId);
}
