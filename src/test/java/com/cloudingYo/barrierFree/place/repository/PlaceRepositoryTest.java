package com.cloudingYo.barrierFree.place.repository;

import com.cloudingYo.barrierFree.place.entity.Place;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PlaceRepositoryTest {

//    @Autowired
//    EntityManager em ;
//    @Autowired
//    PlaceRepository placeRepository ;
//
//    @Test
//    void findByAll() {
//        Place placeA = Place.builder()
//                .placename("a")
//                .latitude(1.0)
//                .longitude(1.0)
//                .build();
//        Place placeB = Place.builder()
//                .placename("b")
//                .latitude(2.0)
//                .longitude(2.0)
//                .build();
//
//        placeRepository.save(placeA);
//        placeRepository.save(placeB);
//
//        em.flush();
//        em.clear();
//
//        List<Place> places = placeRepository.findAll();  // 모든 장소 찾기
//        assertEquals(2, places.size());
//
//        em.flush();
//        em.clear();
//
//        Place findPlace = placeRepository.findByPlacename("a");  // 장소명으로 장소 찾기
//        assertEquals("a", findPlace.getPlacename());
//    }

}