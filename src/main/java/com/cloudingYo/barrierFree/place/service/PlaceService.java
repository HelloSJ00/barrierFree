package com.cloudingYo.barrierFree.place.service;

import com.cloudingYo.barrierFree.place.dto.PlaceAIResDTO;

import java.util.List;

public interface PlaceService {

    /*
        * AI 서버에서 실시간 장소 리스트를 가져오는 메소드
     */
    List<PlaceAIResDTO> getRealTimeRecommendPlaceList();
}
