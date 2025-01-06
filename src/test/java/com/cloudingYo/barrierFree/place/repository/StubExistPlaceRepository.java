package com.cloudingYo.barrierFree.place.repository;

import com.cloudingYo.barrierFree.place.dto.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.entity.Place;

import java.util.Optional;


public class StubExistPlaceRepository implements PlaceRepository{
    @Override
    public Optional<Place> findByPlaceKey(int placeKey) {
        return Optional.empty();
    }

    @Override
    public Optional<PlaceCoordinateDTO> findCoordinateByPlaceKey(int placeKey) {
        return Optional.ofNullable(PlaceCoordinateDTO.builder()
                .PLACE_KEY(1)
                .latitude(2.0)
                .longitude(3.0)
                .build());
    }
}
