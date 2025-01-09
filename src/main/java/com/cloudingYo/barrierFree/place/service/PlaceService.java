package com.cloudingYo.barrierFree.place.service;


import com.cloudingYo.barrierFree.place.dto.resp.PlaceCoordinateDTO;

public interface PlaceService {
    PlaceCoordinateDTO getPlaceCoordinateV2(Long placeKey);
}
