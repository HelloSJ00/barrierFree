package com.cloudingYo.barrierFree.place.repository;

import com.cloudingYo.barrierFree.place.dto.resp.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.entity.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceRepositoryImpl implements PlaceRepository {

    private final PlaceJPARepository placeJPARepository;

    @Override
    public Optional<Place> findByPlaceKey(Long placeKey) {
        return placeJPARepository.findByPlaceKey(placeKey);
    }

    @Override
    public Optional<PlaceCoordinateDTO> findCoordinateByPlaceKey(Long placeKey) {
        return placeJPARepository.findCoordinateByPlaceKey(placeKey);
    }

}
