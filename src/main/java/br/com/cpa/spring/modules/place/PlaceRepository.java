package br.com.cpa.spring.modules.place;

import br.com.cpa.spring.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaceRepository extends JpaRepository<Place, Long> {
  @Modifying
  @Query(value = "UPDATE places p SET deleted_at=now() WHERE p.id=:id")
  void deleteById(@Param("id") Long id);
}
