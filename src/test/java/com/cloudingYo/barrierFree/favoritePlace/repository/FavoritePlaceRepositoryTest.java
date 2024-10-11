package com.cloudingYo.barrierFree.favoritePlace.repository;

import com.cloudingYo.barrierFree.favoritePlace.entity.FavoritePlace;
import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.place.repository.PlaceRepository;
import com.cloudingYo.barrierFree.user.entity.User;
import com.cloudingYo.barrierFree.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class FavoritePlaceRepositoryTest {

//    @Autowired
//    EntityManager em ;
//    @Autowired
//    FavoritePlaceRepository favoritePlaceRepository;
//    @Autowired
//    PlaceRepository placeRepository;
//    @Autowired
//    UserRepository userRepository;

//    @Test
//    void findFavoritePlaces() {
//        // given
//        User user = User.builder()
//                .username("a")
//                .password("b")
//                .build();
//        userRepository.save(user);
//
//        Place place = Place.builder()
//                .placename("c")
//                .build();
//        placeRepository.save(place);
//
//        // FavoritePlaces
//        FavoritePlace favoritePlace = FavoritePlace.builder()
//                .place(place)
//                .user(user)
//                .build();
//        favoritePlaceRepository.save(favoritePlace);
//        em.flush();
//        em.clear();
//        // when
//
//        FavoritePlace findFavoritePlace = favoritePlaceRepository.findById(favoritePlace.getId()).get();
//        // then
//
//        Assertions.assertThat(favoritePlace.getPlace().getPlacename()).isEqualTo(findFavoritePlace.getPlace().getPlacename());
//    }

}