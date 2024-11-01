package com.cloudingYo.barrierFree.place.repository;

import com.cloudingYo.barrierFree.place.dto.PlaceCoordinateDTO;

import java.util.Optional;

public interface PlaceRepositoryCustom {
    Optional<PlaceCoordinateDTO> findCoordinateByPlaceKey(int placeKey);
}
