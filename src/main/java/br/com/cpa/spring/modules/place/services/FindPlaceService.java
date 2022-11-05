package br.com.cpa.spring.modules.place.services;

import br.com.cpa.spring.models.Place;
import br.com.cpa.spring.modules.place.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindPlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    public List<Place> execute() {
        return placeRepository.findAll();
    }
}
