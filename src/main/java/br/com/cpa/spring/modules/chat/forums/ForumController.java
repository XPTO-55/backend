package br.com.cpa.spring.modules.chat.forums;

import java.io.IOException;
import java.util.List;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.cpa.spring.models.Forum;
import br.com.cpa.spring.modules.chat.forums.dto.CreateForumDTO;
import br.com.cpa.spring.modules.chat.forums.services.ForumProfileImage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/forums")
@Tag(name = "Forum", description = "Forum Routes")
@SecurityRequirement(name = "jwtauth")
public class ForumController {
  @Autowired
  ForumRepository repository;
  @Autowired
  ForumProfileImage service;

  @Operation(summary = "Get all forums")
  @GetMapping
  public ResponseEntity<List<Forum>> index() {
    List<Forum> list = this.repository.findAll();
    return list.isEmpty() ? ResponseEntity.noContent().build()
        : ResponseEntity.status(200).body(list);
  }

  @Operation(summary = "Create new forum")
  @PostMapping
  public ResponseEntity<Forum> store(@RequestBody CreateForumDTO forumData) {
    Forum forum = new Forum();
    forum.setName(forumData.getName());
    return ResponseEntity.ok(this.repository.save(forum));
  }

  @Operation(summary = "Update forum by expecific id")
  @PutMapping("/{forumId}")
  public ResponseEntity<Forum> update(@PathVariable String forumId, @RequestBody CreateForumDTO forumData) {
    if (!this.repository.existsById(forumId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found");
    }
    Forum forum = new Forum();
    forum.setName(forumData.getName());
    return ResponseEntity.ok(this.repository.save(forum));
  }

  @Operation(summary = "Delete forum by expecific id")
  @DeleteMapping("/{forumId}")
  public ResponseEntity<Void> delete(@PathVariable String forumId) {
    if (!repository.existsById(forumId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found");
    }
    Forum forum = repository.findById(forumId).get();
    forum.setDeletedAt(LocalDateTime.now());
    repository.save(forum);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Get forum imageProfile from expecific by ID")
  @GetMapping(value = "/{forumId}/imageProfile", produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> getProfileImage(
      @PathVariable String forumId) {

    byte[] foto;
    try {
      foto = service.get(forumId);
      return ResponseEntity.status(200).header("content-disposition", "attachment; filename=\"forum.jpg\"")
          .body(foto);
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível carregar a imagem");
    }
  }

  @Operation(summary = "Put patient imageProfile from expecific by ID")
  @PatchMapping(value = "/{forumId}/imageProfile", consumes = "image/*")
  public ResponseEntity<Void> patchProfileImage(
      @PathVariable String forumId,
      @RequestBody byte[] novaFoto) {

    service.save(forumId, novaFoto);

    return ResponseEntity.status(200).build();
  }
}
