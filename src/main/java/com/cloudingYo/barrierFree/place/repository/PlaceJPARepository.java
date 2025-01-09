package com.cloudingYo.barrierFree.place.repository;

import com.cloudingYo.barrierFree.place.dto.resp.PlaceCoordinateDTO;
import com.cloudingYo.barrierFree.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PlaceJPARepository extends JpaRepository<Place, Long> {

    @Query( "SELECT new com.cloudingYo.barrierFree.place.dto.resp.PlaceCoordinateDTO(p.placeKey,p.latitude,p.longitude)" +
            "FROM Place p WHERE p.placeKey = :placeKey")
    Optional<PlaceCoordinateDTO> findCoordinateByPlaceKey(@Param("placeKey") Long placeKey);

    Optional<Place> findByPlaceKey(Long placeKey);
}
