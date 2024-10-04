package com.cloudingYo.barrierFree.place.service;

import com.cloudingYo.barrierFree.place.dto.PlaceAIResDTO;
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
    private final String AI_URL = "건우 머신러닝 apu url";

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
}
