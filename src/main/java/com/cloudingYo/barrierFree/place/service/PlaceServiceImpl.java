package com.cloudingYo.barrierFree.place.service;

import com.cloudingYo.barrierFree.place.dto.PlaceDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceDetailsDTO;
import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final RestTemplate restTemplate;
    @Value("${ai.url}")
    private String AI_URL;

    /*
        DTO를 엔티티로 변환하는 함수
     */
    private Place convertToEntity(PlaceDTO placeDTO){
        return Place.builder()
                .placename(placeDTO.getPlacename())
                .latitude(placeDTO.getLatitude())
                .longitude(placeDTO.getLongitude())
                .build();
    }

    @Override
    public List<PlaceDTO> getRealTimeRecommendPlaceList() {
        ResponseEntity<List<PlaceDTO>> response = restTemplate.exchange(
                AI_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PlaceDTO>>() {}
        );
        List<PlaceDTO> recommendedPlaces = response.getBody();
        if(recommendedPlaces != null){
            savePlaces(recommendedPlaces);
        }
        else{
            // 추천 장소가 없을 경우, 빈 리스트를 반환
            recommendedPlaces = List.of();
        }
        return recommendedPlaces;
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
