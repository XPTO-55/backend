package br.com.cpa.spring.modules.place;

import br.com.cpa.spring.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
