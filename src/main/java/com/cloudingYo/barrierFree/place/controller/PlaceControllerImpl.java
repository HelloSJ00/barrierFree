package com.cloudingYo.barrierFree.place.controller;

import com.cloudingYo.barrierFree.place.dto.PlaceAIResDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceDetailsDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceResponseDTO;
import com.cloudingYo.barrierFree.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaceControllerImpl implements PlaceController{

    PlaceService placeService;

    @Override
    @GetMapping("/place/real-time-recommend")
    public ResponseEntity<List<PlaceAIResDTO>> getRealTimeRecommendPlaceList() {
        return ResponseEntity.ok(placeService.getRealTimeRecommendPlaceList());
    }

    @Override
    @GetMapping("/place/details")
    public ResponseEntity<PlaceDetailsDTO> getPlaceDetails(@RequestParam("PLACE_KEY") String PLACE_KEY) {
        return null;
    }

}
