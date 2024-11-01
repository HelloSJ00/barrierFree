package com.cloudingYo.barrierFree.favoritePlace.service;

import com.cloudingYo.barrierFree.favoritePlace.dto.FavoritePlaceDTO;
import com.cloudingYo.barrierFree.favoritePlace.entity.FavoritePlace;
import com.cloudingYo.barrierFree.favoritePlace.repository.FavoritePlaceRepository;
import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FavoritePlaceServiceImpl implements FavoritePlaceService {

    private final FavoritePlaceRepository favoritePlaceRepository;
    private final PlaceRepository placeRepository;

    @Override
    public void registerFavoritePlace(int placeKey, Long userId){
        Place place = placeRepository.findByPlaceKey(placeKey).get();
        FavoritePlace favoritePlace = FavoritePlace.builder()
                .placeKey(placeKey)
                .placeId(place.getId())
                .userId(userId)
                .placename(place.getPlacename())
                .longitude(place.getLongitude())
                .latitude(place.getLatitude())
                .build();
        favoritePlaceRepository.save(favoritePlace);
    }

    @Override
    public void deleteFavoritePlace(int placeKey, Long userId){
        favoritePlaceRepository.deleteByPlaceKeyAndUserId(placeKey, userId);
    }

    @Override
    public Page<FavoritePlaceDTO> getFavoritePlaceList(Long userId, int page) {
        Pageable pageable = PageRequest.of(page, 5); // 페이지당 5개 설정

        // Streamable<FavoritePlaceDTO>을 List<FavoritePlaceDTO>로 변환
        List<FavoritePlaceDTO> favoritePlaceDTOList = favoritePlaceRepository
                .findByUserIdOrderByCreatedDateDesc(userId, pageable)
                .stream()
                .map(favoritePlace -> {
                    Optional<Place> placeOptional = placeRepository.findByPlaceKey(favoritePlace.getPlaceKey());

                    return placeOptional.map(place -> FavoritePlaceDTO.builder()
                            .placeKey(favoritePlace.getPlaceKey())
                            .placename(place.getPlacename())
                            .latitude(place.getLatitude())
                            .longitude(place.getLongitude())
                            .bookmarked(true)
                            .build()).orElse(null);
                })
                .filter(dto -> dto != null) // null 값 제거
                .collect(Collectors.toList());

        // List<FavoritePlaceDTO>를 Page<FavoritePlaceDTO>로 변환
        return new PageImpl<>(favoritePlaceDTOList, pageable, favoritePlaceDTOList.size());
    }
}
