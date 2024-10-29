package com.cloudingYo.barrierFree.favoritePlace.service;

import com.cloudingYo.barrierFree.favoritePlace.dto.FavoritePlaceDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FavoritePlaceService {

    void registerFavoritePlace(Long placeId, Long userId);
    void deleteFavoritePlace(Long placeId, Long userId);
    public Page<FavoritePlaceDTO> getFavoritePlaceList(Long userId, int page);
}
