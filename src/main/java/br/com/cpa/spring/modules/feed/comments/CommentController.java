package br.com.cpa.spring.modules.feed.comments;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cpa.spring.models.Comment;
import br.com.cpa.spring.modules.feed.comments.dtos.CreateCommentDTO;
import br.com.cpa.spring.modules.feed.comments.services.CreateCommentService;
import br.com.cpa.spring.modules.feed.comments.services.FindCommentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Comments", description = "Comments Routes")
@SecurityRequirement(name = "jwtauth")
@RequestMapping("/feed/{postId}/comments")
public class CommentController {
  @Autowired
  CreateCommentService createCommentService;
  @Autowired
  FindCommentsService findCommentservice;

  @GetMapping
  @Operation(summary = "Create comment to post")
  public ResponseEntity<List<Comment>> getComments(@PathVariable String postId) {
    List<Comment> comment = findCommentservice.execute(postId);
    return ResponseEntity.status(200).body(comment);
  }

  @PostMapping
  @Operation(summary = "Create comment to post")
  public ResponseEntity<Comment> createPostComment(@PathVariable String postId,
      @Valid @RequestBody CreateCommentDTO commentData) {
    System.out.println(postId);
    System.out.println(commentData.getPatientId());
    Comment comment = createCommentService.execute(postId, commentData);
    return ResponseEntity.status(201).body(comment);
  }

}
