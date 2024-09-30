package com.cloudingYo.barrierFree.place.service;

import com.cloudingYo.barrierFree.place.dto.PlaceDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceResponseDTO;

public interface PlaceService {
    PlaceResponseDTO getPlace(PlaceDTO placeDTO);
    void registerAllPlace(PlaceDTO placeDTO);
    void registerPlace(PlaceDTO placeDTO);
    void updatePlace(PlaceDTO placeDTO);
    void deletePlace(PlaceDTO placeDTO);
}
