package com.cloudingYo.barrierFree.place.repository;

import com.cloudingYo.barrierFree.place.dto.resp.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.entity.Place;

public interface PlaceRepository {
    Place findByPlaceKey(Long placeKey);
    PlaceCoordinateDTO findCoordinateByPlaceKey(Long placeKey);
}
