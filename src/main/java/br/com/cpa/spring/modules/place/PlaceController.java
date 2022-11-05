package br.com.cpa.spring.modules.place;

import br.com.cpa.spring.models.Place;
import br.com.cpa.spring.modules.place.dto.CreatePlaceDTO;
import br.com.cpa.spring.modules.place.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Place", description = "Places Routes")
@RequestMapping("/places")
public class PlaceController {
    @Autowired
    private CreatePlaceService createPlaceService;
    @Autowired
    private FindPlaceService findPlaceService;

    @Autowired
    private DeletePlaceService deletePlaceService;

    @Autowired
    private FindOnePlaceService fIndOnePlaceService;

    @Autowired
    private UpdatePlaceService updatePlaceService;


    @Operation(summary = "Create new place")
    @PostMapping
    public ResponseEntity<Place> save(@RequestBody @Valid CreatePlaceDTO createPlace) {
        Place lugar = createPlaceService.execute(createPlace);
        return ResponseEntity.status(201).body(lugar);
    }

    @Operation(summary = "List all places")
    @GetMapping
    public ResponseEntity<List<Place>> index() {
        List<Place> lista = findPlaceService.execute();
        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }

    @Operation(summary = "Delete expecific place")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deletePlaceService.execute(id);
        return ResponseEntity.status(204).build();

    }

    @Operation(summary = "List expecific place by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Place> place = fIndOnePlaceService.execute(id);
        return place.isEmpty()
                ? ResponseEntity.status(404).build()
                : ResponseEntity.status(200).body(place);
    }

    @Operation(summary = "Update expecific place by id")
    @PutMapping("/{id}")
    public ResponseEntity<Place> update(@PathVariable Long id,
                                        @RequestBody @Valid CreatePlaceDTO newPlace) {
        Place place = updatePlaceService.execute(id, newPlace);
        return ResponseEntity.status(200).body(place);
    }
}
