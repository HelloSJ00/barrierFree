package com.cloudingYo.barrierFree.favoritePlace.controller;

import com.cloudingYo.barrierFree.favoritePlace.dto.FavoritePlaceDTO;
import com.cloudingYo.barrierFree.favoritePlace.dto.FavoritePlaceResponseDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FavoritePlaceController {
    ResponseEntity<FavoritePlaceResponseDTO<?>> registerFavoritePlace(@RequestParam int placeKey,HttpSession session);
    ResponseEntity<FavoritePlaceResponseDTO<?>> deleteFavoritePlace(@RequestParam int placeKey,HttpSession session);
    ResponseEntity<FavoritePlaceResponseDTO<?>> getFavoritePlaceList(@RequestParam(defaultValue = "0") int page, HttpSession session);
}
