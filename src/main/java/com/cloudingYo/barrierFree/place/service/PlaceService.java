package com.cloudingYo.barrierFree.place.service;

import com.cloudingYo.barrierFree.place.dto.PlaceDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceDetailsDTO;

import java.util.List;

public interface PlaceService {

    /*
        * AI 서버에서 실시간 장소 리스트를 가져오는 메소드
     */
    List<PlaceDTO> getRealTimeRecommendPlaceList();
    PlaceDetailsDTO getPlaceDetails(String PLACE_KEY);
    void savePlaces(List<PlaceDTO> places);
    void updatePlaces();
}
