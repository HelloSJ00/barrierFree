package com.cloudingYo.barrierFree.place.repository;

import com.cloudingYo.barrierFree.place.dto.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.entity.QPlace;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceRepositoryCustomImpl implements PlaceRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<PlaceCoordinateDTO> findCoordinateByPlaceKey(int placeKey) {
        QPlace place = QPlace.place;

        return Optional.ofNullable(
                queryFactory.select(
                                Projections.constructor(PlaceCoordinateDTO.class,
                                        place.placeKey,
                                        place.latitude,
                                        place.longitude)
                        )
                        .from(place)
                        .where(place.placeKey.eq(placeKey))
                        .fetchOne()
        );
    }
}
