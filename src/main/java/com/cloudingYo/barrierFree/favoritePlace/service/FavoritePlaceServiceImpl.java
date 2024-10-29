package com.cloudingYo.barrierFree.favoritePlace.service;

import com.cloudingYo.barrierFree.favoritePlace.dto.FavoritePlaceDTO;
import com.cloudingYo.barrierFree.favoritePlace.entity.FavoritePlace;
import com.cloudingYo.barrierFree.favoritePlace.repository.FavoritePlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class FavoritePlaceServiceImpl implements FavoritePlaceService {

    private final FavoritePlaceRepository favoritePlaceRepository;

    @Override
    public void registerFavoritePlace(Long placeId, Long userId){
        FavoritePlace favoritePlace = FavoritePlace.builder()
                .placeId(placeId)
                .userId(userId)
                .build();
        favoritePlaceRepository.save(favoritePlace);
    }

    @Override
    public void deleteFavoritePlace(Long placeId, Long userId){
        favoritePlaceRepository.deleteByPlaceIdAndUserId(placeId, userId);
    }

    @Override
    public Page<FavoritePlaceDTO> getFavoritePlaceList(Long userId, int page) {
        Pageable pageable = PageRequest.of(page, 5); // 페이지당 5개 설정
        return favoritePlaceRepository.findByUserIdOrderByCreatedDateDesc(userId, pageable)
                .map(place -> FavoritePlaceDTO.builder()
                        .placename(place.getPlacename())
                        .bookmarked(true)
                        .latitude(place.getLatitude())
                        .longitude(place.getLongitude())
                        .build()
                );
    }
}
