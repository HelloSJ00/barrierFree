package com.cloudingYo.barrierFree.favoritePlace.repository;

import com.cloudingYo.barrierFree.favoritePlace.entity.FavoritePlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritePlaceRepository extends JpaRepository<FavoritePlace, Long> {
    FavoritePlace findByPlaceId(Long placeId);
    FavoritePlace findByPlaceIdAndUserId(Long placeId, Long userId);
    List<FavoritePlace> findByUserId(Long userId);
    void deleteByPlaceIdAndUserId(Long placeId, Long userId);
}
