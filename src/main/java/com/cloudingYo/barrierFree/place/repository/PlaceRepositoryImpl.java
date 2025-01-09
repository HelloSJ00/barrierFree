package com.cloudingYo.barrierFree.place.repository;

import com.cloudingYo.barrierFree.common.exception.model.CustomException;
import com.cloudingYo.barrierFree.place.dto.resp.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.entity.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.cloudingYo.barrierFree.common.exception.enums.ErrorType.NOT_FOUND_PLACE_INFORMATION;

@Repository
@RequiredArgsConstructor
public class PlaceRepositoryImpl implements PlaceRepository {

    private final PlaceJPARepository placeJPARepository;

    @Override
    public Place findByPlaceKey(Long placeKey) {
        return placeJPARepository.findByPlaceKey(placeKey)
                .orElseThrow(() -> new CustomException(NOT_FOUND_PLACE_INFORMATION));
    }

    @Override
    public PlaceCoordinateDTO findCoordinateByPlaceKey(Long placeKey) {
        return placeJPARepository.findCoordinateByPlaceKey(placeKey)
                .orElseThrow(() -> new CustomException(NOT_FOUND_PLACE_INFORMATION));
    }

}
