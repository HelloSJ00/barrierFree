package com.cloudingYo.barrierFree.place.controller;

import com.cloudingYo.barrierFree.place.dto.PlaceDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceDetailsDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceResponseDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceWithBookmarkDTO;
import com.cloudingYo.barrierFree.place.service.PlaceService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlaceControllerImpl implements PlaceController{

    PlaceService placeService;

    @Override
    @GetMapping("/place/real-time-recommend")
    public ResponseEntity<PlaceResponseDTO<List<PlaceWithBookmarkDTO>>> getRealTimeRecommendPlaceList(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");  // 세션에서 불러오기

        List<PlaceWithBookmarkDTO> realTimeRecommendPlaceList = placeService.getRealTimeRecommendPlaceList(userId);
        return ResponseEntity.ok(PlaceResponseDTO.success("Success", realTimeRecommendPlaceList));
    }

    @Override
    @GetMapping("/place/details")
    public ResponseEntity<PlaceResponseDTO<PlaceDetailsDTO>> getPlaceDetails(@RequestParam("PLACE_KEY") String PLACE_KEY) {
        return null;
    }

}
