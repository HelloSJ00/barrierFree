package com.cloudingYo.barrierFree.favoritePlace.controller;

import com.cloudingYo.barrierFree.favoritePlace.dto.FavoritePlaceDTO;
import com.cloudingYo.barrierFree.favoritePlace.dto.FavoritePlaceResponseDTO;
import com.cloudingYo.barrierFree.favoritePlace.service.FavoritePlaceService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favoritePlace")
public class FavoritePlaceControllerImpl implements FavoritePlaceController {
    private final FavoritePlaceService favoritePlaceService;


    // userId와 page를 인자로 받아 5개씩 페이징된 데이터 반환
    @Override
    @GetMapping("/list")
    public ResponseEntity<FavoritePlaceResponseDTO<?>> getFavoritePlaceList(
            @RequestParam(defaultValue = "0") int page,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");
        Page<FavoritePlaceDTO> favoritePlaces = favoritePlaceService.getFavoritePlaceList(userId, page);

        // FavoritePlaceResponseDTO로 응답 생성
        FavoritePlaceResponseDTO<Page<FavoritePlaceDTO>> response = FavoritePlaceResponseDTO.<Page<FavoritePlaceDTO>>builder()
                .message("Favorite places fetched successfully")
                .status(HttpStatus.OK.value())
                .data(favoritePlaces)
                .build();

        return ResponseEntity.ok(response);
    }
}
