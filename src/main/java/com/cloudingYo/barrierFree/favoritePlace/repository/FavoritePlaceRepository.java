package com.cloudingYo.barrierFree.favoritePlace.repository;

import com.cloudingYo.barrierFree.favoritePlace.entity.FavoritePlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritePlaceRepository extends JpaRepository<FavoritePlace, Long> {
    FavoritePlace findByPlaceId(Long placeId);
    FavoritePlace findByUserId(Long userId);
    FavoritePlace findByPlaceIdAndUserId(Long placeId, Long userId);
    void deleteByPlaceIdAndUserId(Long placeId, Long userId);
}
