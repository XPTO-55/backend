package br.com.cpa.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cpa.spring.models.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

}
