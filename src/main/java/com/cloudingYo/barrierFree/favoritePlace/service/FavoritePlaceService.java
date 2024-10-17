package com.cloudingYo.barrierFree.favoritePlace.service;

import com.cloudingYo.barrierFree.favoritePlace.dto.FavoritePlaceDTO;

import java.util.List;

public interface FavoritePlaceService {

    void registerFavoritePlace(Long placeId, Long userId);
    void deleteFavoritePlace(Long placeId, Long userId);
    List<FavoritePlaceDTO> getFavoritePlaceList(Long userId);
}
