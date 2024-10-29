package com.cloudingYo.barrierFree.place.repository;

import com.cloudingYo.barrierFree.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findByPlacename(String placename);
    Place findByPlaceKey(int placeKey);
}
