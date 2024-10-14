package com.cloudingYo.barrierFree.infraNearbyPlace.repository;

import com.cloudingYo.barrierFree.infra.entity.Infra;
import com.cloudingYo.barrierFree.infra.repository.InfraRepository;
import com.cloudingYo.barrierFree.infraNearbyPlace.entity.InfraNearbyPlace;
import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.place.repository.PlaceRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class InfraNearbyPlaceRepositoryTest {

//    @Autowired
//    EntityManager em ;
//
//    @Autowired InfraNearbyPlaceRepository infraNearbyPlaceRepository;
//    @Autowired InfraRepository infraRepository;
//    @Autowired PlaceRepository placeRepository;
//
//    @Test
//    void findInfraNearbyPlace() {
//        // given
//        Infra infra = Infra.builder()
//                .infraname("a")
//                .build();
//        infraRepository.save(infra);
//
//        Place place = Place.builder()
//                .placename("b")
//                .build();
//        placeRepository.save(place);
//
//        InfraNearbyPlace infraNearbyPlace = InfraNearbyPlace.builder()
//                .infra(infra)
//                .place(place)
//                .build();
//        infraNearbyPlaceRepository.save(infraNearbyPlace);
//
//        em.flush();
//
//        // when
//        InfraNearbyPlace findInfraNearbyPlace = infraNearbyPlaceRepository.findById(infraNearbyPlace.getId()).get();
//
//        // then
//        assertEquals(findInfraNearbyPlace.getInfra().getInfraname(), infra.getInfraname());
//        assertEquals(findInfraNearbyPlace.getPlace().getPlacename(), place.getPlacename());
//    }
}