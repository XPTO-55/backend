package br.com.cpa.spring.modules.forums;

import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cpa.spring.models.Forum;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/forums")
@Tag(name = "Forum", description = "Forum Routes")
@SecurityRequirement(name = "jwtauth")
public class ForumController {
  @Autowired
  ForumRepository repository;

  @GetMapping
  public List<Forum> index() {
    return this.repository.findAll();
  }

  @PostMapping
  public Forum store(@RequestBody Forum forum) {
    return this.repository.save(forum);
  }
}
