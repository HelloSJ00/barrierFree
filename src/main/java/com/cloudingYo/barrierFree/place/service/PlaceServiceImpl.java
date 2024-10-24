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
    private final RestTemplate restTemplate;
    @Value("${ai.url}")
    private String AI_URL;

    /*
        DTO를 엔티티로 변환하는 함수
     */
    private Place convertToEntity(PlaceDTO placeDTO){
        return Place.builder()
                .placename(placeDTO.getPLACE_NM())
                .latitude(placeDTO.getLatitude())
                .longitude(placeDTO.getLongitude())
                .build();
    }

    @Override
    public List<PlaceWithBookmarkDTO> getRealTimeRecommendPlaceList(Long userId) {
        // 1. 외부 API에서 추천 장소 리스트 가져오기
        ResponseEntity<List<PlaceDTO>> response = restTemplate.exchange(
                AI_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PlaceDTO>>() {}  // 리스트로 바로 변환
        );
        List<PlaceDTO> recommendedPlaces = response.getBody();  // 추천 장소 리스트

        // 2. 내부 DB에서 유저가 북마크한 장소 리스트 가져오기
        List<FavoritePlace> bookmarkedPlaces = favoritePlaceRepository.findByUserId(userId);

        // 북마크된 장소의 ID 리스트 생성
        List<Long> bookmarkedPlaceIds = bookmarkedPlaces.stream()
                .map(FavoritePlace::getPlaceId)  // FavoritePlace에서 placeId 추출
                .toList();

        // 3. 추천 장소에 북마크 정보 병합
        return recommendedPlaces.stream()
                .map(place -> PlaceWithBookmarkDTO.builder()
                        .PLACE_NM(place.getPLACE_NM())
                        .latitude(place.getLatitude())
                        .longitude(place.getLongitude())
                        .bookmarked(bookmarkedPlaceIds.contains(place.getId())) // 북마크 여부 확인
                        .build()
                )
                .toList();
    }


    @Override
    public PlaceDetailsDTO getPlaceDetails(String PLACE_KEY) {
        // place_key를 포함한 URL
        String url = AI_URL + "/place/" + PLACE_KEY + "/details";

        // RestTemplate을 사용하여 GET 요청을 보내고, PlaceDetailsDTO를 반환받음
        ResponseEntity<PlaceDetailsDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null, // 요청 본문이 없으므로 null
                PlaceDetailsDTO.class
        );

        // 응답 결과를 반환
        return response.getBody();
    }

    @Override
    public void savePlaces(List<PlaceDTO> places){
        for (PlaceDTO place : places) {
            Place entity = convertToEntity(place);
            placeRepository.save(entity);
        }
    }

    @Override
    public void updatePlaces(){

    }

}
