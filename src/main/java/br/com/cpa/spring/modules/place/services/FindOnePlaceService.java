package br.com.cpa.spring.modules.place.services;

import br.com.cpa.spring.models.Place;
import br.com.cpa.spring.modules.place.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindOnePlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    public Optional<Place> execute(Long id) {
        return placeRepository.findById(id);
    }
}
