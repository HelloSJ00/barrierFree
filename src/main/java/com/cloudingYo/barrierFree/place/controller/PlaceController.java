package com.cloudingYo.barrierFree.place.controller;

import com.cloudingYo.barrierFree.place.dto.PlaceDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceDetailsDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceResponseDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceWithBookmarkDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PlaceController {
    ResponseEntity<PlaceResponseDTO<PlaceDTO>> getPlaceCoordinate(@RequestParam("PLACE_KEY") int placeKey);
}
