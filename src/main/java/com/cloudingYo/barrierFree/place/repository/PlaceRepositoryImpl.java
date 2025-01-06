package com.cloudingYo.barrierFree.place.repository;

import com.cloudingYo.barrierFree.place.dto.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.user.repository.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceRepositoryImpl implements PlaceRepository {

    private final PlaceJPARepository placeJPARepository;

    @Override
    public Optional<Place> findByPlaceKey(int placeKey) {
        return placeJPARepository.findByPlaceKey(placeKey);
    }

    @Override
    public Optional<PlaceCoordinateDTO> findCoordinateByPlaceKey(int placeKey) {
        return placeJPARepository.findCoordinateByPlaceKey(placeKey);
    }
    //    @Override
//    public Optional<PlaceCoordinateDTO> findCoordinateByPlaceKey(int placeKey) {
//        QPlace place = QPlace.place;
//
//        return Optional.ofNullable(
//                queryFactory.select(
//                                Projections.constructor(PlaceCoordinateDTO.class,
//                                        place.placeKey,
//                                        place.latitude,
//                                        place.longitude)
//                        )
//                        .from(place)
//                        .where(place.placeKey.eq(placeKey))
//                        .fetchOne()
//        );
//    }
}
