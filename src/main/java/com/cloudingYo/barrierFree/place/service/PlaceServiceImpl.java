package com.cloudingYo.barrierFree.place.service;

import com.cloudingYo.barrierFree.place.dto.resp.PlaceCoordinateDTO;
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

    @Override
    @Transactional(readOnly = true)
    public PlaceCoordinateDTO getPlaceCoordinateV2(Long placeKey) {
        return placeRepository.findCoordinateByPlaceKey(placeKey);
    }
}