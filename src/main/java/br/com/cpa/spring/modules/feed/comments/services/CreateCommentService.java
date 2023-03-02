package br.com.cpa.spring.modules.feed.comments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.cpa.spring.models.Comment;
import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.modules.feed.comments.CommentRepository;
import br.com.cpa.spring.modules.feed.comments.dtos.CreateCommentDTO;
import br.com.cpa.spring.modules.user.patient.PatientRepository;

@Service
public class CreateCommentService {
  @Autowired
  CommentRepository repository;

  @Autowired
  PatientRepository patientRepository;

  public Comment execute(String postId, CreateCommentDTO commentData) {
    if (!patientRepository.existsById(commentData.getPatientId())) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
    }
    Patient patient = patientRepository.findById(commentData.getPatientId()).get();
    Comment comment = new Comment();
    comment.setComment(commentData.getComment());
    comment.setPatient(patient);
    comment.setPostId(postId);
    return repository.save(comment);
  }
}
