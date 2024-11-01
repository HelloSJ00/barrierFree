package com.cloudingYo.barrierFree.place.service;


import com.cloudingYo.barrierFree.place.dto.PlaceCoordinateDTO;

public interface PlaceService {
    PlaceCoordinateDTO getPlaceCoordinate(int placeKey);
    PlaceCoordinateDTO getPlaceCoordinateV2(int placeKey);
}
