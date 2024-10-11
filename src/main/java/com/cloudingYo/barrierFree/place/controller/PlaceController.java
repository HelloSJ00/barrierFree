package com.cloudingYo.barrierFree.place.controller;

import com.cloudingYo.barrierFree.place.dto.PlaceAIResDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceDetailsDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PlaceController {
    /*
     * AI 서버에서 실시간 장소 리스트를 가져오는 메소드
     */
    ResponseEntity<List<PlaceAIResDTO>> getRealTimeRecommendPlaceList();
    ResponseEntity<PlaceDetailsDTO> getPlaceDetails(@RequestParam("PLACE_KEY") String PLACE_KEY);
}
