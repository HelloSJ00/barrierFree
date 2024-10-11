package com.cloudingYo.barrierFree.place.service;

import com.cloudingYo.barrierFree.place.dto.PlaceAIResDTO;
import com.cloudingYo.barrierFree.place.dto.PlaceDetailsDTO;
import com.cloudingYo.barrierFree.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final RestTemplate restTemplate;
    @Value("${ai.url}")
    private String AI_URL;

    @Override
    public List<PlaceAIResDTO> getRealTimeRecommendPlaceList() {
        ResponseEntity<List<PlaceAIResDTO>> response = restTemplate.exchange(
                AI_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PlaceAIResDTO>>() {}
        );
        return response.getBody();
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

}
