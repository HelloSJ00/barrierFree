package com.cloudingYo.barrierFree.place.service;

import com.cloudingYo.barrierFree.place.dto.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.place.exception.NotFoundPlaceException;
import com.cloudingYo.barrierFree.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    // placeKey가 int 타입인 경우 메서드 수정
    @Override
    @Transactional(readOnly = true)
    public PlaceCoordinateDTO getPlaceCoordinate(int placeKey) {
        Place place = placeRepository.findByPlaceKey(placeKey)
                .orElseThrow(() -> new NotFoundPlaceException("Place not found"));

        // Place 엔티티를 PlaceDTO로 변환
        return PlaceCoordinateDTO.builder()
                .PLACE_KEY(place.getPlaceKey())
                .latitude(place.getLatitude())
                .longitude(place.getLongitude())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public PlaceCoordinateDTO getPlaceCoordinateV2(int placeKey) {
        return placeRepository.findCoordinateByPlaceKey(placeKey)
                .orElseThrow(() -> new NotFoundPlaceException("Place not found with placeKey: " + placeKey));
    }
}