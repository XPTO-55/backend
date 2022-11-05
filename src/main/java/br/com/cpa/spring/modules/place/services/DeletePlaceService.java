package br.com.cpa.spring.modules.place.services;

import br.com.cpa.spring.models.Place;
import br.com.cpa.spring.modules.place.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeletePlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    public void execute(Long id) {
        placeRepository.deleteById(id);
    }
}
