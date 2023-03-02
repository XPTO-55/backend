package br.com.cpa.spring.modules.place.services;

import br.com.cpa.spring.models.Address;
import br.com.cpa.spring.models.Place;
import br.com.cpa.spring.modules.place.PlaceRepository;
import br.com.cpa.spring.modules.place.dto.CreatePlaceDTO;
import br.com.cpa.spring.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

@Service
public class CreatePlaceService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PlaceRepository placeRepository;

    public Place execute(CreatePlaceDTO createPlaceDTO) {
        Place place = new Place();
        place.setNomeLugar(createPlaceDTO.getNomeLugar());
        place.setObservacoes(createPlaceDTO.getObservacoes());
        place.setImageUrl(createPlaceDTO.getImageUrl());
        if (createPlaceDTO.getAddress() != null) {
            Address address = new Address();
            address.setStreet(createPlaceDTO.getAddress().getStreet());
            address.setDistrict(createPlaceDTO.getAddress().getDistrict());
            address.setNumber(createPlaceDTO.getAddress().getNumber());
            address.setComplement(createPlaceDTO.getAddress().getComplement());
            address.setZipcode(createPlaceDTO.getAddress().getZipcode());
            address.setCity(createPlaceDTO.getAddress().getCity());
            address.setUf(createPlaceDTO.getAddress().getUf());
            address.setLatitute(createPlaceDTO.getAddress().getLatitute());
            address.setLongitude((createPlaceDTO.getAddress().getLongitude()));
            addressRepository.save(address);
            place.setAddress(address);
        }
        return placeRepository.save(place);
    }

}
