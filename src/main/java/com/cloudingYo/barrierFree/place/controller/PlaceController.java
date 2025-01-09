package com.cloudingYo.barrierFree.place.controller;

import com.cloudingYo.barrierFree.common.entity.ApiResponse;
import com.cloudingYo.barrierFree.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.cloudingYo.barrierFree.common.exception.enums.SuccessType.FIND_PLACE_INFORMATION;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/spring/place")
public class PlaceController{

    private final PlaceService placeService;

    @GetMapping("/coordinate/V2")
    public ResponseEntity<ApiResponse<?>> getPlaceCoordinateV2(@RequestParam("placeKey") Long placeKey) {
        return ResponseEntity.ok(ApiResponse.success(FIND_PLACE_INFORMATION,placeService.getPlaceCoordinateV2(placeKey)));
    }
}
