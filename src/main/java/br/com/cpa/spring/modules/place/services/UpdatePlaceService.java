package br.com.cpa.spring.modules.place.services;

import br.com.cpa.spring.models.Address;
import br.com.cpa.spring.models.Place;
import br.com.cpa.spring.modules.place.PlaceRepository;
import br.com.cpa.spring.modules.place.dto.CreatePlaceDTO;
import br.com.cpa.spring.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdatePlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Place execute(Long id, CreatePlaceDTO newPlace) {
        Place place = new Place();
        place.setIdLugar(id);
        place.setNomeLugar(newPlace.getNomeLugar());
        place.setObservacoes(newPlace.getObservacoes());

        if (newPlace.getAddress() != null) {
            Address address = new Address();
            address.setStreet(newPlace.getAddress().getStreet());
            address.setDistrict(newPlace.getAddress().getDistrict());
            address.setNumber(newPlace.getAddress().getNumber());
            address.setComplement(newPlace.getAddress().getComplement());
            address.setZipcode(newPlace.getAddress().getZipcode());
            address.setCity(newPlace.getAddress().getCity());
            address.setUf(newPlace.getAddress().getUf());
            addressRepository.save(address);
            place.setAddress(address);
        }
        return placeRepository.save(place);
    }
}
