package com.cloudingYo.barrierFree.place.repository;


import com.cloudingYo.barrierFree.place.dto.resp.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.entity.Place;

import java.util.Optional;

public class StubEmptyPlaceRepository implements PlaceRepository {

    @Override
    public Optional<Place> findByPlaceKey(int placeKey) {
        return Optional.empty();
    }

    @Override
    public Optional<PlaceCoordinateDTO> findCoordinateByPlaceKey(int placeKey) {
        return Optional.empty();
    }
}
