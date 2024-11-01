package com.cloudingYo.barrierFree.place.controller;

import com.cloudingYo.barrierFree.place.dto.PlaceDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceDetailsDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceResponseDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceWithBookmarkDTO;
import com.cloudingYo.barrierFree.place.service.PlaceService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/spring/place")
public class PlaceControllerImpl implements PlaceController{

    private final PlaceService placeService;

    @Override
    @GetMapping("/coordinate")
    public ResponseEntity<PlaceResponseDTO<PlaceDTO>> getPlaceCoordinate(@RequestParam("placeKey") int placeKey) {
        try {
            // PlaceDTO를 가져옴
            PlaceDTO placeDTO = placeService.getPlaceCoordinate(placeKey);

            // 성공 응답 생성
            PlaceResponseDTO<PlaceDTO> response = PlaceResponseDTO.success("Place details fetched successfully", placeDTO);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching place details for placeKey {}: {}", placeKey, e.getMessage());

            // 예외 발생 시 실패 응답 생성
            PlaceResponseDTO<PlaceDTO> response = PlaceResponseDTO.fail("Failed to fetch place details");
            return ResponseEntity.status(400).body(response);
        }
    }
}
