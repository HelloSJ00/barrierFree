package com.cloudingYo.barrierFree.place.service;

import com.cloudingYo.barrierFree.favoritePlace.entity.FavoritePlace;
import com.cloudingYo.barrierFree.favoritePlace.repository.FavoritePlaceRepository;
import com.cloudingYo.barrierFree.place.dto.PlaceDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceDetailsDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceWithBookmarkDTO;
import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final FavoritePlaceRepository favoritePlaceRepository;

    // placeKey가 int 타입인 경우 메서드 수정
    @Override
    public PlaceDTO getPlaceCoordinate(int placeKey) {
        Place place = placeRepository.findByPlaceKey(placeKey)
                .orElseThrow(() -> new IllegalArgumentException("Place not found"));

        // Place 엔티티를 PlaceDTO로 변환
        return PlaceDTO.builder()
                .id(place.getId())
                .PLACE_NM(place.getPlacename())
                .en_placename(place.getEn_placename())
                .PLACE_KEY(place.getPlaceKey()) // int 타입
                .REC_SCORE((long) place.getTotalScroe()) // 필요한 경우 타입 변환
                .category(place.getCategory())
                .latitude(place.getLatitude())
                .longitude(place.getLongitude())
                .build();
    }
}