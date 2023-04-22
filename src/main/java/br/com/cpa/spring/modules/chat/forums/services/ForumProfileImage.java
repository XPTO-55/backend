package br.com.cpa.spring.modules.chat.forums.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.util.IOUtils;

import br.com.cpa.spring.helpers.AwsHelper;
import br.com.cpa.spring.models.Forum;
import br.com.cpa.spring.modules.chat.forums.ForumRepository;

@Service
public class ForumProfileImage {
  @Autowired
  private ForumRepository repository;

  @Autowired
  AwsHelper awsHelper;

  public void save(String id, byte[] foto) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Forum not found");
    }
    Forum f = repository.findById(id).get();
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
    String filename = f.getId() + "-" + currentDateTime.format(formatter);
    awsHelper.save(foto, filename);
    f.setImageUrl(filename);
    repository.save(f);
  }

  public byte[] get(String id) throws IOException {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
    Forum f = repository.findById(id).get();
    return IOUtils.toByteArray(awsHelper.findByName(f.getImageUrl()));
  }
}
