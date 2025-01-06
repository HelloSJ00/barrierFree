package com.cloudingYo.barrierFree.place.repository;

import com.cloudingYo.barrierFree.place.dto.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository {
    Optional<Place> findByPlaceKey(int placeKey);
    Optional<PlaceCoordinateDTO> findCoordinateByPlaceKey(int placeKey);
}
