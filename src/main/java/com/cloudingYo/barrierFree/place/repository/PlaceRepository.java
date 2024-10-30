package com.cloudingYo.barrierFree.place.repository;

import com.cloudingYo.barrierFree.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findByPlacename(String placename);
    Optional<Place> findByPlaceKey(int placeKey);
}
