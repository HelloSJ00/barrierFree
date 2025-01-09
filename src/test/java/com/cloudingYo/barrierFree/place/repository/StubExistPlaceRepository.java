package com.cloudingYo.barrierFree.place.repository;

import com.cloudingYo.barrierFree.place.dto.resp.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.entity.Place;


public class StubExistPlaceRepository implements PlaceRepository{
    @Override
    public Place findByPlaceKey(Long placeKey) {
        return null;
    }

    @Override
    public PlaceCoordinateDTO findCoordinateByPlaceKey(Long placeKey) {
        return PlaceCoordinateDTO.builder()
                .PLACE_KEY(1L)
                .latitude(2.0)
                .longitude(3.0)
                .build();
    }
}
