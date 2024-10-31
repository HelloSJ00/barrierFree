package com.cloudingYo.barrierFree.place.service;

import com.cloudingYo.barrierFree.place.dto.PlaceDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceDetailsDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceWithBookmarkDTO;

import java.util.List;

public interface PlaceService {
    PlaceDTO getPlaceCoordinate(int placeKey);
}
