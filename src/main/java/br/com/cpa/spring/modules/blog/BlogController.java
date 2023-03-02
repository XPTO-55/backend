package br.com.cpa.spring.modules.blog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cpa.spring.resources.PilhaObj;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
  PilhaObj<String> pesquisa = new PilhaObj<>(100);

  @GetMapping
  public ResponseEntity<String> getSearch() {
    return ResponseEntity.ok(pesquisa.peek());
  }

  @PostMapping
  public ResponseEntity<String> search(@RequestBody String busca) {
    pesquisa.push(busca);
    return ResponseEntity.ok(busca);
  }
}
