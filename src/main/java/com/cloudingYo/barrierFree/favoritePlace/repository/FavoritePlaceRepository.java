package com.cloudingYo.barrierFree.favoritePlace.repository;

import com.cloudingYo.barrierFree.favoritePlace.entity.FavoritePlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritePlaceRepository extends JpaRepository<FavoritePlace, Long> {
    FavoritePlace findByPlaceId(Long placeId);
    FavoritePlace findByPlaceKeyAndUserId(int placeKey, Long userId);
    List<FavoritePlace> findByUserId(Long userId);
    void deleteByPlaceKeyAndUserId(int placeKey, Long userId);
    Page<FavoritePlace> findByUserIdOrderByCreatedDateDesc(Long userId, Pageable pageable);

}
