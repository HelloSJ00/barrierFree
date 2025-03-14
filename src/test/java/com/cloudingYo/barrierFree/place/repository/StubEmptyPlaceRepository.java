package com.cloudingYo.barrierFree.place.repository;


import com.cloudingYo.barrierFree.place.dto.resp.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.entity.Place;

public class StubEmptyPlaceRepository implements PlaceRepository {

    @Override
    public Place findByPlaceKey(Long placeKey) {
        return null;
    }

    @Override
    public PlaceCoordinateDTO findCoordinateByPlaceKey(Long placeKey) {
        return null;
    }
}
