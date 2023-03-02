package br.com.cpa.spring.modules.feed.comments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cpa.spring.models.Comment;
import br.com.cpa.spring.modules.feed.comments.CommentRepository;

@Service
public class FindCommentsService {
  @Autowired
  CommentRepository repository;

  public List<Comment> execute(String postId) {
    return repository.findByPostId(postId);
  }
}