package com.cloudingYo.barrierFree.place.controller;

import com.cloudingYo.barrierFree.place.dto.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface PlaceController {
    ResponseEntity<PlaceResponseDTO<PlaceCoordinateDTO>> getPlaceCoordinate(@RequestParam("placeKey") int placeKey);
    ResponseEntity<PlaceResponseDTO<PlaceCoordinateDTO>> getPlaceCoordinateV2(@RequestParam("placeKey") int placeKey);

}
