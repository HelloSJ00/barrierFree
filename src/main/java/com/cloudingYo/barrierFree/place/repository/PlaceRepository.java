package com.cloudingYo.barrierFree.place.repository;

import com.cloudingYo.barrierFree.place.dto.resp.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.entity.Place;

import java.util.Optional;

public interface PlaceRepository {
    Optional<Place> findByPlaceKey(Long placeKey);
    Optional<PlaceCoordinateDTO> findCoordinateByPlaceKey(Long placeKey);
}
