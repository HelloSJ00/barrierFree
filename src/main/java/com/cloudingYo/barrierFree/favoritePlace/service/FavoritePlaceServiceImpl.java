package com.cloudingYo.barrierFree.favoritePlace.service;

import com.cloudingYo.barrierFree.favoritePlace.dto.FavoritePlaceDTO;
import com.cloudingYo.barrierFree.favoritePlace.entity.FavoritePlace;
import com.cloudingYo.barrierFree.favoritePlace.repository.FavoritePlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public List<FavoritePlaceDTO> getFavoritePlaceList(Long userId) {
        List<FavoritePlace> favoritePlaceList = favoritePlaceRepository.findByUserId(userId);

        // 빌더 패턴을 사용하여 FavoritePlaceDTO로 변환
        return favoritePlaceList.stream()
                .map(place -> FavoritePlaceDTO.builder()
                        .placeId(place.getPlaceId())  // getter 메서드 사용
                        .userId(place.getUserId())    // getter 메서드 사용
                        .build()
                )
                .collect(Collectors.toList());  // .toList() 대신 Collectors.toList() 사용
    }
}
